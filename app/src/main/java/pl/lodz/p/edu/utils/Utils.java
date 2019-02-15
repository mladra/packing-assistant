package pl.lodz.p.edu.utils;

import java.util.Random;

public class Utils {

    public static String random(int length) {
        final Random gen = new Random();
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = (char) (gen.nextInt(96) + 32);
            sb.append(c);
        }
        return sb.toString();
    }

}
