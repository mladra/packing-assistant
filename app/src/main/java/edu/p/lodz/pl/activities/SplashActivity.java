package edu.p.lodz.pl.activities;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/***
 * Class representing loading screen which is showed when application starts
 */
public class SplashActivity extends AppCompatActivity {

    private static final Class<? extends AppCompatActivity> HOME = HomeActivity.class;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final View rootView = getWindow().getDecorView().getRootView();
        final LayerDrawable layerDrawable = (LayerDrawable) rootView.getBackground();
        final AnimationDrawable animationDrawable = (AnimationDrawable) layerDrawable.getDrawable(1);
        animationDrawable.start();

        startActivity();
    }

    /***
     * Go into home activity when ready
     */
    private void startActivity() {
        final Intent intent = new Intent(this, HOME);
        startActivity(intent);
        finish();
    }
}
