package pl.lodz.p.edu.database.converters;

import java.util.Date;

import androidx.room.TypeConverter;

public class DateTypeConverter {

    @TypeConverter
    public static Long fromDate(Date date) {
        if (date == null) {
            return null;
        }

        return date.getTime();
    }

    @TypeConverter
    public static Date toDate(Long millis) {
        if (millis == null) {
            return null;
        }

        return new Date(millis);
    }

}
