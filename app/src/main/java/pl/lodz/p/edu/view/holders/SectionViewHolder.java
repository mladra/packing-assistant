package pl.lodz.p.edu.view.holders;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import pl.lodz.p.edu.BR;
import pl.lodz.p.edu.R;
import pl.lodz.p.edu.database.entity.StatusEnum;
import pl.lodz.p.edu.databinding.SingleSectionLayoutBinding;
import pl.lodz.p.edu.fragments.CreatedPackingListFragment;
import pl.lodz.p.edu.view.adapters.SectionItemViewAdapter;
import pl.lodz.p.edu.view.model.Section;

public class SectionViewHolder extends RecyclerView.ViewHolder {

    private SingleSectionLayoutBinding binding;

    public SectionViewHolder(SingleSectionLayoutBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Section section, StatusEnum status, CreatedPackingListFragment ctx) {
        this.binding.setVariable(BR.instance, section.getInstance());
        this.binding.setVariable(BR.definition, section.getDefinition());
        this.binding.itemsRecyclerView.setLayoutManager(new LinearLayoutManager(ctx.getContext()));
        this.binding.itemsRecyclerView.setAdapter(new SectionItemViewAdapter(section.getItems(), status, R.layout.single_item_selected_layout, ctx));
    }

    public ViewDataBinding getBinding() {
        return binding;
    }
}
