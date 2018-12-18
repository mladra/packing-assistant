package edu.p.lodz.pl.handlers;

import android.content.Intent;

import edu.p.lodz.pl.activities.AddPackingListActivity;
import edu.p.lodz.pl.activities.HomeActivity;

public class HomeFabClickListener implements ClickHandler {

    private static final String TAG = "HomeFabClickListener";

    private HomeActivity activity;

    public HomeFabClickListener(HomeActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick() {
        Intent intent = new Intent(activity.getApplicationContext(), AddPackingListActivity.class);
        activity.startActivity(intent);
    }
}
