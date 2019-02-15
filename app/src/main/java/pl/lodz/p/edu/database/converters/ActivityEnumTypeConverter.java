package pl.lodz.p.edu.database.converters;

import androidx.room.TypeConverter;
import pl.lodz.p.edu.database.entity.ActivityEnum;

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
