package pl.lodz.p.edu.handlers;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.FragmentActivity;
import pl.lodz.p.edu.database.PackAssistantDatabase;
import pl.lodz.p.edu.database.entity.instances.ItemInstance;
import pl.lodz.p.edu.fragments.CreatedPackingListFragment;
import pl.lodz.p.edu.view.model.Item;
import pl.lodz.p.edu.view.model.PackingList;
import pl.lodz.p.edu.view.model.Section;

public abstract class AbstractPackingListHandler implements ClickHandler {

    CreatedPackingListFragment fragment;

    AbstractPackingListHandler(CreatedPackingListFragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void onClick() {
        final SaveItemInstancesAsyncTask task = new SaveItemInstancesAsyncTask(fragment);
        task.execute(fragment.getPackingList());
        final FragmentActivity activity = fragment.getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    private static class SaveItemInstancesAsyncTask extends AsyncTask<PackingList, Void, Void> {

        private static final String TAG = "SaveItemsAsyncTask";
        private CreatedPackingListFragment fragment;

        SaveItemInstancesAsyncTask(CreatedPackingListFragment fragment) {
            this.fragment = fragment;
        }

        @Override
        protected Void doInBackground(PackingList... lists) {
            Log.d(TAG, "doInBackground: Saving item instances...");
            final List<ItemInstance> allItemInstances = new ArrayList<>();
            if (lists != null && lists.length > 0) {
                for (final PackingList list : lists) {
                    final List<Section> sections = list.getSections();
                    if (sections != null && !sections.isEmpty()) {
                        for (final Section section : sections) {
                            final List<Item> items = section.getItems();
                            if (items != null && !items.isEmpty()) {
                                for (final Item item : items) {
                                    allItemInstances.add(item.getInstance());
                                }
                            }
                        }
                    }
                }
            }

            if (!allItemInstances.isEmpty()) {
                final PackAssistantDatabase db = PackAssistantDatabase.getInstance(fragment.getContext());
                db.itemInstancesDao().update(allItemInstances.toArray(new ItemInstance[0]));
            }
            Log.d(TAG, "doInBackground: Successfully saved item instances.");
            return null;
        }
    }
}
