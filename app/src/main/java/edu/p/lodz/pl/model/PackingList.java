package edu.p.lodz.pl.model;

import java.util.List;

import edu.p.lodz.pl.database.entity.definitions.PackingListDefinition;
import edu.p.lodz.pl.database.entity.instances.PackingListInstance;

public class PackingList {

    private PackingListInstance instance;
    private PackingListDefinition definition;
    private List<Section> sections;

    public PackingList(PackingListInstance instance, PackingListDefinition definition, List<Section> sections) {
        this.instance = instance;
        this.definition = definition;
        this.sections = sections;
    }

    public PackingListInstance getInstance() {
        return instance;
    }

    public void setInstance(PackingListInstance instance) {
        this.instance = instance;
    }

    public PackingListDefinition getDefinition() {
        return definition;
    }

    public void setDefinition(PackingListDefinition definition) {
        this.definition = definition;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}
