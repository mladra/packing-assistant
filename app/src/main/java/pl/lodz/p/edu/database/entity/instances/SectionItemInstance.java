package pl.lodz.p.edu.database.entity.instances;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "section_item_instances",
        primaryKeys = {"section_id", "item_id"},
        foreignKeys = {
                @ForeignKey(entity = SectionInstance.class,
                        parentColumns = "id",
                        childColumns = "section_id"),
                @ForeignKey(entity = ItemInstance.class,
                        parentColumns = "id",
                        childColumns = "item_id")
        })
public class SectionItemInstance {

    @ColumnInfo(name = "section_id")
    private long sectionInstanceId;

    @ColumnInfo(name = "item_id")
    private long itemInstanceId;

    @ColumnInfo(name = "is_required")
    private boolean required;

    public SectionItemInstance(long sectionInstanceId, long itemInstanceId, boolean required) {
        this.sectionInstanceId = sectionInstanceId;
        this.itemInstanceId = itemInstanceId;
        this.required = required;
    }

    public long getSectionInstanceId() {
        return sectionInstanceId;
    }

    public void setSectionInstanceId(long sectionInstanceId) {
        this.sectionInstanceId = sectionInstanceId;
    }

    public long getItemInstanceId() {
        return itemInstanceId;
    }

    public void setItemInstanceId(long itemInstanceId) {
        this.itemInstanceId = itemInstanceId;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
}
