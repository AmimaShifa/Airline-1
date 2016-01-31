package airlines.business;


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

        LocalDate parsedDate = DateFormatter.formatStringToLocaleDate(localDateString);

        assertEquals(localDate.getYear(), parsedDate.getYear());
        assertEquals(localDate.getMonth(), parsedDate.getMonth());
        assertEquals(localDate.getDayOfMonth(), parsedDate.getDayOfMonth());
    }

    @Test
    public void shouldReturnDateInFormat_YYYY_MM_dd() {
        String date = "2015-11-12";

        LocalDate formattedDate = DateFormatter.formatStringToLocaleDate(date);

        assertEquals(date, formattedDate.toString());
    }

    @Test
    public void shouldReturnTrimmedDate() {
        String date = "2016-01-07T00:00:00.000Z";

        String trimmedDate = DateFormatter.trimDate(date);

        assertEquals("2016-01-07", trimmedDate);
    }

    @Test
    public void shouldIncrementDepartureAndArrivalByOneDay() throws Exception {
        String departure = "2016-01-07";
        String arrival = "2016-01-14";

        assertEquals(DateFormatter.addOneDayDueToTimeZoneMissMach(departure), "2016-01-08");
        assertEquals(DateFormatter.addOneDayDueToTimeZoneMissMach(arrival), "2016-01-15");
    }


}