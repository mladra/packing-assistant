package edu.p.lodz.pl.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "section_item",
        primaryKeys = {"item_id", "section_id"},
        foreignKeys = {
                @ForeignKey(entity = Section.class,
                        parentColumns = "id",
                        childColumns = "section_id"),
                @ForeignKey(entity = Item.class,
                        parentColumns = "id",
                        childColumns = "item_id")
        })
public class SectionItem {

    @ColumnInfo(name = "section_id")
    private final int sectionId;

    @ColumnInfo(name = "item_id")
    private final int item_id;

    @ColumnInfo(name = "is_required")
    private final boolean required;

    public SectionItem(int sectionId, int item_id, boolean required) {
        this.sectionId = sectionId;
        this.item_id = item_id;
        this.required = required;
    }

    public int getSectionId() {
        return sectionId;
    }

    public int getItem_id() {
        return item_id;
    }

    public boolean isRequired() {
        return required;
    }

    public static SectionItem[] populateData() {
        //TODO: mladra: populate data
        return new SectionItem[]{};
    }
}
