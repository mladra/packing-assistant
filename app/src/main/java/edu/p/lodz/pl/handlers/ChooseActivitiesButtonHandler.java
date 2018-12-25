package edu.p.lodz.pl.handlers;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.p.lodz.pl.model.ActivitySelectedDataModel;

public class ChooseActivitiesButtonHandler implements ClickHandler {

    private static final String TAG = "ClickHandler";

    private List<ActivitySelectedDataModel> models;

    public ChooseActivitiesButtonHandler(List<ActivitySelectedDataModel> models) {
        this.models = models;
    }

    @Override
    public void onClick() {
        List<ActivitySelectedDataModel> selectedModels = getSelectedModels();
        Log.d(TAG, "Selected models count: " + selectedModels.size());
        for (ActivitySelectedDataModel model : selectedModels) {
            Log.d(TAG, "Model selected: " + model.getActivity().getName());
        }
    }

    private List<ActivitySelectedDataModel> getSelectedModels() {
        final List<ActivitySelectedDataModel> result = new ArrayList<>();
        for (ActivitySelectedDataModel model : models) {
            if (model.isSelected()) {
                result.add(model);
            }
        }
        return result;
    }
}
