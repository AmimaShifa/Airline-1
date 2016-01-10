package airlines.business.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by winio_000 on 2016-01-10.
 */
public class DateFormatter {
    private static final String DATE_PATTERN_YYYY_MM_dd = "yyyyMMdd";
    private static DateTimeFormatter dateTimeFormatter;

    public static LocalDate formatDate(String date) {
        LocalDate localDate;
        if (date == null)
            localDate = LocalDate.now();

        date = date.split("T")[0];
        date = date.replaceAll("-", "");
        dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN_YYYY_MM_dd);

        localDate = LocalDate.parse(date, dateTimeFormatter);

        return localDate;
    }

}
