package pl.lodz.p.edu.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import pl.lodz.p.edu.R;
import pl.lodz.p.edu.activities.AddPackingListActivity;
import pl.lodz.p.edu.databinding.FragmentBasicDataPackingListBinding;
import pl.lodz.p.edu.handlers.AddPackingListClickHandler;
import pl.lodz.p.edu.handlers.DateFromOnFocusChangeListener;

public class BasicDataPackingListFragment extends Fragment {

    private FragmentBasicDataPackingListBinding binding;
    private AddPackingListActivity parent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = DataBindingUtil.inflate(inflater, R.layout.fragment_basic_data_packing_list, container, false);
        this.binding.setHandler(new AddPackingListClickHandler(getActivity(), parent));
        this.binding.setParams(this.parent.getCreationParameters());
        this.binding.dateFromEditText.setOnFocusChangeListener(new DateFromOnFocusChangeListener(parent));
        this.binding.dateToEditText.setOnFocusChangeListener(new DateFromOnFocusChangeListener(parent));
        return this.binding.getRoot();
    }

    public void setParent(AddPackingListActivity parent) {
        this.parent = parent;
    }

}
