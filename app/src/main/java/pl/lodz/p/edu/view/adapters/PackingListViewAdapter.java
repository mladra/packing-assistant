package pl.lodz.p.edu.view.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import pl.lodz.p.edu.database.entity.instances.PackingListInstance;
import pl.lodz.p.edu.databinding.SinglePackingListLayoutBinding;
import pl.lodz.p.edu.view.holders.PackingListViewHolder;

public class PackingListViewAdapter extends RecyclerView.Adapter<PackingListViewHolder> {

    private List<PackingListInstance> lists;
    private LayoutInflater inflater;
    private int layoutId;

    public PackingListViewAdapter(List<PackingListInstance> lists, int layoutId) {
        this.lists = lists;
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public PackingListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (this.inflater == null) {
            this.inflater = LayoutInflater.from(parent.getContext());
        }

        final SinglePackingListLayoutBinding binding = DataBindingUtil.inflate(this.inflater, this.layoutId, parent, false);
        return new PackingListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PackingListViewHolder holder, int position) {
        final PackingListInstance instance = lists.get(position);
        holder.bind(instance);
    }

    @Override
    public int getItemCount() {
        return lists != null ? lists.size() : 0;
    }
}
