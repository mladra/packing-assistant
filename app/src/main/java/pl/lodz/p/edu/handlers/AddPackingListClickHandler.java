package pl.lodz.p.edu.handlers;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import pl.lodz.p.edu.R;
import pl.lodz.p.edu.activities.AddPackingListActivity;
import pl.lodz.p.edu.api.yahoo.ConditionCodesEnum;
import pl.lodz.p.edu.api.yahoo.Forecast;
import pl.lodz.p.edu.api.yahoo.WeatherRequest;
import pl.lodz.p.edu.api.yahoo.WeatherRequestQueue;
import pl.lodz.p.edu.api.yahoo.WeatherResponse;
import pl.lodz.p.edu.database.PackAssistantDatabase;
import pl.lodz.p.edu.database.entity.StatusEnum;
import pl.lodz.p.edu.database.entity.WeatherEnum;
import pl.lodz.p.edu.database.entity.definitions.ItemDefinition;
import pl.lodz.p.edu.database.entity.definitions.PackingListDefinition;
import pl.lodz.p.edu.database.entity.definitions.SectionDefinition;
import pl.lodz.p.edu.database.entity.definitions.SectionItemDefinition;
import pl.lodz.p.edu.database.entity.instances.ItemInstance;
import pl.lodz.p.edu.database.entity.instances.PackingListInstance;
import pl.lodz.p.edu.database.entity.instances.PackingListSectionInstance;
import pl.lodz.p.edu.database.entity.instances.SectionInstance;
import pl.lodz.p.edu.database.entity.instances.SectionItemInstance;
import pl.lodz.p.edu.fragments.ChooseActivitiesPackingListFragment;
import pl.lodz.p.edu.fragments.CreatedPackingListFragment;
import pl.lodz.p.edu.view.model.PackingListCreationParameters;

public class AddPackingListClickHandler implements ClickHandler, com.android.volley.Response.Listener<WeatherResponse>, Response.ErrorListener {

    private static final String TAG = "AddPackingListClickHand";

    private FragmentActivity activity;
    private AddPackingListActivity parent;

    public AddPackingListClickHandler(FragmentActivity activity, AddPackingListActivity parent) {
        this.activity = activity;
        this.parent = parent;
    }

    @Override
    public void onClick() {
        final PackingListCreationParameters params = parent.getCreationParameters();
        if (params.isFromTemplate()) {
            validateAndCreateListFromChosenTemplate(params);
        } else {
            receiveWeatherDataAndGoToTheNextView(params);
        }
    }

