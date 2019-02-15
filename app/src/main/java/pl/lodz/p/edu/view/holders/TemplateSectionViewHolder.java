package pl.lodz.p.edu.view.holders;

import android.app.Activity;
import android.content.Intent;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import pl.lodz.p.edu.R;
import pl.lodz.p.edu.activities.ItemsActivity;
import pl.lodz.p.edu.activities.extras.ExtrasCodesEnum;
import pl.lodz.p.edu.activities.extras.RequestCodesEnum;
import pl.lodz.p.edu.databinding.SingleTemplateSectionLayoutBinding;
import pl.lodz.p.edu.handlers.ClickHandler;
import pl.lodz.p.edu.view.adapters.TemplateSectionItemAdapter;
import pl.lodz.p.edu.view.model.TemplateSection;

public class TemplateSectionViewHolder extends RecyclerView.ViewHolder {

    private SingleTemplateSectionLayoutBinding binding;

    public TemplateSectionViewHolder(SingleTemplateSectionLayoutBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(final TemplateSection section, final Activity ctx) {
        this.binding.setSection(section);
        this.binding.setHandler(new ClickHandler() {
            @Override
            public void onClick() {
                final Intent intent = new Intent(ctx, ItemsActivity.class);
                intent.putExtra(ExtrasCodesEnum.SELECTION_MODE.name(), true);
                intent.putExtra(ExtrasCodesEnum.SECTION_ID.name(), section.getId());
                ctx.startActivityForResult(intent, RequestCodesEnum.ITEMS.getNum());
            }
        });
        this.binding.templateSectionItemsRecyclerView.setLayoutManager(new LinearLayoutManager(ctx));
        this.binding.templateSectionItemsRecyclerView.setAdapter(new TemplateSectionItemAdapter(section.getItems(), R.layout.single_template_item_layout));
    }

    public ViewDataBinding getBinding() {
        return binding;
    }
}
