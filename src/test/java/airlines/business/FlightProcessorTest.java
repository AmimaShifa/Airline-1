package airlines.business;

import airlines.model.Flight;
import airlines.model.FlightInfo;
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

    @Before
    public void setUp() {
        flights = new ArrayList<>();
        flightProcessor = new FlightProcessor();
    }

    @Test
    public void shouldReturnTrueWhenEquatingTwoFlightsWithTheSameFields() {
        Flight flight1 = new Flight("1", "1", "1", "1", "1", 1.0, 1);
        Flight flight2 = new Flight("1", "1", "1", "1", "1", 1.0, 1);

        assertEquals(flight1, flight2);
    }

    //equals() and hashcode() test for flight model
    @Test
    public void shouldReturnAllFlightsWhenFlightInfoIsEmpty() {
        setUpFlightList(3);

        List<Flight> resultFlights = flightProcessor.findWantedFlights(flights, new FlightInfo(null, null, null, null));

        assertEquals(flights, resultFlights);
    }

    @Test
    public void shouldReturnOnlyOneFlightWithGivenSource() {
        setUpFlightList(3);

        List<Flight> resultFlights = flightProcessor.findWantedFlights(flights, new FlightInfo(null, "source1", null, null));

        assertEquals(resultFlights.size(), 1);
        assertEquals(resultFlights.get(0), flights.get(1));
    }

    @Test
    public void shouldReturnEmptyListWhenZeroFlightsGiven() {
        List<Flight> wantedFlights = flightProcessor.findWantedFlights(new ArrayList<>(), new FlightInfo(null, "source1", null, null));

        assertEquals(0, wantedFlights.size());
    }

    @Test
    public void shouldNotReturnAnyFlightNonOfTheFlightsMatchesTwoParameters() {
        setUpFlightList(5);

        List<Flight> resultFlights = flightProcessor.findWantedFlights(flights, new FlightInfo(null, "source4", "destination3", null));

        assertEquals(resultFlights.size(), 0);
    }

    @Test
    public void shouldReturnOneFlightWhenSourceAndDestinationMatches() {
        setUpFlightList(5);

        List<Flight> resultFlights = flightProcessor.findWantedFlights(flights, new FlightInfo(null, "source4", "destination4", null));

        assertEquals(resultFlights.size(), 1);
        assertEquals(resultFlights.get(0), flights.get(4));
    }

    @Test
    public void shouldReturnTwoDifferentFlightsWhenSourceAndDestinationMathcesBoth() {
        List<Flight> flights = new ArrayList<>();

        flights.add(new Flight("aaa", "sadasd", "notNull", "source111", "destination111", 123.99, 88));
        flights.add(new Flight("difftaaa", "diffbbb", "notNull", "source111", "destination111", 499.12, 144));
        flights.add(createFlight(1));
        flights.add(createFlight(2));
        flights.add(createFlight(3));

        List<Flight> resultFlights =
                flightProcessor
                        .findWantedFlights(flights, new FlightInfo(null, "source111", "destination111", null));

        assertEquals(2, resultFlights.size());
        assertEquals(flights.get(0), resultFlights.get(0));
        assertEquals(flights.get(1), resultFlights.get(1));
    }

    @Test
    public void shouldReturnFlightWhenAllParametersMatches() {
        List<Flight> flights = new ArrayList<>();

        flights.add(new Flight("aaa", "ddd", "aaa", "source111", "destination111", 99.99, 99));
        flights.add(new Flight("difftaaa", "ddd", "aaa", "source111", "destination111", 399.12, 12));
        flights.add(createFlight(1));
        flights.add(createFlight(2));
        flights.add(createFlight(3));

        List<Flight> resultFlights =
                flightProcessor
                        .findWantedFlights(flights, new FlightInfo("aaa", "source111", "destination111", "ddd"));

        assertEquals(2, resultFlights.size());
        assertEquals(flights.get(0), resultFlights.get(0));
        assertEquals(flights.get(1), resultFlights.get(1));
    }

    private void setUpFlightList(int i) {
        for (int j = 0; j < i; j++) {
            flights.add(createFlight((long) j));
        }
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