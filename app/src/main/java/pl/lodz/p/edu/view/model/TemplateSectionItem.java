package pl.lodz.p.edu.view.model;

public class TemplateSectionItem {

    private String name;
    private boolean required;

    public TemplateSectionItem(String name, boolean required) {
        this.name = name;
        this.required = required;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
}
