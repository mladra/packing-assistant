package edu.p.lodz.pl.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "packing_list_section",
        primaryKeys = {"packing_list_id", "section_id"},
        foreignKeys = {
                @ForeignKey(entity = PackingList.class,
                        parentColumns = "id",
                        childColumns = "packing_list_id"),
                @ForeignKey(entity = Section.class,
                        parentColumns = "id",
                        childColumns = "section_id")
        })
public class PackingListSection {

    @ColumnInfo(name = "section_id")
    private final int sectionId;

    @ColumnInfo(name = "packing_list_id")
    private final int packingListId;

    @ColumnInfo(name = "is_required")
    private final boolean required;

    public PackingListSection(int sectionId, int packingListId, boolean required) {
        this.sectionId = sectionId;
        this.packingListId = packingListId;
        this.required = required;
    }

    public int getSectionId() {
        return sectionId;
    }

    public int getPackingListId() {
        return packingListId;
    }

    public boolean isRequired() {
        return required;
    }

    public static PackingListSection[] populateData() {
        //TODO: mladra: populate data
        return new PackingListSection[]{};
    }
}
