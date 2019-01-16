package edu.p.lodz.pl.model;

import edu.p.lodz.pl.database.entity.definitions.ItemDefinition;
import edu.p.lodz.pl.database.entity.instances.ItemInstance;

public class Item {

    private ItemDefinition definition;
    private ItemInstance instance;

    public Item(ItemDefinition definition, ItemInstance instance) {
        this.definition = definition;
        this.instance = instance;
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
}
