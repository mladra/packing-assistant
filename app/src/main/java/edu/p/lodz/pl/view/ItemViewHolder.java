package edu.p.lodz.pl.view;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import edu.p.lodz.pl.BR;
import edu.p.lodz.pl.model.Item;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    private ViewDataBinding binding;

    public ItemViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Item item) {
        this.binding.setVariable(BR.instance, item.getInstance());
        this.binding.setVariable(BR.definition, item.getDefinition());
    }

    public ViewDataBinding getBinding() {
        return binding;
    }
}
