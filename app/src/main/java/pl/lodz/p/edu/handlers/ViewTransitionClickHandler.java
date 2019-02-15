package pl.lodz.p.edu.handlers;

import android.app.Activity;
import android.content.Intent;

public class ViewTransitionClickHandler implements ClickHandler {

    private final Activity activity;
    private final Class<?> destination;

    public ViewTransitionClickHandler(Activity activity, Class<?> destination) {
        this.activity = activity;
        this.destination = destination;
    }

    @Override
    public void onClick() {
        final Intent intent = new Intent(this.activity.getBaseContext(), this.destination);
        this.activity.startActivity(intent);
    }
}
