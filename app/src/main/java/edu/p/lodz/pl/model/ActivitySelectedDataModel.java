package edu.p.lodz.pl.model;

import edu.p.lodz.pl.database.entity.ActivityEnum;

public class ActivitySelectedDataModel {

    private ActivityEnum activity;
    private boolean selected;

    public ActivitySelectedDataModel(ActivityEnum activity, boolean selected) {
        this.activity = activity;
        this.selected = selected;
    }

    public ActivityEnum getActivity() {
        return activity;
    }

    public void setActivity(ActivityEnum activity) {
        this.activity = activity;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
