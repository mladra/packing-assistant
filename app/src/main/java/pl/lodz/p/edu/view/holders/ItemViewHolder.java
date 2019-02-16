package pl.lodz.p.edu.view.holders;

import android.widget.CompoundButton;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import pl.lodz.p.edu.BR;
import pl.lodz.p.edu.database.entity.StatusEnum;
import pl.lodz.p.edu.fragments.CreatedPackingListFragment;
import pl.lodz.p.edu.view.model.Item;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    private ViewDataBinding binding;

    public ItemViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(final Item item, StatusEnum status, final CreatedPackingListFragment ctx) {
        this.binding.setVariable(BR.instance, item.getInstance());
        this.binding.setVariable(BR.definition, item.getDefinition());
        this.binding.setVariable(BR.listStatus, status);
        this.binding.setVariable(BR.checkedListener, new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.getInstance().setSelected(isChecked);
                ctx.refreshWeight();
            }
        });
    }

    public ViewDataBinding getBinding() {
        return binding;
    }
}
