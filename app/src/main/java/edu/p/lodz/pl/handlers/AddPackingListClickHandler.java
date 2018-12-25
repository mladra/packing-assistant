package edu.p.lodz.pl.handlers;

import android.content.Intent;

import edu.p.lodz.pl.activities.AddPackingListActivity;
import edu.p.lodz.pl.activities.ChooseActivitiesActivity;

public class AddPackingListClickHandler implements ClickHandler {

    private AddPackingListActivity activity;

    public AddPackingListClickHandler(AddPackingListActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick() {
        final Intent intent = new Intent(activity.getApplicationContext(), ChooseActivitiesActivity.class);
        activity.startActivity(intent);
    }
}
