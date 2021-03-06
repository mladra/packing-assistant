package pl.lodz.p.edu.view.holders;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import pl.lodz.p.edu.BR;

public class ViewHolder extends RecyclerView.ViewHolder {

    protected ViewDataBinding binding;

    public ViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Object obj) {
        this.binding.setVariable(BR.obj, obj);
    }

    public ViewDataBinding getBinding() {
        return binding;
    }
}
