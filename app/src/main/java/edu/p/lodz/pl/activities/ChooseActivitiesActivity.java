package edu.p.lodz.pl.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import edu.p.lodz.pl.R;
import edu.p.lodz.pl.databinding.ActivityChooseActivitiesBinding;

public class ChooseActivitiesActivity extends AppCompatActivity {

    private ActivityChooseActivitiesBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_choose_activities);

        getSupportActionBar().setTitle("Choose activities");
    }
}
