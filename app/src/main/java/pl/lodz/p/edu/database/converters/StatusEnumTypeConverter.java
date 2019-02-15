package pl.lodz.p.edu.database.converters;

import androidx.room.TypeConverter;
import pl.lodz.p.edu.database.entity.StatusEnum;

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
