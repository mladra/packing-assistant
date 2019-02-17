package pl.lodz.p.edu.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import pl.lodz.p.edu.R;
import pl.lodz.p.edu.activities.AddPackingListActivity;
import pl.lodz.p.edu.converters.PackingListDefinitionConverter;
import pl.lodz.p.edu.database.PackAssistantDatabase;
import pl.lodz.p.edu.database.entity.definitions.PackingListDefinition;
import pl.lodz.p.edu.databinding.FragmentBasicDataPackingListBinding;
import pl.lodz.p.edu.handlers.AddPackingListClickHandler;

public class BasicDataPackingListFragment extends Fragment {

    private FragmentBasicDataPackingListBinding binding;
    private AddPackingListActivity parent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = DataBindingUtil.inflate(inflater, R.layout.fragment_basic_data_packing_list, container, false);
        fillTemplateAutocomplete();
        this.binding.setHandler(new AddPackingListClickHandler(getActivity(), parent));
        this.binding.setParams(this.parent.getCreationParameters());
        this.binding.setCheckedListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                binding.getParams().setFromTemplate(isChecked);
                binding.templateAutocompleteTv.setVisibility(isChecked ? View.VISIBLE : View.INVISIBLE);
            }
        });
        return this.binding.getRoot();
    }

    private void fillTemplateAutocomplete() {
        final PackAssistantDatabase db = PackAssistantDatabase.getInstance(parent);
        db.packingListDefinitionsDao().getAll(true)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(createTemplatesObserver());
    }

    private Consumer<? super List<PackingListDefinition>> createTemplatesObserver() {
        return new Consumer<List<PackingListDefinition>>() {
            @Override
            public void accept(List<PackingListDefinition> packingListDefinitions) throws Exception {
                if (packingListDefinitions != null && !packingListDefinitions.isEmpty()) {
                    binding.setConverter(new PackingListDefinitionConverter(packingListDefinitions));
                    binding.templateAutocompleteTv.setAdapter(new ArrayAdapter<>(parent, android.R.layout.simple_dropdown_item_1line, packingListDefinitions));
                }
            }
        };
    }

    public void setParent(AddPackingListActivity parent) {
        this.parent = parent;
    }

}
