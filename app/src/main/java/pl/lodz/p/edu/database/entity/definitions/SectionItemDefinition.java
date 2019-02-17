package pl.lodz.p.edu.database.entity.definitions;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "section_item_definitions",
        primaryKeys = {"section_id", "item_id"},
        foreignKeys = {
                @ForeignKey(entity = SectionDefinition.class,
                        parentColumns = "id",
                        childColumns = "section_id"),
                @ForeignKey(entity = ItemDefinition.class,
                        parentColumns = "id",
                        childColumns = "item_id")
        })
public class SectionItemDefinition {

    @ColumnInfo(name = "section_id")
    private final long sectionId;

    @ColumnInfo(name = "item_id")
    private final long itemId;

    @ColumnInfo(name = "is_required")
    private final boolean required;

    public SectionItemDefinition(long sectionId, long itemId, boolean required) {
        this.sectionId = sectionId;
        this.itemId = itemId;
        this.required = required;
    }

    public long getSectionId() {
        return sectionId;
    }

    public long getItemId() {
        return itemId;
    }

    public boolean isRequired() {
        return required;
    }

    public static SectionItemDefinition[] populateData() {
        return new SectionItemDefinition[]{
                new SectionItemDefinition(1, 1, true),
                new SectionItemDefinition(1, 2, true),
                new SectionItemDefinition(1, 3, true),
                new SectionItemDefinition(3, 5, true),
                new SectionItemDefinition(4, 6, true),
                new SectionItemDefinition(5, 7, true),
                new SectionItemDefinition(7, 4, true),
                new SectionItemDefinition(11, 8, true),
                new SectionItemDefinition(2, 9, false),
                new SectionItemDefinition(2, 10, false),
                new SectionItemDefinition(2, 11, false),
                new SectionItemDefinition(2, 12, false),
                new SectionItemDefinition(2, 13, false),
                new SectionItemDefinition(2, 14, false),
                new SectionItemDefinition(2, 15, false),
                new SectionItemDefinition(2, 16, false),
                new SectionItemDefinition(2, 17, false),
                new SectionItemDefinition(2, 18, false),
        };
    }
}
