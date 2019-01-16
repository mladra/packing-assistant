package edu.p.lodz.pl.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import edu.p.lodz.pl.R;
import edu.p.lodz.pl.databinding.ActivityAddPackingListBinding;
import edu.p.lodz.pl.fragments.BasicDataPackingListFragment;
import edu.p.lodz.pl.model.PackingListCreationParameters;

public class AddPackingListActivity extends AppCompatActivity {

    private ActivityAddPackingListBinding binding;

    private PackingListCreationParameters creationParameters = new PackingListCreationParameters();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_add_packing_list);

        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        final BasicDataPackingListFragment fragment = new BasicDataPackingListFragment();
        fragmentTransaction.add(R.id.fragment_container, fragment);

        fragmentTransaction.commit();

        getSupportActionBar().setTitle("Generate packing list");
    }

    public PackingListCreationParameters getCreationParameters() {
        return creationParameters;
    }
}
