package Utils;

import java.util.Random;
import java.util.UUID;
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


    public static String generateId() {
        return UUID.randomUUID().toString();
    }

    public static String generateId(String prefix) {
        int randomNum = new Random().nextInt(90000) + 10000; // 5 digits
        return prefix + "-" + randomNum;
    }

    public static String generateId(String prefix, int length) {
        if (length <= 0) length = 5;
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10)); // add random digit 0–9
        }

        return prefix + "-" + sb.toString();
    }

    public static String generateId(String prefix, String suffix) {
        int randomNum = new Random().nextInt(90000) + 10000;
        return prefix + "-" + randomNum + "-" + suffix;
    }
}