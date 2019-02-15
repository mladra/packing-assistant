package pl.lodz.p.edu.converters;

import androidx.databinding.InverseMethod;
import pl.lodz.p.edu.database.entity.WeatherEnum;

public class WeatherConverter {

    private static final String EMPTY = "";

    @InverseMethod("toObject")
    public static String toString(WeatherEnum value) {
        return value == null ? EMPTY : value.getDisplayName();
    }

    public static WeatherEnum toObject(String value) {
        return WeatherEnum.getByDisplayName(value);
    }
}
