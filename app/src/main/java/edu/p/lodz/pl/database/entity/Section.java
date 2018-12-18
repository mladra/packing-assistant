package edu.p.lodz.pl.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sections")
public class Section {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "required")
    private boolean required;

    public Section(int id, String name, boolean required) {
        this.id = id;
        this.name = name;
        this.required = required;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public static Section[] populateData() {
        return new Section[] {
                new Section(1, "General", true),
                new Section(2, "Other", false),
                new Section(3, ActivityEnum.JOGGING.name(), false),
                new Section(4, ActivityEnum.PLAYING_BASKETBALL.name(), false),
                new Section(5, ActivityEnum.PLAYING_FOOTBALL.name(), false),
                new Section(6, ActivityEnum.PLAYING_GOLF.name(), false),
                new Section(7, ActivityEnum.RIDING_A_BIKE.name(), false),
                new Section(8, ActivityEnum.SKATING.name(), false),
                new Section(9, ActivityEnum.SKIING.name(), false),
                new Section(10, ActivityEnum.SNOWBOARDING.name(), false),
                new Section(11, ActivityEnum.SWIMMING.name(), false)
        };
    }
}
