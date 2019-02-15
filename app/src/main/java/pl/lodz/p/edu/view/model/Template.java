package pl.lodz.p.edu.view.model;

import java.util.ArrayList;
import java.util.List;

public class Template {

    private String name;
    private List<TemplateSection> sections;

    public Template() {
        this.sections = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TemplateSection> getSections() {
        return sections;
    }

    public void setSections(List<TemplateSection> sections) {
        this.sections = sections;
    }
}
