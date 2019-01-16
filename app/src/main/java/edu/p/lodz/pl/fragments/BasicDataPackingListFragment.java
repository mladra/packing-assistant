package edu.p.lodz.pl.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import edu.p.lodz.pl.R;
import edu.p.lodz.pl.databinding.FragmentBasicDataPackingListBinding;
import edu.p.lodz.pl.handlers.AddPackingListClickHandler;

public class BasicDataPackingListFragment extends Fragment {

    private FragmentBasicDataPackingListBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = DataBindingUtil.inflate(inflater, R.layout.fragment_basic_data_packing_list, container, false);
        this.binding.setHandler(new AddPackingListClickHandler(getActivity()));
        //TODO: mladra: Add parameters from basic parameters view to the object
        return this.binding.getRoot();
    }
}
