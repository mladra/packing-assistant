package edu.p.lodz.pl.database.converters;

import androidx.room.TypeConverter;
import edu.p.lodz.pl.database.entity.WeatherEnum;

public class WeatherEnumTypeConverter {

    @TypeConverter
    public static String fromEnum(WeatherEnum e) {
        return e == null ? null : e.name();
    }

    @TypeConverter
    public static WeatherEnum toEnum(String name) {
        return name == null ? null : WeatherEnum.valueOf(name);
    }

}
