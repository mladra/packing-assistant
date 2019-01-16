package edu.p.lodz.pl.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class ViewAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    private List<T> objects;
    private LayoutInflater inflater;
    private int layoutId;

    public ViewAdapter(List<T> objects, int layoutId) {
        this.objects = objects;
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (this.inflater == null) {
            this.inflater = LayoutInflater.from(parent.getContext());
        }

        final ViewDataBinding binding = DataBindingUtil.inflate(this.inflater, this.layoutId, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final T obj = objects.get(position);
        holder.bind(obj);
    }

    @Override
    public int getItemCount() {
        return objects != null ? objects.size() : 0;
    }
}
