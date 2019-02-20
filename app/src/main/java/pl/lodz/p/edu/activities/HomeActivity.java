package pl.lodz.p.edu.activities;

import android.os.Bundle;

import pl.lodz.p.edu.R;
import pl.lodz.p.edu.databinding.ActivityHomeBinding;
import pl.lodz.p.edu.handlers.ViewTransitionClickHandler;

public class HomeActivity extends AbstractActivity<ActivityHomeBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.initBinding(R.layout.activity_home);

        this.binding.setHandler(new ViewTransitionClickHandler(this, AddPackingListActivity.class));
        this.binding.setItemsHandler(new ViewTransitionClickHandler(this, ItemsActivity.class));
        this.binding.setPackingListsHandler(new ViewTransitionClickHandler(this, PackingListsActivity.class));
        this.binding.setTemplatesHandler(new ViewTransitionClickHandler(this, TemplatesActivity.class));
    }
}
