package edu.p.lodz.pl.handlers;

import android.content.Intent;

import edu.p.lodz.pl.activities.AddPackingListActivity;
import edu.p.lodz.pl.activities.ChooseActivitiesActivity;

public class AddPackingListClickListener implements ClickHandler {

    private AddPackingListActivity activity;

    public AddPackingListClickListener(AddPackingListActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick() {
        Intent intent = new Intent(activity.getApplicationContext(), ChooseActivitiesActivity.class);
        activity.startActivity(intent);
    }
}