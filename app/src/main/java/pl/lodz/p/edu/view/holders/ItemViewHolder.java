package pl.lodz.p.edu.view.holders;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import pl.lodz.p.edu.database.entity.definitions.ItemDefinition;
import pl.lodz.p.edu.databinding.SingleItemLayoutBinding;
import pl.lodz.p.edu.handlers.AddEditItemClickHandler;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    private SingleItemLayoutBinding binding;
    private Activity activity;

    public ItemViewHolder(@NonNull SingleItemLayoutBinding binding, Activity activity) {
        super(binding.getRoot());
        this.binding = binding;
        this.activity = activity;
    }

    public void bind(final ItemDefinition itemDefinition) {
        this.binding.setObj(itemDefinition);
        this.binding.setHandler(new AddEditItemClickHandler(activity, itemDefinition));
    }

    public ViewDataBinding getBinding() {
        return binding;
    }
}
