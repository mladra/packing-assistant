package edu.p.lodz.pl.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import edu.p.lodz.pl.R;
import edu.p.lodz.pl.databinding.ActivityAddPackingListBinding;
import edu.p.lodz.pl.handlers.AddPackingListClickHandler;

public class AddPackingListActivity extends AppCompatActivity {

    private ActivityAddPackingListBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_add_packing_list);
        this.binding.setHandler(new AddPackingListClickHandler(this));

        getSupportActionBar().setTitle("Generate packing list");
    }
}
