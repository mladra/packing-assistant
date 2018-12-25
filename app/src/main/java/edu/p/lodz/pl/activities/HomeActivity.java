package edu.p.lodz.pl.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import edu.p.lodz.pl.R;
import edu.p.lodz.pl.databinding.ActivityHomeBinding;
import edu.p.lodz.pl.handlers.HomeFabClickHandler;
import edu.p.lodz.pl.handlers.ItemsButtonHandler;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        this.binding.setHandler(new HomeFabClickHandler(this));
        this.binding.setItemsHandler(new ItemsButtonHandler(this));

        getSupportActionBar().setTitle("Home");
    }
}
