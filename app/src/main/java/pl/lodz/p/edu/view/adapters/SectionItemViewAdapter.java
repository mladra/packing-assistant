package pl.lodz.p.edu.view.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import pl.lodz.p.edu.database.entity.StatusEnum;
import pl.lodz.p.edu.databinding.SingleItemSelectedLayoutBinding;
import pl.lodz.p.edu.fragments.CreatedPackingListFragment;
import pl.lodz.p.edu.view.holders.SectionItemViewHolder;
import pl.lodz.p.edu.view.model.Item;

public class SectionItemViewAdapter extends RecyclerView.Adapter<SectionItemViewHolder> {

    private List<Item> items;
    private StatusEnum status;
    private LayoutInflater inflater;
    private int layoutId;
    private CreatedPackingListFragment ctx;

    public SectionItemViewAdapter(List<Item> items, StatusEnum status, int layoutId, CreatedPackingListFragment ctx) {
        this.items = items;
        this.status = status;
        this.layoutId = layoutId;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public SectionItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (this.inflater == null) {
            this.inflater = LayoutInflater.from(parent.getContext());
        }

        final SingleItemSelectedLayoutBinding binding = DataBindingUtil.inflate(this.inflater, this.layoutId, parent, false);
        return new SectionItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SectionItemViewHolder holder, int position) {
        final Item item = items.get(position);
        holder.bind(item, status, ctx);
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }
}
