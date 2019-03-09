package pl.lodz.p.edu.converters;

import androidx.databinding.InverseMethod;

public class DoubleConverter {

    private static final String DOT = ".";
    private static final String COMA = ",";
    private static final String EMPTY = "";

    @InverseMethod("toDouble")
    public static String toString(Double value) {
        return value == null ? EMPTY : value.toString();
    }

    public static Double toDouble(String value) {
        try {
            return value == null || value.isEmpty() ? 0D : Double.valueOf(value.replace(COMA, DOT));
        } catch (NumberFormatException exc) {
            return 0D;
        }
    }

}
