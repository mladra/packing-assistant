package pl.lodz.p.edu.activities;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import pl.lodz.p.edu.databinding.ActivityPackingListsBinding;
import pl.lodz.p.edu.R;
import pl.lodz.p.edu.database.PackAssistantDatabase;
import pl.lodz.p.edu.database.entity.instances.PackingListInstance;
import pl.lodz.p.edu.view.adapters.PackingListViewAdapter;
import pl.lodz.p.edu.view.adapters.ViewAdapter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PackingListsActivity extends AbstractActivity<ActivityPackingListsBinding> {

    private final static String TAG = "PackingListsActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.initBinding(R.layout.activity_packing_lists);
        setHeaderTitle(R.string.packing_lists_title);
        initPackingListsRecyclerView();
    }

    private void initPackingListsRecyclerView() {
        this.binding.packingListsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        final Disposable subscription = PackAssistantDatabase
                .getInstance(this)
                .packingListInstancesDao()
                .getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(createPackingListsObserver());
    }

    private Consumer<List<PackingListInstance>> createPackingListsObserver() {
        return new Consumer<List<PackingListInstance>>() {
            @Override
            public void accept(List<PackingListInstance> data) {
                Log.d(TAG, "Retrieved packingListInstances from database. Size: " + data.size());
                binding.packingListsRecyclerView.setAdapter(new PackingListViewAdapter(data, R.layout.single_packing_list_layout));
            }
        };
    }
}
