package airlines.business.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by winio_000 on 2016-01-10.
 */
public class DateFormatter {
    private static final String DATE_PATTERN_YYYY_MM_dd = "yyyyMMdd";

    public static LocalDate formatStringToLocaleDate(String date) {
        LocalDate localDate;
        if (date == null)
            localDate = LocalDate.now();
        date = date.split("T")[0];
        date = date.replaceAll("-", "");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN_YYYY_MM_dd);

        localDate = LocalDate.parse(date, dateTimeFormatter);

        return localDate;
    }

    public static String trimDate(String date) {
        String resultDate = "";
        if (date.length() < 11)
            resultDate = date;
        else if (date.charAt(10) == 'T') {
            resultDate = date.split("T")[0];
        } else if (date.charAt(10) == ' ') {
            resultDate = date.split(" ")[0];
        } else resultDate = LocalDate.now().toString();

        return resultDate;
    }
}
