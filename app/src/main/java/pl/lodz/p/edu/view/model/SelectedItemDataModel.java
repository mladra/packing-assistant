package pl.lodz.p.edu.view.model;

import pl.lodz.p.edu.database.entity.definitions.ItemDefinition;

public class SelectedItemDataModel {

    private ItemDefinition itemDefinition;
    private boolean selected;

    public SelectedItemDataModel(ItemDefinition itemDefinition) {
        this.itemDefinition = itemDefinition;
        this.selected = false;
    }

    public ItemDefinition getItemDefinition() {
        return itemDefinition;
    }

    public void setItemDefinition(ItemDefinition itemDefinition) {
        this.itemDefinition = itemDefinition;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
