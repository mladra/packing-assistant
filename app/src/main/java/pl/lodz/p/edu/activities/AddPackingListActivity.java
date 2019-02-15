package pl.lodz.p.edu.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import pl.lodz.p.edu.R;
import pl.lodz.p.edu.databinding.ActivityAddPackingListBinding;
import pl.lodz.p.edu.fragments.BasicDataPackingListFragment;
import pl.lodz.p.edu.view.model.PackingListCreationParameters;

public class AddPackingListActivity extends AbstractActivity<ActivityAddPackingListBinding> {

    private PackingListCreationParameters creationParameters = new PackingListCreationParameters();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.initBinding(R.layout.activity_add_packing_list);

        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        final BasicDataPackingListFragment fragment = new BasicDataPackingListFragment();
        fragment.setParent(this);
        fragmentTransaction.add(R.id.fragment_container, fragment);

        fragmentTransaction.commit();

        setHeaderTitle(R.string.generate_packing_list_title);
    }

    public PackingListCreationParameters getCreationParameters() {
        return creationParameters;
    }
}