    private void validateAndCreateListFromChosenTemplate(PackingListCreationParameters params) {
        if (params.getTemplate() == null) {
            Snackbar.make(parent.getBinding().getRoot(), R.string.choose_template_error, Snackbar.LENGTH_LONG).show();
        } else {
            try {
                createListFromChosenTemplate(params);
            } catch (ExecutionException e) {
                Log.d(TAG, "validateAndCreateListFromChosenTemplate: " + e.getMessage());
                e.printStackTrace();
            } catch (InterruptedException e) {
                Log.d(TAG, "validateAndCreateListFromChosenTemplate: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void createListFromChosenTemplate(PackingListCreationParameters params) throws ExecutionException, InterruptedException {
        final CreateListFromTemplateTask task = new CreateListFromTemplateTask(activity, parent);
        long packingListInstanceId = task.execute(params.getTemplate()).get();

        final FragmentManager manager = activity.getSupportFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();

        final CreatedPackingListFragment fragment = new CreatedPackingListFragment();
        fragment.setPackingListInstanceId(packingListInstanceId);
        transaction.replace(R.id.fragment_container, fragment, "CREATED_LIST_FRAGMENT");
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void receiveWeatherDataAndGoToTheNextView(PackingListCreationParameters params) {
        try {
            final ApplicationInfo applicationInfo = parent.getPackageManager().getApplicationInfo(parent.getPackageName(), PackageManager.GET_META_DATA);
            final Bundle bundle = applicationInfo.metaData;

            final String appId = bundle.getString("APP_ID");
            final String clientId = bundle.getString("CLIENT_ID");
            final String clientSecret = bundle.getString("CLIENT_SECRET");

            final WeatherRequest request = new WeatherRequest(Request.Method.GET, null, null, this, this, appId, clientId, clientSecret, params.getCityName());
            WeatherRequestQueue.getInstance(parent).add(request);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d(TAG, "onErrorResponse: " + error.toString());
        Snackbar.make(parent.getBinding().getRoot(), R.string.destination_error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(WeatherResponse response) {
        Log.d(TAG, "onResponse: Successfully obtained weather data.");
        extractWeatherDataIntoParams(response);
        goToTheNextView();
    }

    private void extractWeatherDataIntoParams(WeatherResponse response) {
        final PackingListCreationParameters parameters = parent.getCreationParameters();
        parameters.setMinTemp(findMinTemp(response));
        parameters.setMaxTemp(findMaxTemp(response));
        parameters.setWeather(findWeatherEnums(response));
    }

    private List<WeatherEnum> findWeatherEnums(WeatherResponse response) {
        if (response.getForecasts() == null || response.getForecasts().isEmpty()) {
            return new ArrayList<>();
        }

        Set<WeatherEnum> result = new HashSet<>();
        for (Forecast forecast : response.getForecasts()) {
            final ConditionCodesEnum condition = ConditionCodesEnum.getByCode(forecast.getCode());
            if (condition != null && !ConditionCodesEnum.NOT_AVAILABLE.equals(condition)) {
                result.add(condition.getWeather());
            }
        }
        return new ArrayList<>(result);
    }

    private Double findMinTemp(WeatherResponse response) {
        if (response.getForecasts() == null || response.getForecasts().isEmpty()) {
            return -20.0D;
        }

        Double min = Double.MAX_VALUE;
        for (Forecast forecast : response.getForecasts()) {
            if (forecast.getLow() < min) {
                min = forecast.getLow();
            }
        }
        return min;
    }

    private Double findMaxTemp(WeatherResponse response) {
        if (response.getForecasts() == null || response.getForecasts().isEmpty()) {
            return 60.0D;
        }

        Double max = Double.MIN_VALUE;
        for (Forecast forecast : response.getForecasts()) {
            if (forecast.getHigh() > max) {
                max = forecast.getHigh();
            }
        }
        return max;
    }

    private void goToTheNextView() {
        final FragmentManager manager = activity.getSupportFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();
        final ChooseActivitiesPackingListFragment fragment = new ChooseActivitiesPackingListFragment();
        transaction.replace(R.id.fragment_container, fragment, "ACTIVITIES_FRAGMENT");
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private static class CreateListFromTemplateTask extends AsyncTask<PackingListDefinition, Void, Long> {

        private FragmentActivity activity;
        private AddPackingListActivity parent;

        public CreateListFromTemplateTask(FragmentActivity activity, AddPackingListActivity parent) {
            this.activity = activity;
            this.parent = parent;
        }

        @Override
        protected Long doInBackground(PackingListDefinition... packingListDefinitions) {
            if (packingListDefinitions != null && packingListDefinitions.length > 0) {
                final PackAssistantDatabase db = PackAssistantDatabase.getInstance(activity.getApplicationContext());
                final PackingListDefinition packingListDefinition = packingListDefinitions[0];
                final PackingListInstance packingListInstance = new PackingListInstance(packingListDefinition.getId(), new Date(), StatusEnum.OPEN, parent.getCreationParameters().getCityName());
                final long packingListInstanceId = db.packingListInstancesDao().insertSingle(packingListInstance);
                final List<SectionDefinition> sections = db.sectionDefinitionsDao().getByPackingListId(packingListDefinition.getId());
                if (sections != null && !sections.isEmpty()) {
                    for (final SectionDefinition sectionDefinition : sections) {
                        final SectionInstance sectionInstance = new SectionInstance(sectionDefinition.getId());
                        final List<ItemDefinition> sectionItems = db.itemDefinitionsDao().getBySectionId(sectionDefinition.getId());
                        if (sectionItems != null && !sectionItems.isEmpty()) {
                            final long sectionInstanceId = db.sectionInstancesDao().insertSingle(sectionInstance);
                            for (final ItemDefinition itemDefinition : sectionItems) {
                                final ItemInstance itemInstance = new ItemInstance(itemDefinition.getId());
                                final SectionItemDefinition sectionItemDefinition = db.sectionItemDefinitionsDao().getByItemIdAndSectionId(itemDefinition.getId(), sectionDefinition.getId());
                                final long itemInstanceId = db.itemInstancesDao().insertSingle(itemInstance);
                                db.sectionItemInstancesDao().insertSingle(new SectionItemInstance(sectionInstanceId, itemInstanceId, sectionItemDefinition.isRequired()));
                            }

                            db.packingListSectionInstancesDao().insertSingle(new PackingListSectionInstance(packingListInstanceId, sectionInstanceId, false));
                        }
                    }
                }
                return packingListInstanceId;
            }
            return null;
        }
    }
}
