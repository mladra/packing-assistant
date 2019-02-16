package pl.lodz.p.edu.handlers;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import pl.lodz.p.edu.database.PackAssistantDatabase;
import pl.lodz.p.edu.database.entity.StatusEnum;
import pl.lodz.p.edu.database.entity.instances.PackingListInstance;
import pl.lodz.p.edu.fragments.CreatedPackingListFragment;
import pl.lodz.p.edu.view.model.PackingList;

public class ClosePackingListHandler extends AbstractPackingListHandler implements ClickHandler {

    public ClosePackingListHandler(CreatedPackingListFragment fragment) {
        super(fragment);
    }

    @Override
    public void onClick() {
        super.onClick();
        final ClosePackingListInstanceTask task = new ClosePackingListInstanceTask(fragment);
        task.execute(fragment.getPackingList());
    }

    private static class ClosePackingListInstanceTask extends AsyncTask<PackingList, Void, Void> {

        private static final String TAG = "CloseListAsyncTask";

        private CreatedPackingListFragment fragment;

        ClosePackingListInstanceTask(CreatedPackingListFragment fragment) {
            this.fragment = fragment;
        }

        @Override
        protected Void doInBackground(PackingList... packingLists) {
            Log.d(TAG, "doInBackground: CLosing packing list...");
            final List<PackingListInstance> instances = new ArrayList<>();
            if (packingLists != null && packingLists.length > 0) {
                for (final PackingList list : packingLists) {
                    list.getInstance().setStatus(StatusEnum.CLOSED);
                    instances.add(list.getInstance());
                }
            }

            if (!instances.isEmpty()) {
                final PackAssistantDatabase db = PackAssistantDatabase.getInstance(fragment.getContext());
                db.packingListInstancesDao().update(instances.toArray(new PackingListInstance[0]));
            }
            Log.d(TAG, "doInBackground: Packing lists successfully closed.");
            return null;
        }
    }
}
