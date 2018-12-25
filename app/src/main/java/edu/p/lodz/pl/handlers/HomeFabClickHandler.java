package edu.p.lodz.pl.handlers;

import android.content.Intent;

import edu.p.lodz.pl.activities.AddPackingListActivity;
import edu.p.lodz.pl.activities.HomeActivity;

public class HomeFabClickHandler implements ClickHandler {

    private HomeActivity activity;

    public HomeFabClickHandler(HomeActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick() {
        final Intent intent = new Intent(activity.getApplicationContext(), AddPackingListActivity.class);
        activity.startActivity(intent);
    }
}
