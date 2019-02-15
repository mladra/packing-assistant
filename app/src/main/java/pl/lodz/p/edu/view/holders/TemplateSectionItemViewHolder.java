package pl.lodz.p.edu.view.holders;

import java.util.List;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import pl.lodz.p.edu.databinding.SingleTemplateItemLayoutBinding;
import pl.lodz.p.edu.handlers.ClickHandler;
import pl.lodz.p.edu.view.model.TemplateSectionItem;

public class TemplateSectionItemViewHolder extends RecyclerView.ViewHolder {

    private SingleTemplateItemLayoutBinding binding;

    public TemplateSectionItemViewHolder(SingleTemplateItemLayoutBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(TemplateSectionItem item, final ClickHandler handler) {
        this.binding.setItem(item);
        this.binding.setHandler(handler);
    }

    public ViewDataBinding getBinding() {
        return binding;
    }
}
