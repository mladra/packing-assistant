package pl.lodz.p.edu.activities;

import android.os.Bundle;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import pl.lodz.p.edu.R;
import pl.lodz.p.edu.database.PackAssistantDatabase;
import pl.lodz.p.edu.database.entity.definitions.PackingListDefinition;
import pl.lodz.p.edu.databinding.ActivityTemplatesBinding;
import pl.lodz.p.edu.handlers.ViewTransitionClickHandler;
import pl.lodz.p.edu.view.adapters.ViewAdapter;

public class TemplatesActivity extends AbstractActivity<ActivityTemplatesBinding> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.initBinding(R.layout.activity_templates);
        setHeaderTitle(R.string.templates_title);

        binding.setHandler(new ViewTransitionClickHandler(this, AddTemplateActivity.class));

        initTemplatesRecyclerView();
    }

    private void initTemplatesRecyclerView() {
        this.binding.templatesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        final Disposable subscription = PackAssistantDatabase
                .getInstance(this)
                .packingListDefinitionsDao()
                .getAll(true)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(createTemplatesObserver());
    }

    private Consumer<List<PackingListDefinition>> createTemplatesObserver() {
        return new Consumer<List<PackingListDefinition>>() {
            @Override
            public void accept(List<PackingListDefinition> data) {
                binding.templatesRecyclerView.setAdapter(new ViewAdapter<>(data, R.layout.single_template_layout));
            }
        };
    }
}
