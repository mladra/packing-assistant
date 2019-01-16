package edu.p.lodz.pl.handlers;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import edu.p.lodz.pl.R;
import edu.p.lodz.pl.fragments.ChooseActivitiesPackingListFragment;

public class AddPackingListClickHandler implements ClickHandler {

    private FragmentActivity activity;

    public AddPackingListClickHandler(FragmentActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick() {
        final FragmentManager manager = activity.getSupportFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();

        final ChooseActivitiesPackingListFragment fragment = new ChooseActivitiesPackingListFragment();
        transaction.replace(R.id.fragment_container, fragment);

        transaction.addToBackStack(null);

        transaction.commit();
    }
}
