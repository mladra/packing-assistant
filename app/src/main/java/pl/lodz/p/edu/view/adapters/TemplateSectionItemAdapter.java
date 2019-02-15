package pl.lodz.p.edu.view.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import pl.lodz.p.edu.databinding.SingleTemplateItemLayoutBinding;
import pl.lodz.p.edu.handlers.ClickHandler;
import pl.lodz.p.edu.view.holders.TemplateSectionItemViewHolder;
import pl.lodz.p.edu.view.model.TemplateSectionItem;

public class TemplateSectionItemAdapter extends RecyclerView.Adapter<TemplateSectionItemViewHolder> {

    private List<TemplateSectionItem> objects;
    private int layoutId;
    private LayoutInflater inflater;

    public TemplateSectionItemAdapter(List<TemplateSectionItem> objects, int layoutId) {
        this.objects = objects;
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public TemplateSectionItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (this.inflater == null) {
            this.inflater = LayoutInflater.from(parent.getContext());
        }

        final SingleTemplateItemLayoutBinding binding = DataBindingUtil.inflate(this.inflater, this.layoutId, parent, false);
        return new TemplateSectionItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TemplateSectionItemViewHolder holder, final int position) {
        final TemplateSectionItem item = objects.get(position);
        holder.bind(item, new ClickHandler() {
            @Override
            public void onClick() {
                objects.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, objects.size());
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return objects != null ? objects.size() : 0;
    }
}
