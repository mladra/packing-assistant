package pl.lodz.p.edu.view.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import pl.lodz.p.edu.R;
import pl.lodz.p.edu.databinding.SingleTemplateSectionLayoutBinding;
import pl.lodz.p.edu.view.holders.TemplateSectionViewHolder;
import pl.lodz.p.edu.view.model.TemplateSection;

public class TemplateSectionViewAdapter extends RecyclerView.Adapter<TemplateSectionViewHolder> {

    private List<TemplateSection> objects;
    private LayoutInflater inflater;
    private int layoutId;
    private Activity ctx;

    public TemplateSectionViewAdapter(List<TemplateSection> objects, Activity ctx) {
        this.objects = objects;
        this.layoutId = R.layout.single_template_section_layout;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public TemplateSectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            this.inflater = LayoutInflater.from(parent.getContext());
        }

        final SingleTemplateSectionLayoutBinding binding = DataBindingUtil.inflate(this.inflater, this.layoutId, parent, false);
        return new TemplateSectionViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TemplateSectionViewHolder holder, int position) {
        final TemplateSection section = objects.get(position);
        holder.bind(section, ctx);
    }

    @Override
    public int getItemCount() {
        return objects != null ? objects.size() : 0;
    }
}
