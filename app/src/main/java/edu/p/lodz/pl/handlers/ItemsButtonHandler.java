package edu.p.lodz.pl.handlers;

import android.app.Activity;
import android.content.Intent;

import edu.p.lodz.pl.activities.ItemsActivity;

public class ItemsButtonHandler implements ClickHandler {

    private Activity activity;

    public ItemsButtonHandler(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick() {
        final Intent intent = new Intent(activity.getBaseContext(), ItemsActivity.class);
        activity.startActivity(intent);
    }
}
