package Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
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
        return isNotNull(str);
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
        int randomNum = new Random().nextInt(90000) + 10000;
        return prefix + "-" + randomNum;
    }

    public static String generateId(String prefix, int length) {
        if (length <= 0) length = 5;
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }

        return prefix + "-" + sb.toString();
    }

    public static String generateId(String prefix, String suffix) {
        int randomNum = new Random().nextInt(90000) + 10000;
        return prefix + "-" + randomNum + "-" + suffix;
    }


    public static boolean isValidDate(LocalDate date) {
        return isNotNull(date);
    }

    public static boolean isValidDate(String dateStr) {
        if (isNull(dateStr)) return false;
        String[] formats = {"yyyy-MM-dd", "dd/MM/yyyy", "MM-dd-yyyy", "yyyy/MM/dd"};

        for (String format : formats) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                sdf.setLenient(false);
                Date date = sdf.parse(dateStr);
                if (date != null) return true;
            } catch (ParseException e) {
            }
        }
        return false;
    }

    public static boolean isValidDate(LocalDate date, LocalDate minDate, LocalDate maxDate) {
        if (isNull(date) || isNull(minDate) || isNull(maxDate)) return false;
        return (!date.isBefore(minDate) && !date.isAfter(maxDate));
    }

    public static boolean isFutureDate(LocalDate date) {
        if (isNull(date)) return false;
        LocalDate today = LocalDate.now();
        return date.isAfter(today);
    }

    public static boolean isPastDate(LocalDate date) {
        if (isNull(date)) return false;
        LocalDate today = LocalDate.now();
        return date.isBefore(today);
    }

    public static boolean isToday(LocalDate date) {
        if (isNull(date)) return false;
        LocalDate today = LocalDate.now();
        return date.isEqual(today);
    }


    public static boolean isValidNumber(int num, int min, int max) {
        return (num >= min && num <= max);
    }

    public static boolean isValidNumber(double num, double min, double max) {
        return (num >= min && num <= max);
    }

    public static boolean isPositive(int num) {
        return num > 0;
    }

    public static boolean isPositive(double num) {
        return num > 0.0;
    }

    public static boolean isNegative(int num) {
        return num < 0;
    }

    public static boolean isNegative(double num) {
        return num < 0.0;
    }


    public static boolean isValidAge(int age) {
        return (age >= 0 && age <= 120);
    }

    public static boolean isValidAge(LocalDate dateOfBirth) {
        if (dateOfBirth == null) return false;
        LocalDate today = LocalDate.now();
        if (dateOfBirth.isAfter(today)) return false;

        int age = Period.between(dateOfBirth, today).getYears();
        return isValidAge(age);
    }
}