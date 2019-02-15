package pl.lodz.p.edu.converters;

import androidx.databinding.InverseMethod;
import pl.lodz.p.edu.database.entity.ActivityEnum;

public class ActivityConverter {

    private static final String EMPTY = "";

    @InverseMethod("toObject")
    public static String toString(ActivityEnum value) {
        return value == null ? EMPTY : value.getName();
    }

    public static ActivityEnum toObject(String value) {
        return ActivityEnum.getByDisplayName(value);
    }
}
