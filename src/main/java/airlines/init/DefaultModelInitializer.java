package airlines.init;

import airlines.model.Client;
import airlines.model.Flight;
import airlines.model.Reservation;
import airlines.rest.ClientRestService;
import airlines.rest.FlightRestService;
import airlines.rest.ReservationRestService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by winio_000 on 2016-01-06.
 */

@Service
public class DefaultModelInitializer {

    @Inject
    private ClientRestService clientRestService;

    @Inject
    private FlightRestService flightRestService;

    @Inject
    private ReservationRestService reservationRestService;

    @PostConstruct
    private void postConstructDefaultModelsInstances() {
        addDefaultClientModel();
        addDefaultFlightModel();
        addDefaultReservationModel();
    }

    private void addDefaultReservationModel() {
        Reservation reservation = new Reservation();

        reservation.setFlightClass("Premium class");
        reservation.setFlights(Arrays.asList(new Flight("aname", "2016-07-07", "2016-07-07", "Kraków", "Oslo", 100.99, 100)));
        reservation.setPassengers(new HashMap<>(new Integer(1), new Integer(2)));

        reservationRestService.createReservation(reservation);
    }

    private void addDefaultClientModel() {
        Client client = new Client();
        client.setEmail("defaultEmail@default.com");
        client.setFirstName("DefaultName");
        client.setLastName("DefaultLastName");
        client.setPassword("defaultPassword");

        clientRestService.createClient(client);
    }

    private void addDefaultFlightModel() {
        Flight flight = new Flight();
        flight.setAirlineName("DefaultAirlineName");
        flight.setArrival("2016-01-07");
        flight.setDeparture("2016-01-07");
        flight.setDestination("DefaultDestination");
        flight.setSource("DefaultSorce");
        flight.setSeats(150);
        flight.setFlightPrice(159.99);

        Flight flight2 = new Flight();
        flight2.setAirlineName("DefaultAirlineName2");
        flight2.setArrival("2016-01-07");
        flight2.setDeparture("2016-01-07");
        flight2.setDestination("Oslo");
        flight2.setSource("Kraków");
        flight2.setSeats(200);
        flight2.setFlightPrice(139.99);

        flightRestService.createFLight(flight);
        flightRestService.createFLight(flight2);
    }
}
