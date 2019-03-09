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

public class AddEditItemClickHandler implements ClickHandler {

    private static final String TAG = "AddEditItemClickHandler";

    private Activity activity;
    private ItemDefinition itemDefinition;

    public AddEditItemClickHandler(Activity activity, ItemDefinition itemDefinition) {
        this.activity = activity;
        this.itemDefinition = itemDefinition;
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

        if (itemDefinition != null) {
            showEditExistingItemDialog(binding);
        } else {
            showCreateNewItemDialog(binding);
        }
    }

    private void showEditExistingItemDialog(final DialogAddItemBinding binding) {
        final NewItemDataModel model = NewItemDataModel.of(itemDefinition);
        binding.setNewItem(model);

        final AlertDialog dialog = createAlertDialog(binding, "Edit item", "Update");

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialog) {
                final Button positive = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "Updating item...");
                        final WeatherEnum weatherEnum = (WeatherEnum) binding.weatherAutocomplete.getSelectedItem();
                        final ActivityEnum activityEnum = (ActivityEnum) binding.activityAutocomplete.getSelectedItem();

                        final NewItemDataModel newItemDataModel = binding.getNewItem();
                        newItemDataModel.setWeather(weatherEnum);
                        newItemDataModel.setActivity(activityEnum);
                        validateAndSave(binding.getNewItem(), activity.getWindow().getDecorView().findViewById(android.R.id.content), true);
                        dialog.dismiss();
                    }
                });

                final Button negative = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_NEGATIVE);
                negative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "Aborting updating item...");
                        dialog.dismiss();
                    }
                });
            }
        });

        dialog.show();
    }

    private void showCreateNewItemDialog(final DialogAddItemBinding binding) {
        binding.setNewItem(new NewItemDataModel());

        final AlertDialog dialog = createAlertDialog(binding, "Add item", "Create");

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialog) {
                final Button positive = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "Creating item...");
                        final WeatherEnum weatherEnum = (WeatherEnum) binding.weatherAutocomplete.getSelectedItem();
                        final ActivityEnum activityEnum = (ActivityEnum) binding.activityAutocomplete.getSelectedItem();

                        final NewItemDataModel newItemDataModel = binding.getNewItem();
                        newItemDataModel.setWeather(weatherEnum);
                        newItemDataModel.setActivity(activityEnum);

                        validateAndSave(newItemDataModel, activity.getWindow().getDecorView().findViewById(android.R.id.content), false);
                        dialog.dismiss();
                    }
                });

                final Button negative = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_NEGATIVE);
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

    private AlertDialog createAlertDialog(DialogAddItemBinding binding, String title, String positiveButtonLabel) {
        return new AlertDialog.Builder(activity)
                .setView(binding.getRoot())
                .setTitle(title)
                .setPositiveButton(positiveButtonLabel, null)
                .setNegativeButton("Cancel", null)
                .create();
    }

    private void validateAndSave(NewItemDataModel newItem, View root, boolean editMode) throws ItemAlreadyExistsException {
        final PackAssistantDatabase db = PackAssistantDatabase.getInstance(activity);
        final ValidateAndSaveOrUpdateItemInDatabaseTask task = new ValidateAndSaveOrUpdateItemInDatabaseTask(db, activity, root, editMode);
        task.execute(newItem);
    }

    private static class ValidateAndSaveOrUpdateItemInDatabaseTask extends AsyncTask<NewItemDataModel, Void, List<Long>> {

        private final PackAssistantDatabase db;
        private final Activity activity;
        private final View view;
        private ItemAlreadyExistsException exception;
        private boolean editMode;

        private ValidateAndSaveOrUpdateItemInDatabaseTask(PackAssistantDatabase db, Activity activity, View view, boolean editMode) {
            this.db = db;
            this.activity = activity;
            this.view = view;
            this.editMode = editMode;
        }

        @Override
        protected List<Long> doInBackground(NewItemDataModel... newItemDataModels) {
            List<Long> createdItemIds = new ArrayList<>();
            for (NewItemDataModel itemDataModel : newItemDataModels) {
                ItemDefinition itemDefinition = db.itemDefinitionsDao().getByName(itemDataModel.getName());
                boolean exists = !editMode ? itemDefinition != null : itemDefinition != null && itemDefinition.getId() != itemDataModel.getId() && itemDefinition.getName().equals(itemDataModel.getName());
                if (exists) {
                    exception = new ItemAlreadyExistsException(activity.getString(R.string.item_with_name_exists_error, itemDataModel.getName()));
                    break;
                }

                if (!editMode) {
                    long[] ids = db.itemDefinitionsDao().insertAll(ItemDefinition.of(itemDataModel));
                    for (long id : ids) {
                        createdItemIds.add(id);
                    }

                    if (itemDataModel.getActivity() != null) {
                        List<SectionDefinition> sectionDefinition = db.sectionDefinitionsDao().getByName(itemDataModel.getActivity().getName());
                        db.sectionItemDefinitionsDao().insertAll(new SectionItemDefinition(sectionDefinition.iterator().next().getId(), ids[0], false));
                    }
                } else {
                    db.itemDefinitionsDao().update(ItemDefinition.of(itemDataModel));
                    if (itemDataModel.getActivity() != null) {
                        final SectionItemDefinition sectionItemDefinition = db.sectionItemDefinitionsDao().getByItemId(itemDataModel.getId());
                        if (sectionItemDefinition != null) {
                            final SectionDefinition sectionDefinition = db.sectionDefinitionsDao().getById(sectionItemDefinition.getSectionId());
                            if (!itemDataModel.getActivity().getName().equals(sectionDefinition.getName())) {
                                final List<SectionDefinition> newSectionDefinition = db.sectionDefinitionsDao().getByName(itemDataModel.getActivity().getName());
                                if (newSectionDefinition != null && newSectionDefinition.size() == 1) {
                                    final long newSectionDefinitionId = newSectionDefinition.iterator().next().getId();
                                    sectionItemDefinition.setSectionId(newSectionDefinitionId);
                                    db.sectionItemDefinitionsDao().update(sectionItemDefinition);
                                }
                            }
                        }
                    }
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
                final String msg = this.editMode ? activity.getString(R.string.item_successfully_updated) : activity.getString(R.string.item_successfully_created);
                Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
            }
        }
    }
}
