package pl.lodz.p.edu.database.entity.definitions;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import pl.lodz.p.edu.database.entity.ActivityEnum;

@Entity(tableName = "section_definitions")
public class SectionDefinition extends BaseEntity {

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "required")
    private boolean required;

    public SectionDefinition(String name, boolean required) {
        this.name = name;
        this.required = required;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static SectionDefinition[] populateData() {
        return new SectionDefinition[] {
                new SectionDefinition("General", true),
                new SectionDefinition("Other", false),
                new SectionDefinition(ActivityEnum.JOGGING.getName(), false),
                new SectionDefinition(ActivityEnum.PLAYING_BASKETBALL.getName(), false),
                new SectionDefinition(ActivityEnum.PLAYING_FOOTBALL.getName(), false),
                new SectionDefinition(ActivityEnum.PLAYING_GOLF.getName(), false),
                new SectionDefinition(ActivityEnum.RIDING_A_BIKE.getName(), false),
                new SectionDefinition(ActivityEnum.SKATING.getName(), false),
                new SectionDefinition(ActivityEnum.SKIING.getName(), false),
                new SectionDefinition(ActivityEnum.SNOWBOARDING.getName(), false),
                new SectionDefinition(ActivityEnum.SWIMMING.getName(), false)
        };
    }
}
