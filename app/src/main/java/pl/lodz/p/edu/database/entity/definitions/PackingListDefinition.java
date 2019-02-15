package pl.lodz.p.edu.database.entity.definitions;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "packing_list_definitions")
public class PackingListDefinition extends BaseEntity {

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "is_template")
    private boolean template;

    public PackingListDefinition(String name, boolean template) {
        this.name = name;
        this.template = template;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTemplate() {
        return template;
    }

    public void setTemplate(boolean template) {
        this.template = template;
    }

    public static PackingListDefinition[] populateData() {
        return new PackingListDefinition[]{};
    }
}
