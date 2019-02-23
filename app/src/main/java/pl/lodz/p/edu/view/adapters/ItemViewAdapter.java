package pl.lodz.p.edu.view.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import pl.lodz.p.edu.database.entity.definitions.ItemDefinition;
import pl.lodz.p.edu.databinding.SingleItemLayoutBinding;
import pl.lodz.p.edu.view.holders.ItemViewHolder;

public class ItemViewAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private Activity activity;
    private List<ItemDefinition> items;
    private LayoutInflater inflater;
    private int layoutId;

    public ItemViewAdapter(List<ItemDefinition> items, int layoutId, Activity activity) {
        this.items = items;
        this.layoutId = layoutId;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            this.inflater = LayoutInflater.from(parent.getContext());
        }

        final SingleItemLayoutBinding binding = DataBindingUtil.inflate(this.inflater, this.layoutId, parent, false);
        return new ItemViewHolder(binding, activity);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        final ItemDefinition itemDefinition = items.get(position);
        holder.bind(itemDefinition);
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }
}
