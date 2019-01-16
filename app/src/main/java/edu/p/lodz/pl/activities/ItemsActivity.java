package edu.p.lodz.pl.activities;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import edu.p.lodz.pl.R;
import edu.p.lodz.pl.database.PackAssistantDatabase;
import edu.p.lodz.pl.database.entity.definitions.ItemDefinition;
import edu.p.lodz.pl.databinding.ActivityItemsBinding;
import edu.p.lodz.pl.view.ViewAdapter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ItemsActivity extends AppCompatActivity {

    private static final String TAG = "ItemsActivity";

    private ActivityItemsBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_items);
        initItemsRecyclerView();

        getSupportActionBar().setTitle("Items");
    }

    private void initItemsRecyclerView() {
        this.binding.itemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        final Disposable subscription = PackAssistantDatabase
                .getInstance(this)
                .itemDefinitionsDao()
                .getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(createItemsObserver());
    }

    private Consumer<List<ItemDefinition>> createItemsObserver() {
        return new Consumer<List<ItemDefinition>>() {
            @Override
            public void accept(List<ItemDefinition> itemDefinitions) {
                Log.d(TAG, "Retrieved itemDefinitions from database. Size: " + itemDefinitions.size());
                for (int i = 0; i < itemDefinitions.size(); i++) {
                    Log.d(TAG, itemDefinitions.get(i).getName());
                }
                binding.itemsRecyclerView.setAdapter(new ViewAdapter<>(itemDefinitions, R.layout.single_item_layout));
            }
        };
    }
}
