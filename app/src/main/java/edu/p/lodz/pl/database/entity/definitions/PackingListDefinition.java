package edu.p.lodz.pl.database.entity.definitions;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "packing_list_definitions")
public class PackingListDefinition extends BaseEntity {

    @ColumnInfo(name = "name")
    private String name;

    public PackingListDefinition(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static PackingListDefinition[] populateData() {
        return new PackingListDefinition[]{};
    }
}
