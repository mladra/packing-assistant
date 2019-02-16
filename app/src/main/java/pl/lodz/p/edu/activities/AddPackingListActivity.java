package pl.lodz.p.edu.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import pl.lodz.p.edu.R;
import pl.lodz.p.edu.activities.extras.ExtrasCodesEnum;
import pl.lodz.p.edu.databinding.ActivityAddPackingListBinding;
import pl.lodz.p.edu.fragments.BasicDataPackingListFragment;
import pl.lodz.p.edu.fragments.CreatedPackingListFragment;
import pl.lodz.p.edu.view.model.PackingListCreationParameters;

public class AddPackingListActivity extends AbstractActivity<ActivityAddPackingListBinding> {

    private static final long INVALID_ID = -100L;
    private PackingListCreationParameters creationParameters = new PackingListCreationParameters();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.initBinding(R.layout.activity_add_packing_list);

        String fragmentTag;
        final Fragment fragment;
        final Intent intent = getIntent();
        final long packingListInstanceId = intent.getLongExtra(ExtrasCodesEnum.PACKING_LIST_INSTANCE_ID.name(), INVALID_ID);
        if (packingListInstanceId == INVALID_ID) {
            final BasicDataPackingListFragment newListFragment = new BasicDataPackingListFragment();
            newListFragment.setParent(this);
            fragment = newListFragment;
            fragmentTag = "BASIC_DATA_FRAGMENT";
        } else {
            final CreatedPackingListFragment existingListFragment = new CreatedPackingListFragment();
            existingListFragment.setPackingListInstanceId(packingListInstanceId);
            fragment = existingListFragment;
            fragmentTag = "CREATED_LIST_FRAGMENT";
        }

        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, fragment, fragmentTag);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();

        setHeaderTitle(R.string.generate_packing_list_title);
    }

    @Override
    public void onBackPressed() {
        final CreatedPackingListFragment fragment = (CreatedPackingListFragment) getSupportFragmentManager().findFragmentByTag("CREATED_LIST_FRAGMENT");
        if (fragment != null && fragment.isVisible()) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    public PackingListCreationParameters getCreationParameters() {
        return creationParameters;
    }
}
