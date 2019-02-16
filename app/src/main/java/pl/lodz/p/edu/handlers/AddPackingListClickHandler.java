package pl.lodz.p.edu.handlers;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.material.snackbar.Snackbar;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import pl.lodz.p.edu.R;
import pl.lodz.p.edu.activities.AddPackingListActivity;
import pl.lodz.p.edu.api.weather.WeatherService;
import pl.lodz.p.edu.api.weather.data.WeatherResponse;
import pl.lodz.p.edu.database.PackAssistantDatabase;
import pl.lodz.p.edu.database.entity.StatusEnum;
import pl.lodz.p.edu.database.entity.definitions.ItemDefinition;
import pl.lodz.p.edu.database.entity.definitions.PackingListDefinition;
import pl.lodz.p.edu.database.entity.definitions.SectionDefinition;
import pl.lodz.p.edu.database.entity.instances.ItemInstance;
import pl.lodz.p.edu.database.entity.instances.PackingListInstance;
import pl.lodz.p.edu.database.entity.instances.PackingListSectionInstance;
import pl.lodz.p.edu.database.entity.instances.SectionInstance;
import pl.lodz.p.edu.database.entity.instances.SectionItemInstance;
import pl.lodz.p.edu.fragments.ChooseActivitiesPackingListFragment;
import pl.lodz.p.edu.fragments.CreatedPackingListFragment;
import pl.lodz.p.edu.view.model.PackingListCreationParameters;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPackingListClickHandler implements ClickHandler {

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
        final CreateListFromTemplateTask task = new CreateListFromTemplateTask(activity);
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
        WeatherService.getInstance(activity).getWeatherByCityName(params.getCityName()).enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                Log.d(TAG, "Response code: " + response.code());
                Log.d(TAG, "Response: \n" + response.body());
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.d(TAG, "Failure: " + t.toString());
            }
        });

        final FragmentManager manager = activity.getSupportFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();

        final ChooseActivitiesPackingListFragment fragment = new ChooseActivitiesPackingListFragment();
        transaction.replace(R.id.fragment_container, fragment, "ACTIVITIES_FRAGMENT");

        transaction.addToBackStack(null);

        transaction.commit();
    }

    private static class CreateListFromTemplateTask extends AsyncTask<PackingListDefinition, Void, Long> {

        private FragmentActivity activity;

        public CreateListFromTemplateTask(FragmentActivity activity) {
            this.activity = activity;
        }

        @Override
        protected Long doInBackground(PackingListDefinition... packingListDefinitions) {
            if (packingListDefinitions != null && packingListDefinitions.length > 0) {
                final PackAssistantDatabase db = PackAssistantDatabase.getInstance(activity.getApplicationContext());
                final PackingListDefinition packingListDefinition = packingListDefinitions[0];
                final PackingListInstance packingListInstance = new PackingListInstance(packingListDefinition.getId(), new Date(), StatusEnum.OPEN);
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
                                final long itemInstanceId = db.itemInstancesDao().insertSingle(itemInstance);
                                db.sectionItemInstancesDao().insertSingle(new SectionItemInstance(sectionInstanceId, itemInstanceId, false));
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
