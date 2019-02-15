package pl.lodz.p.edu.view.holders;

import android.content.Context;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import pl.lodz.p.edu.BR;
import pl.lodz.p.edu.R;
import pl.lodz.p.edu.databinding.SingleSectionLayoutBinding;
import pl.lodz.p.edu.view.adapters.ItemViewAdapter;
import pl.lodz.p.edu.view.model.Section;

public class SectionViewHolder extends RecyclerView.ViewHolder {

    private SingleSectionLayoutBinding binding;

    public SectionViewHolder(SingleSectionLayoutBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Section section, Context ctx) {
        this.binding.setVariable(BR.instance, section.getInstance());
        this.binding.setVariable(BR.definition, section.getDefinition());
        this.binding.itemsRecyclerView.setLayoutManager(new LinearLayoutManager(ctx));
        this.binding.itemsRecyclerView.setAdapter(new ItemViewAdapter(section.getItems(), R.layout.single_item_selected_layout));
    }

    public ViewDataBinding getBinding() {
        return binding;
    }
}
