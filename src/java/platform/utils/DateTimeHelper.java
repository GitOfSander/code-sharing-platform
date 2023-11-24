package platform.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeHelper {
    private final String DATE_FORMAT = "yyyy/dd/MM HH:mm:ss";

    public String dateToString(Date date, String... format) {
        DateFormat dateFormat = new SimpleDateFormat(format.length > 0 ? format[0] : DATE_FORMAT);
        return dateFormat.format(date);
    }
}
