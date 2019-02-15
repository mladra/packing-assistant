package pl.lodz.p.edu.view.model;

import java.util.ArrayList;
import java.util.List;

public class TemplateSection {

    private static int GLOBAL_ID = 0;

    private int id;
    private String name;
    private List<TemplateSectionItem> items;

    public TemplateSection() {
        this.id = GLOBAL_ID++;
        this.items = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TemplateSectionItem> getItems() {
        return items;
    }

    public void setItems(List<TemplateSectionItem> items) {
        this.items = items;
    }
}
