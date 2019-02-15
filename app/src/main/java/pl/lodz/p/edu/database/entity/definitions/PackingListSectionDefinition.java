package pl.lodz.p.edu.database.entity.definitions;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "packing_list_section_definitions",
        primaryKeys = {"packing_list_id", "section_id"},
        foreignKeys = {
                @ForeignKey(entity = PackingListDefinition.class,
                        parentColumns = "id",
                        childColumns = "packing_list_id"),
                @ForeignKey(entity = SectionDefinition.class,
                        parentColumns = "id",
                        childColumns = "section_id")
        })
public class PackingListSectionDefinition {

    @ColumnInfo(name = "section_id")
    private final long sectionId;

    @ColumnInfo(name = "packing_list_id")
    private final long packingListId;

    @ColumnInfo(name = "is_required")
    private final boolean required;

    public PackingListSectionDefinition(long sectionId, long packingListId, boolean required) {
        this.sectionId = sectionId;
        this.packingListId = packingListId;
        this.required = required;
    }

    public long getSectionId() {
        return sectionId;
    }

    public long getPackingListId() {
        return packingListId;
    }

    public boolean isRequired() {
        return required;
    }

    public static PackingListSectionDefinition[] populateData() {
        //TODO: mladra: populate data
        return new PackingListSectionDefinition[]{};
    }
}
