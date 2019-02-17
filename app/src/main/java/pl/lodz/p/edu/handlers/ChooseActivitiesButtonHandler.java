package pl.lodz.p.edu.handlers;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import pl.lodz.p.edu.R;
import pl.lodz.p.edu.activities.AddPackingListActivity;
import pl.lodz.p.edu.database.PackAssistantDatabase;
import pl.lodz.p.edu.database.entity.ActivityEnum;
import pl.lodz.p.edu.database.entity.StatusEnum;
import pl.lodz.p.edu.database.entity.WeatherEnum;
import pl.lodz.p.edu.database.entity.definitions.ItemDefinition;
import pl.lodz.p.edu.database.entity.definitions.PackingListDefinition;
import pl.lodz.p.edu.database.entity.definitions.SectionDefinition;
import pl.lodz.p.edu.database.entity.instances.ItemInstance;
import pl.lodz.p.edu.database.entity.instances.PackingListInstance;
import pl.lodz.p.edu.database.entity.instances.PackingListSectionInstance;
import pl.lodz.p.edu.database.entity.instances.SectionInstance;
import pl.lodz.p.edu.database.entity.instances.SectionItemInstance;
import pl.lodz.p.edu.fragments.CreatedPackingListFragment;
import pl.lodz.p.edu.view.model.ActivitySelectedDataModel;
import pl.lodz.p.edu.view.model.PackingListCreationParameters;
import pl.lodz.p.edu.utils.Utils;

public class ChooseActivitiesButtonHandler implements ClickHandler {

    private static final Long INVALID_ID = -1L;
    private final List<ActivitySelectedDataModel> models;
    private final FragmentActivity activity;
    private PackingListCreationParameters params;

    public ChooseActivitiesButtonHandler(FragmentActivity activity, List<ActivitySelectedDataModel> models) {
        this.activity = activity;
        this.models = models;
    }

    @Override
    public void onClick() {
        setSelectedActivitiesList();
        final long instanceId = createAndSaveDataInDatabase();
        if (instanceId == INVALID_ID) {
            Toast.makeText(
                    activity.getApplicationContext(),
                    "Couldn't create data from selected parameters. Contact dev team to solve the problem.",
                    Toast.LENGTH_LONG
            ).show();
            return;
        }
        goToTheNextView(instanceId);
    }

    @SuppressLint("CheckResult")
    private long createAndSaveDataInDatabase() {
        try {
            final PackAssistantDatabase db = PackAssistantDatabase.getInstance(this.activity);
            final CreateAndSaveDataInDatabaseTask task = new CreateAndSaveDataInDatabaseTask(db, params);
            return task.execute(getModelNames()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
            return INVALID_ID;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return INVALID_ID;
        }
    }

    private String[] getModelNames() {
        final List<String> names = new ArrayList<>();
        names.add("General");
        for (ActivityEnum activity : this.params.getActivities()) {
            names.add(activity.getName());
        }
        names.add("Other");
        return names.toArray(new String[0]);
    }

    private void setSelectedActivitiesList() {
        final AddPackingListActivity parentActivity = (AddPackingListActivity) this.activity;
        this.params = parentActivity.getCreationParameters();
        this.params.setActivities(getSelectedModels());
    }

    private void goToTheNextView(long instanceId) {
        final FragmentManager manager = this.activity.getSupportFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();

        final CreatedPackingListFragment fragment = new CreatedPackingListFragment();
        fragment.setPackingListInstanceId(instanceId);

        transaction.replace(R.id.fragment_container, fragment, "CREATED_LIST_FRAGMENT");
        transaction.commit();
    }

    private List<ActivityEnum> getSelectedModels() {
        final List<ActivityEnum> selected = new ArrayList<>();
        for (int i = 0; i < this.models.size(); i++) {
            if (this.models.get(i).isSelected()) {
                selected.add(ActivityEnum.valueOf(this.models.get(i).getActivity().name()));
            }
        }
        return selected;
    }

    private static class CreateAndSaveDataInDatabaseTask extends AsyncTask<String, Void, Long> {

        private final PackAssistantDatabase db;
        private final PackingListCreationParameters params;

        public CreateAndSaveDataInDatabaseTask(PackAssistantDatabase db, PackingListCreationParameters params) {
            this.db = db;
            this.params = params;
        }

        @Override
        protected Long doInBackground(String... modelNames) {
            final List<SectionDefinition> sections = db.sectionDefinitionsDao().getByName(modelNames);

            final Map<Long, List<ItemDefinition>> itemDefinitionsBySectionId = new HashMap<>();
            for (SectionDefinition sectionDefinition : sections) {
                List<ItemDefinition> items = db.itemDefinitionsDao().getBySectionId(sectionDefinition.getId());
                if (items == null) {
                    items = new ArrayList<>();
                }
                itemDefinitionsBySectionId.put(sectionDefinition.getId(), items);
            }

            final PackingListDefinition packingListDefinition = new PackingListDefinition(Utils.random(10), false);
            long packingListDefinitionId = db.packingListDefinitionsDao().insertSingle(packingListDefinition);

            final PackingListInstance packingListInstance = new PackingListInstance(packingListDefinitionId, new Date(), StatusEnum.OPEN, params.getCityName());
            final long packingListInstanceId = db.packingListInstancesDao().insertSingle(packingListInstance);

            for (SectionDefinition sectionDefinition : sections) {
                final SectionInstance sectionInstance = new SectionInstance(sectionDefinition.getId());
                long sectionInstanceId = db.sectionInstancesDao().insertSingle(sectionInstance);

                List<ItemDefinition> items = itemDefinitionsBySectionId.get(sectionDefinition.getId());

                if (items != null && !items.isEmpty()) {
                    if (sectionDefinition.getName().equals("Other")) {
                        items = filterItems(items, params);
                    }

                    for (ItemDefinition itemDefinition : items) {
                        final ItemInstance itemInstance = new ItemInstance(itemDefinition.getId());
                        long itemInstanceId = db.itemInstancesDao().insertSingle(itemInstance);
                        db.sectionItemInstancesDao().insertSingle(new SectionItemInstance(sectionInstanceId, itemInstanceId, sectionDefinition.getName().equals("General")));
                    }

                    db.packingListSectionInstancesDao().insertSingle(new PackingListSectionInstance(packingListInstanceId, sectionInstanceId, false));
                }
            }
            return packingListInstanceId;
        }

        private List<ItemDefinition> filterItems(List<ItemDefinition> items, PackingListCreationParameters params) {
            final double minTemp = params.getMinTemp();
            final double maxTemp = params.getMaxTemp();
            final List<WeatherEnum> weathers = params.getWeather();

            final List<ItemDefinition> filtered = new ArrayList<>();
            for (ItemDefinition itemDefinition : items) {
                if (((itemDefinition.getMinTemp() != null && itemDefinition.getMinTemp() >= minTemp) && (itemDefinition.getMaxTemp() != null && itemDefinition.getMaxTemp() <= maxTemp)) || weathers.contains(itemDefinition.getWeather())) {
                    filtered.add(itemDefinition);
                }
            }
            return filtered;
        }
    }
}
