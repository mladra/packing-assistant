package pl.lodz.p.edu.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import pl.lodz.p.edu.R;
import pl.lodz.p.edu.activities.extras.ExtrasCodesEnum;
import pl.lodz.p.edu.activities.extras.RequestCodesEnum;
import pl.lodz.p.edu.activities.extras.ResultCodesEnum;
import pl.lodz.p.edu.database.entity.definitions.ItemDefinition;
import pl.lodz.p.edu.databinding.ActivityAddTemplateBinding;
import pl.lodz.p.edu.handlers.ClickHandler;
import pl.lodz.p.edu.view.adapters.TemplateSectionViewAdapter;
import pl.lodz.p.edu.view.model.Template;
import pl.lodz.p.edu.view.model.TemplateSection;
import pl.lodz.p.edu.view.model.TemplateSectionItem;

public class AddTemplateActivity extends AbstractActivity<ActivityAddTemplateBinding> {

    private Template template = new Template();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.initBinding(R.layout.activity_add_template);
        setHeaderTitle(R.string.add_template_title);

        binding.templateSectionsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.setTemplate(template);
        binding.setHandler(new ClickHandler() {
            @Override
            public void onClick() {
                template.getSections().add(new TemplateSection());
                binding.templateSectionsRecyclerView.setAdapter(new TemplateSectionViewAdapter(template.getSections(), AddTemplateActivity.this));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_template_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_template_button:
                saveTemplate();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveTemplate() {
        //TODO: mladra: Save packing list template
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != ResultCodesEnum.OK.getNum()) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }

        if (requestCode == RequestCodesEnum.ITEMS.getNum()) {
            assignItemsToSection(data);
        }
    }

    @SuppressWarnings("unchecked")
    private void assignItemsToSection(final Intent data) {
        List<ItemDefinition> items = (List<ItemDefinition>) data.getSerializableExtra(ExtrasCodesEnum.ITEMS.name());
        int sectionId = data.getIntExtra(ExtrasCodesEnum.SECTION_ID.name(), -1);

        if (sectionId >= 0 && items != null && items.size() > 0) {
            TemplateSection section = getSectionById(sectionId);
            if (section == null) {
                return;
            }

            section.getItems().addAll(mapToTemplateSectionItem(items));
            binding.templateSectionsRecyclerView.setAdapter(new TemplateSectionViewAdapter(template.getSections(), AddTemplateActivity.this));
            binding.getRoot().invalidate();
        }
    }

    private TemplateSection getSectionById(int id) {
        if (id >= 0 && template.getSections() != null && template.getSections().size() > 0) {
            for (TemplateSection section : template.getSections()) {
                if (section.getId() == id) {
                    return section;
                }
            }
        }

        return null;
    }

    private List<TemplateSectionItem> mapToTemplateSectionItem(List<ItemDefinition> items) {
        List<TemplateSectionItem> result = new ArrayList<>();
        if (items != null && items.size() > 0) {
            for (ItemDefinition definition : items) {
                result.add(new TemplateSectionItem(definition.getName()));
            }
        }
        return result;
    }
}
