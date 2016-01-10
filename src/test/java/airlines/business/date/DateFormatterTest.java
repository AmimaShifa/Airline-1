package airlines.business.date;


import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

/**
 * Created by winio_000 on 2016-01-10.
 */
public class DateFormatterTest {

    private LocalDate localDate;

    @Test
    public void shouldReturnTheSameDateAfterFormat() {
        localDate = LocalDate.now();
        String localDateString = localDate.toString();

        LocalDate parsedDate = DateFormatter.formatDate(localDateString);

        assertEquals(localDate.getYear(), parsedDate.getYear());
        assertEquals(localDate.getMonth(), parsedDate.getMonth());
        assertEquals(localDate.getDayOfMonth(), parsedDate.getDayOfMonth());
    }

    @Test
    public void shouldReturnDateInFormat_YYYY_MM_dd() {
        String date = "2015-11-12";

        LocalDate formattedDate = DateFormatter.formatDate(date);

        assertEquals(date, formattedDate.toString());
    }
}