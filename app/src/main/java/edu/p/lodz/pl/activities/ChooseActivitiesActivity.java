package edu.p.lodz.pl.activities;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import edu.p.lodz.pl.R;
import edu.p.lodz.pl.database.entity.ActivityEnum;
import edu.p.lodz.pl.databinding.ActivityChooseActivitiesBinding;
import edu.p.lodz.pl.handlers.ChooseActivitiesButtonHandler;
import edu.p.lodz.pl.model.ActivitySelectedDataModel;
import edu.p.lodz.pl.view.ViewAdapter;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ChooseActivitiesActivity extends AppCompatActivity {

    private static final String TAG = "ChooseActivitiesActivit";

    private ActivityChooseActivitiesBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_choose_activities);
        initActivitiesRecyclerView();

        getSupportActionBar().setTitle("Choose activities");
    }

    private void initActivitiesRecyclerView() {
        this.binding.activitiesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        final Disposable subscription = Flowable
                .just(ActivityEnum.values())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(createActivitiesObserver());
    }

    private Consumer<? super ActivityEnum[]> createActivitiesObserver() {
        return new Consumer<ActivityEnum[]>() {
            @Override
            public void accept(ActivityEnum[] activities) {
                final List<ActivitySelectedDataModel> models = createSelectionModel(activities);
                binding.activitiesRecyclerView.setAdapter(new ViewAdapter<>(models, R.layout.single_activity_layout));
                binding.setHandler(new ChooseActivitiesButtonHandler(models));
            }
        };
    }

    private List<ActivitySelectedDataModel> createSelectionModel(ActivityEnum[] enums) {
        final List<ActivitySelectedDataModel> result = new ArrayList<>();
        for (ActivityEnum activity : enums) {
            result.add(new ActivitySelectedDataModel(activity, false));
        }
        return result;
    }
}
