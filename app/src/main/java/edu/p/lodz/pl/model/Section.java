package edu.p.lodz.pl.model;

import java.util.List;

import edu.p.lodz.pl.database.entity.definitions.SectionDefinition;
import edu.p.lodz.pl.database.entity.instances.SectionInstance;

public class Section {

    private SectionDefinition definition;
    private SectionInstance instance;
    private List<Item> items;

    public Section(SectionDefinition definition, SectionInstance instance, List<Item> items) {
        this.definition = definition;
        this.instance = instance;
        this.items = items;
    }

    public SectionDefinition getDefinition() {
        return definition;
    }

    public void setDefinition(SectionDefinition definition) {
        this.definition = definition;
    }

    public SectionInstance getInstance() {
        return instance;
    }

    public void setInstance(SectionInstance instance) {
        this.instance = instance;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
