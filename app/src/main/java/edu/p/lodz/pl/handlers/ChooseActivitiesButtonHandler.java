package edu.p.lodz.pl.handlers;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import edu.p.lodz.pl.R;
import edu.p.lodz.pl.activities.AddPackingListActivity;
import edu.p.lodz.pl.database.PackAssistantDatabase;
import edu.p.lodz.pl.database.entity.ActivityEnum;
import edu.p.lodz.pl.database.entity.StatusEnum;
import edu.p.lodz.pl.database.entity.definitions.ItemDefinition;
import edu.p.lodz.pl.database.entity.definitions.PackingListDefinition;
import edu.p.lodz.pl.database.entity.definitions.SectionDefinition;
import edu.p.lodz.pl.database.entity.instances.ItemInstance;
import edu.p.lodz.pl.database.entity.instances.PackingListInstance;
import edu.p.lodz.pl.database.entity.instances.PackingListSectionInstance;
import edu.p.lodz.pl.database.entity.instances.SectionInstance;
import edu.p.lodz.pl.database.entity.instances.SectionItemInstance;
import edu.p.lodz.pl.fragments.CreatedPackingListFragment;
import edu.p.lodz.pl.model.ActivitySelectedDataModel;
import edu.p.lodz.pl.model.PackingListCreationParameters;
import edu.p.lodz.pl.utils.Utils;

public class ChooseActivitiesButtonHandler implements ClickHandler {

    private static final Long INVALID_ID = -1L;
    private static final String TAG = "ChooseActivitiesBtnHndl";
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
            final CreateAndSaveDataInDatabaseTask task = new CreateAndSaveDataInDatabaseTask(db);
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
        List<String> names = new ArrayList<>();
        names.add("General");
        for (ActivityEnum activity : this.params.getActivities()) {
            names.add(activity.getName());
        }
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

        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
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

        public CreateAndSaveDataInDatabaseTask(PackAssistantDatabase db) {
            this.db = db;
        }

        @Override
        protected Long doInBackground(String... modelNames) {
            final List<SectionDefinition> sections = db.sectionDefinitionsDao().getByName(modelNames);

            final Map<Long, List<ItemDefinition>> itemDefinitionsBySectionId = new HashMap<>();
            for (SectionDefinition sectionDefinition : sections) {
                List<ItemDefinition> items = db.itemDefinitionsDao().getBySectionId(sectionDefinition.getId());
                itemDefinitionsBySectionId.put(sectionDefinition.getId(), items);
            }

            final PackingListDefinition packingListDefinition = new PackingListDefinition(Utils.random(10));
            long packingListDefinitionId = db.packingListDefinitionsDao().insertSingle(packingListDefinition);

            final PackingListInstance packingListInstance = new PackingListInstance(packingListDefinitionId, new Date(), StatusEnum.OPEN);
            final long packingListInstanceId = db.packingListInstancesDao().insertSingle(packingListInstance);

            for (SectionDefinition sectionDefinition : sections) {
                final SectionInstance sectionInstance = new SectionInstance(sectionDefinition.getId());
                long sectionInstanceId = db.sectionInstancesDao().insertSingle(sectionInstance);

                for (ItemDefinition itemDefinition : itemDefinitionsBySectionId.get(sectionDefinition.getId())) {
                    final ItemInstance itemInstance = new ItemInstance(itemDefinition.getId());
                    long itemInstanceId = db.itemInstancesDao().insertSingle(itemInstance);
                    db.sectionItemInstancesDao().insertSingle(new SectionItemInstance(sectionInstanceId, itemInstanceId, false));
                }

                db.packingListSectionInstancesDao().insertSingle(new PackingListSectionInstance(packingListInstanceId, sectionInstanceId, false));
            }
            return packingListInstanceId;
        }
    }
}
