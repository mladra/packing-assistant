package pl.lodz.p.edu.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import pl.lodz.p.edu.R;
import pl.lodz.p.edu.activities.extras.ExtrasCodesEnum;
import pl.lodz.p.edu.activities.extras.ResultCodesEnum;
import pl.lodz.p.edu.database.PackAssistantDatabase;
import pl.lodz.p.edu.database.entity.definitions.ItemDefinition;
import pl.lodz.p.edu.databinding.ActivityItemsBinding;
import pl.lodz.p.edu.handlers.AddItemClickHandler;
import pl.lodz.p.edu.view.adapters.ViewAdapter;
import pl.lodz.p.edu.view.model.SelectedItemDataModel;

public class ItemsActivity extends AbstractActivity<ActivityItemsBinding> {

    private boolean selectionMode = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.initBinding(R.layout.activity_items);

        final Intent intent = getIntent();
        if (intent != null) {
            this.selectionMode = intent.getBooleanExtra(ExtrasCodesEnum.SELECTION_MODE.name(), false);
        }

        this.binding.setHandler(new AddItemClickHandler(this));
        initItemsRecyclerView();
    }

    @SuppressWarnings("CheckResult")
    private void initItemsRecyclerView() {
        this.binding.itemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        PackAssistantDatabase
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
                if (selectionMode) {
                    binding.itemsRecyclerView.setAdapter(new ViewAdapter<>(createItemSelectedModel(itemDefinitions), R.layout.single_item_selection_layout));
                } else {
                    binding.itemsRecyclerView.setAdapter(new ViewAdapter<>(itemDefinitions, R.layout.single_item_layout));
                }
            }
        };
    }

    private List<SelectedItemDataModel> createItemSelectedModel(List<ItemDefinition> itemDefinitions) {
        final List<SelectedItemDataModel> result = new ArrayList<>();
        for (ItemDefinition definition : itemDefinitions) {
            result.add(new SelectedItemDataModel(definition));
        }
        return result;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (selectionMode) {
            final MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.item_selection_menu, menu);
            return true;
        } else {
            return super.onCreateOptionsMenu(menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (selectionMode) {
            switch (item.getItemId()) {
                case R.id.add_button:
                    passSelectedItemsToParentActivity();
                    return true;
                default:
                    return true;
            }
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @SuppressWarnings("unchecked")
    private void passSelectedItemsToParentActivity() {
        ViewAdapter<SelectedItemDataModel> adapter = (ViewAdapter<SelectedItemDataModel>) this.binding.itemsRecyclerView.getAdapter();
        if (adapter != null) {
            List<SelectedItemDataModel> items = adapter.getObjects();
            List<ItemDefinition> selected = retrieveSelectedItems(items);
            int sectionId = getIntent().getIntExtra(ExtrasCodesEnum.SECTION_ID.name(), -1);

            final Intent result = new Intent();
            result.putExtra(ExtrasCodesEnum.SECTION_ID.name(), sectionId);
            result.putExtra(ExtrasCodesEnum.ITEMS.name(), (Serializable) selected);
            setResult(ResultCodesEnum.OK.getNum(), result);
            finish();
        }
    }

    private List<ItemDefinition> retrieveSelectedItems(List<SelectedItemDataModel> items) {
        List<ItemDefinition> result = new ArrayList<>();
        for (SelectedItemDataModel model : items) {
            if (model.isSelected()) {
                result.add(model.getItemDefinition());
            }
        }
        return result;
    }
}
