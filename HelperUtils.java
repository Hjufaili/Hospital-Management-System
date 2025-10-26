package Utils;

import java.util.regex.Pattern;

public class HelperUtils {
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isNull(String str) {
        return (str == null || str.trim().isEmpty());
    }

    public static boolean isNotNull(Object obj) {
        return obj != null;
    }

    public static boolean isNotNull(String str) {
        return (str != null && !str.trim().isEmpty());
    }


    public static boolean isValidString(String str) {
        return (str != null && !str.trim().isEmpty());
    }

    public static boolean isValidString(String str, int minLength) {
        return (isValidString(str) && str.trim().length() >= minLength);
    }

    public static boolean isValidString(String str, int minLength, int maxLength) {
        if (!isValidString(str)) return false;
        int len = str.trim().length();
        return (len >= minLength && len <= maxLength);
    }

    public static boolean isValidString(String str, String regex) {
        if (!isValidString(str) || isNull(regex)) return false;
        return Pattern.matches(regex, str.trim());
    }
}