package pl.lodz.p.edu.view.holders;

import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;
import pl.lodz.p.edu.activities.AddPackingListActivity;
import pl.lodz.p.edu.activities.extras.ExtrasCodesEnum;
import pl.lodz.p.edu.database.entity.StatusEnum;
import pl.lodz.p.edu.database.entity.instances.PackingListInstance;
import pl.lodz.p.edu.databinding.SinglePackingListLayoutBinding;
import pl.lodz.p.edu.handlers.ClickHandler;

public class PackingListViewHolder extends RecyclerView.ViewHolder {

    private SinglePackingListLayoutBinding binding;

    public PackingListViewHolder(SinglePackingListLayoutBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(final PackingListInstance instance) {
        this.binding.setObj(instance);
        this.binding.setHandler(new ClickHandler() {
            @Override
            public void onClick() {
                final Intent intent = new Intent(binding.getRoot().getContext(), AddPackingListActivity.class);
                intent.putExtra(ExtrasCodesEnum.PACKING_LIST_INSTANCE_ID.name(), instance.getId());
                binding.getRoot().getContext().startActivity(intent);
            }
        });
    }

    public SinglePackingListLayoutBinding getBinding() {
        return this.binding;
    }
}
