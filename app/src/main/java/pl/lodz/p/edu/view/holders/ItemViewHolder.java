package pl.lodz.p.edu.view.holders;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.CompoundButton;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import pl.lodz.p.edu.BR;
import pl.lodz.p.edu.R;
import pl.lodz.p.edu.database.entity.StatusEnum;
import pl.lodz.p.edu.databinding.SingleItemSelectedLayoutBinding;
import pl.lodz.p.edu.fragments.CreatedPackingListFragment;
import pl.lodz.p.edu.view.model.Item;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    private SingleItemSelectedLayoutBinding binding;

    public ItemViewHolder(SingleItemSelectedLayoutBinding binding) {
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
        if (item.getSectionItemInstance().isRequired()) {
            final String current = item.getDefinition().getName();
            final String asterix = " \u002A";
            final SpannableStringBuilder builder = new SpannableStringBuilder(current + asterix);
            builder.setSpan(new ForegroundColorSpan(Color.RED), current.length(), builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            this.binding.singleItemNameTv.setText(builder);
        } else {
            this.binding.singleItemNameTv.setText(item.getDefinition().getName());
        }
    }

    public ViewDataBinding getBinding() {
        return binding;
    }
}
