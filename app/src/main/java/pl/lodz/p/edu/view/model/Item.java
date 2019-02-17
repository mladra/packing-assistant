package pl.lodz.p.edu.view.model;

import pl.lodz.p.edu.database.entity.definitions.ItemDefinition;
import pl.lodz.p.edu.database.entity.instances.ItemInstance;
import pl.lodz.p.edu.database.entity.instances.SectionItemInstance;

public class Item {

    private ItemDefinition definition;
    private ItemInstance instance;
    private SectionItemInstance sectionItemInstance;

    public Item(ItemDefinition definition, ItemInstance instance, SectionItemInstance sectionItemInstance) {
        this.definition = definition;
        this.instance = instance;
        this.sectionItemInstance = sectionItemInstance;
    }

    public ItemDefinition getDefinition() {
        return definition;
    }

    public void setDefinition(ItemDefinition definition) {
        this.definition = definition;
    }

    public ItemInstance getInstance() {
        return instance;
    }

    public void setInstance(ItemInstance instance) {
        this.instance = instance;
    }

    public SectionItemInstance getSectionItemInstance() {
        return sectionItemInstance;
    }

    public void setSectionItemInstance(SectionItemInstance sectionItemInstance) {
        this.sectionItemInstance = sectionItemInstance;
    }
}
