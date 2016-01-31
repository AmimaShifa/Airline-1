package airlines.business;

import airlines.model.Flight;
import airlines.model.ReservationInfo;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by winio_000 on 2016-01-11.
 */
public class FlightProcessorTest {

    private List<Flight> flights;
    private FlightProcessor flightProcessor;
    private ReservationInfo reservationInfo;

    @Before
    public void setUp() {
        flights = new ArrayList<>();
        flightProcessor = new FlightProcessor();
        reservationInfo = new ReservationInfo();
    }

    @Test
    public void shouldReturnEmptyListWhenNoFlightsIndDB() throws Exception {
        List<Flight> processedFlights = flightProcessor.findWantedFlights(flights, reservationInfo);

        assertEquals(0, processedFlights.size());
    }

    @Test
    public void shouldReturnAllStoredFlightsWhenNoQueryParams() throws Exception {
        flights.add(createFlight(1));
        flights.add(createFlight(2));

        List<Flight> processedFlights = flightProcessor.findWantedFlights(flights, reservationInfo);

        assertEquals(flights, processedFlights);
    }

    @Test
    public void shouldReturnTwoFlightsWithEqualOnlySource() throws Exception {
        flights.add(createFlightFrom("source1", "destination1", "2016-01-02 15:45", "2016-01-02 16:20"));
        flights.add(createFlightFrom("source1", "destination2", "2016-01-02 09:30", "2016-01-02 15:50"));
        flights.add(createFlightFrom("source2", "destination3", "2017-09-09 00:30", "2017-09-09 05:30"));
        ReservationInfo reservationInfo = createReservationFrom("source1", null, null, null);

        List<Flight> processedFlights = flightProcessor.findWantedFlights(flights, reservationInfo);

        assertEquals(2, processedFlights.size());
        assertEquals(flights.get(0), processedFlights.get(0));
        assertEquals(flights.get(1), processedFlights.get(1));
    }

    @Test
    public void shouldReturnOneFlightWithEqualOnlyDestination() throws Exception {
        flights.add(createFlightFrom("source1234", "destination", "2016-01-02 15:45", "2016-01-02 16:20"));
        flights.add(createFlightFrom("source4567", "destination2", "2016-01-02 09:30", "2016-01-02 15:50"));
        flights.add(createFlightFrom("source7890", "destination3", "2017-09-09 00:30", "2017-09-09 05:30"));
        ReservationInfo reservationInfo = createReservationFrom(null, "destination", null, null);

        List<Flight> processedFlights = flightProcessor.findWantedFlights(flights, reservationInfo);

        assertEquals(1, processedFlights.size());
        assertEquals(flights.get(0), processedFlights.get(0));
    }

    @Test
    public void shouldReturnTwoFlightsWithEqualSourceAndDestination() throws Exception {
        flights.add(createFlightFrom("source1234", "destination", "2016-01-02 15:45", "2016-01-02 16:20"));
        flights.add(createFlightFrom("source1234", "destination", "2016-01-02 09:30", "2016-01-02 15:50"));
        flights.add(createFlightFrom("source7890", "destination3", "2017-09-09 00:30", "2017-09-09 05:30"));
        ReservationInfo reservationInfo = createReservationFrom("source1234", "destination", null, null);

        List<Flight> processedFlights = flightProcessor.findWantedFlights(flights, reservationInfo);

        assertEquals(2, processedFlights.size());
        assertEquals(flights.get(0), processedFlights.get(0));
        assertEquals(flights.get(1), processedFlights.get(1));
    }

    @Test
    public void shouldReturnFlightWithEqualDeparture() throws Exception {
        flights.add(createFlightFrom("source1234", "destination", "2016-01-02 15:45", "2016-01-02 16:20"));
        flights.add(createFlightFrom("source7890", "destination3", "2017-09-09 00:30", "2017-09-09 05:30"));
        ReservationInfo reservationInfo = createReservationFrom(null, null, "2016-01-02", null);

        List<Flight> processedFlights = flightProcessor.findWantedFlights(flights, reservationInfo);

        assertEquals(1, processedFlights.size());
        assertEquals(flights.get(0), processedFlights.get(0));
    }

    @Test
    public void shouldReturnFlightWithEqualArrival() throws Exception {
        flights.add(createFlightFrom("source1234", "destination", "2016-01-02 15:45", "2016-01-02 16:20"));
        flights.add(createFlightFrom("source7890", "destination3", "2017-09-09 00:30", "2017-09-09 05:30"));
        ReservationInfo reservationInfo = createReservationFrom(null, null, null, "2017-09-09");

        List<Flight> processedFlights = flightProcessor.findWantedFlights(flights, reservationInfo);

        assertEquals(1, processedFlights.size());
        assertEquals(flights.get(1), processedFlights.get(0));
    }

    @Test
    public void shouldReturnTwoFlightsWithEqualDepartureAndArrival() throws Exception {
        flights.add(createFlightFrom("source1234", "destination1", "2016-01-02 15:45", "2016-01-02 16:20"));
        flights.add(createFlightFrom("source4567", "destination2", "2016-01-02 09:30", "2016-01-02 15:50"));
        flights.add(createFlightFrom("source7890", "destination3", "2017-09-09 00:30", "2017-09-09 05:30"));
        ReservationInfo reservationInfo = createReservationFrom(null, null, "2016-01-02", "2016-01-02");

        List<Flight> processedFlights = flightProcessor.findWantedFlights(flights, reservationInfo);

        assertEquals(2, processedFlights.size());
        assertEquals(flights.get(0), processedFlights.get(0));
        assertEquals(flights.get(1), processedFlights.get(1));
    }

    @Test
    public void shouldReturnOnlyOneFlightWithAllProperParams() throws Exception {
        flights.add(createFlightFrom("source1", "destination1", "2015-05-07 12:30", "2015-05-07 15:30"));
        flights.add(createFlightFrom("source2", "destination2", "2015-06-17 17:30", "2015-06-07 19:30"));
        flights.add(createFlightFrom("source3", "destination3", "2015-07-27 20:30", "2015-07-27 23:30"));
        ReservationInfo reservationInfo = createReservationFrom("source1", "destination1", "2015-05-07", "2015-05-07");

        List<Flight> processedFlights = flightProcessor.findWantedFlights(flights, reservationInfo);

        assertEquals(1, processedFlights.size());
        assertEquals(flights.get(0), processedFlights.get(0));
    }

    private ReservationInfo createReservationFrom(String source, String destination, String departure, String arrival) {
        ReservationInfo reservationInfo = new ReservationInfo();

        reservationInfo.setSource(source);
        reservationInfo.setDestination(destination);
        reservationInfo.setDeparture(departure);
        reservationInfo.setArrival(arrival);

        return reservationInfo;
    }

    private Flight createFlightFrom(String source, String destination, String departure, String arrival) {
        Flight flight = new Flight();

        flight.setSource(source);
        flight.setDestination(destination);
        flight.setDeparture(departure);
        flight.setArrival(arrival);

        return flight;
    }

    private Flight createFlight(long i) {
        Flight flight = new Flight();
        flight.setFlightPrice(i + 100.99);
        flight.setSeats((int) i + 100);
        flight.setSource("source" + i);
        flight.setDestination("destination" + i);
        flight.setAirlineName("name" + i);
        flight.setArrival("arrival" + i);
        flight.setDeparture("departure" + i);

        return flight;
    }
}