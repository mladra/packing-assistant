package edu.p.lodz.pl.database.converters;

import androidx.room.TypeConverter;
import edu.p.lodz.pl.database.entity.ActivityEnum;

public class ActivityEnumTypeConverter {

    @TypeConverter
    public static String fromEnum(ActivityEnum e) {
        return e == null ? null : e.name();
    }

    @TypeConverter
    public static ActivityEnum toEnum(String name) {
        return name == null ? null : ActivityEnum.valueOf(name);
    }

}
