package Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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


    public static boolean isValidDate(Date date) {
        return (date != null);
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

    public static boolean isValidDate(Date date, Date minDate, Date maxDate) {
        if (isNull(date) || isNull(minDate) || isNull(maxDate)) return false;
        return (!date.before(minDate) && !date.after(maxDate));
    }

    public static boolean isFutureDate(Date date) {
        if (isNull(date)) return false;
        Date now = new Date();
        return date.after(now);
    }

    public static boolean isPastDate(Date date) {
        if (isNull(date)) return false;
        Date now = new Date();
        return date.before(now);
    }

    public static boolean isToday(Date date) {
        if (isNull(date)) return false;

        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        Date today = new Date();
        return fmt.format(today).equals(fmt.format(date));
    }
}