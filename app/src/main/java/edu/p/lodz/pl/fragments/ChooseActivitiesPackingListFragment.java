package edu.p.lodz.pl.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import edu.p.lodz.pl.R;
import edu.p.lodz.pl.database.entity.ActivityEnum;
import edu.p.lodz.pl.databinding.FragmentChooseActivitiesPackingListBinding;
import edu.p.lodz.pl.handlers.ChooseActivitiesButtonHandler;
import edu.p.lodz.pl.model.ActivitySelectedDataModel;
import edu.p.lodz.pl.view.ViewAdapter;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ChooseActivitiesPackingListFragment extends Fragment {

    private FragmentChooseActivitiesPackingListBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        this.binding = DataBindingUtil.inflate(inflater, R.layout.fragment_choose_activities_packing_list, container, false);
        initActivitiesRecyclerView();
        return this.binding.getRoot();
    }

    @SuppressWarnings("CheckResult")
    private void initActivitiesRecyclerView() {
        this.binding.activitiesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Flowable
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
                binding.setHandler(new ChooseActivitiesButtonHandler(getActivity(), models));
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
