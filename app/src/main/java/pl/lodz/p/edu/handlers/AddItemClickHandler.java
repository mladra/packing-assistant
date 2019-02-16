package pl.lodz.p.edu.handlers;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import pl.lodz.p.edu.R;
import pl.lodz.p.edu.database.PackAssistantDatabase;
import pl.lodz.p.edu.database.entity.ActivityEnum;
import pl.lodz.p.edu.database.entity.WeatherEnum;
import pl.lodz.p.edu.database.entity.definitions.ItemDefinition;
import pl.lodz.p.edu.database.entity.definitions.SectionDefinition;
import pl.lodz.p.edu.database.entity.definitions.SectionItemDefinition;
import pl.lodz.p.edu.databinding.DialogAddItemBinding;
import pl.lodz.p.edu.exceptions.ItemAlreadyExistsException;
import pl.lodz.p.edu.view.model.NewItemDataModel;
import pl.lodz.p.edu.view.model.Section;

public class AddItemClickHandler implements ClickHandler {

    private static final String TAG = "AddItemClickHandler";

    private Activity activity;

    public AddItemClickHandler(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick() {
        List<WeatherEnum> weathers = Arrays.asList(WeatherEnum.values());
        ArrayAdapter<WeatherEnum> weatherAdapter = new ArrayAdapter<>(activity, android.R.layout.simple_dropdown_item_1line, weathers);

        List<ActivityEnum> activities = Arrays.asList(ActivityEnum.values());
        ArrayAdapter<ActivityEnum> activitiesAdapter = new ArrayAdapter<>(activity, android.R.layout.simple_dropdown_item_1line, activities);

        final DialogAddItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.dialog_add_item, null, false);
        binding.weatherAutocomplete.setAdapter(weatherAdapter);
        binding.activityAutocomplete.setAdapter(activitiesAdapter);
        binding.setNewItem(new NewItemDataModel());

        AlertDialog dialog = new AlertDialog.Builder(activity)
                .setView(binding.getRoot())
                .setTitle("Add item")
                .setPositiveButton("Create", null)
                .setNegativeButton("Cancel", null)
                .create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialog) {
                Button positive = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "Creating item...");
                        validateAndSave(binding.getNewItem(), activity.getWindow().getDecorView().findViewById(android.R.id.content));
                        dialog.dismiss();
                    }
                });

                Button negative = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_NEGATIVE);
                negative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "Aborting creating item...");
                        dialog.dismiss();
                    }
                });
            }
        });

        dialog.show();
    }

    private void validateAndSave(NewItemDataModel newItem, View root) throws ItemAlreadyExistsException {
        final PackAssistantDatabase db = PackAssistantDatabase.getInstance(activity);
        final ValidateAndSaveItemInDatabaseTask task = new ValidateAndSaveItemInDatabaseTask(db, activity, root);
        task.execute(newItem);
    }

    private static class ValidateAndSaveItemInDatabaseTask extends AsyncTask<NewItemDataModel, Void, List<Long>> {

        private final PackAssistantDatabase db;
        private final Activity activity;
        private final View view;
        private ItemAlreadyExistsException exception;

        private ValidateAndSaveItemInDatabaseTask(PackAssistantDatabase db, Activity activity, View view) {
            this.db = db;
            this.activity = activity;
            this.view = view;
        }

        @Override
        protected List<Long> doInBackground(NewItemDataModel... newItemDataModels) {
            List<Long> createdItemIds = new ArrayList<>();
            for (NewItemDataModel itemDataModel : newItemDataModels) {
                ItemDefinition itemDefinition = db.itemDefinitionsDao().getByName(itemDataModel.getName());
                boolean exists = itemDefinition != null;
                if (exists) {
                    exception = new ItemAlreadyExistsException(activity.getString(R.string.item_with_name_exists_error, itemDataModel.getName()));
                    break;
                }

                long[] ids = db.itemDefinitionsDao().insertAll(ItemDefinition.of(itemDataModel));
                for (long id : ids) {
                    createdItemIds.add(id);
                }

                if (itemDataModel.getActivity() != null) {
                    List<SectionDefinition> sectionDefinition = db.sectionDefinitionsDao().getByName(itemDataModel.getActivity().getName());
                    db.sectionItemDefinitionsDao().insertAll(new SectionItemDefinition(sectionDefinition.iterator().next().getId(), ids[0], itemDataModel.isRequired()));
                }
            }
            return createdItemIds;
        }

        @Override
        protected void onPostExecute(List<Long> longs) {
            if (exception != null) {
                Snackbar.make(view, exception.getMessage(), Snackbar.LENGTH_LONG).show();
                Log.d(TAG, exception.getMessage());
            } else {
                Snackbar.make(view, activity.getString(R.string.item_successfully_created), Snackbar.LENGTH_LONG).show();
            }
        }
    }

}
