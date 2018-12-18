package edu.p.lodz.pl.database.converters;

import androidx.room.TypeConverter;
import edu.p.lodz.pl.database.entity.StatusEnum;

public class StatusEnumTypeConverter {

    @TypeConverter
    public static String fromEnum(StatusEnum e) {
        return e == null ? null : e.name();
    }

    @TypeConverter
    public static StatusEnum toEnum(String name) {
        return name == null ? null : StatusEnum.valueOf(name);
    }

}
