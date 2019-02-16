package pl.lodz.p.edu.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import pl.lodz.p.edu.R;
import pl.lodz.p.edu.database.entity.StatusEnum;
import pl.lodz.p.edu.databinding.SingleSectionLayoutBinding;
import pl.lodz.p.edu.view.holders.SectionViewHolder;
import pl.lodz.p.edu.view.model.Section;

public class SectionViewAdapter extends RecyclerView.Adapter<SectionViewHolder> {

    private List<Section> objects;
    private StatusEnum packingListStatus;
    private LayoutInflater inflater;
    private int layoutId;
    private Context ctx;

    public SectionViewAdapter(List<Section> objects, StatusEnum packingListStatus, Context ctx) {
        this.objects = objects;
        this.layoutId = R.layout.single_section_layout;
        this.ctx = ctx;
        this.packingListStatus = packingListStatus;
    }

    @NonNull
    @Override
    public SectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (this.inflater == null) {
            this.inflater = LayoutInflater.from(parent.getContext());
        }

        final SingleSectionLayoutBinding binding = DataBindingUtil.inflate(this.inflater, this.layoutId, parent, false);
        return new SectionViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SectionViewHolder holder, int position) {
        final Section section = objects.get(position);
        holder.bind(section, packingListStatus, ctx);
    }

    @Override
    public int getItemCount() {
        return objects != null ? objects.size() : 0;
    }
}
