package edu.p.lodz.pl.database.entity.instances;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import edu.p.lodz.pl.database.entity.definitions.SectionDefinition;

@Entity(tableName = "packing_list_section_instances",
        primaryKeys = {"packing_list_id", "section_id"},
        foreignKeys = {
                @ForeignKey(entity = PackingListInstance.class,
                        parentColumns = "id",
                        childColumns = "packing_list_id"),
                @ForeignKey(entity = SectionInstance.class,
                        parentColumns = "id",
                        childColumns = "section_id")
        })
public class PackingListSectionInstance {

    @ColumnInfo(name = "packing_list_id")
    private long packingListInstanceId;

    @ColumnInfo(name = "section_id")
    private long sectionInstanceId;

    @ColumnInfo(name = "is_required")
    private boolean required;

    public PackingListSectionInstance(long packingListInstanceId, long sectionInstanceId, boolean required) {
        this.packingListInstanceId = packingListInstanceId;
        this.sectionInstanceId = sectionInstanceId;
        this.required = required;
    }

    public long getPackingListInstanceId() {
        return packingListInstanceId;
    }

    public void setPackingListInstanceId(long packingListInstanceId) {
        this.packingListInstanceId = packingListInstanceId;
    }

    public long getSectionInstanceId() {
        return sectionInstanceId;
    }

    public void setSectionInstanceId(long sectionInstanceId) {
        this.sectionInstanceId = sectionInstanceId;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
}
