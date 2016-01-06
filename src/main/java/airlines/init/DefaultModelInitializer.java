package airlines.init;

import airlines.model.Client;
import airlines.model.Flight;
import airlines.rest.ClientRestService;
import airlines.rest.FlightRestService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Calendar;

/**
 * Created by winio_000 on 2016-01-06.
 */

@Service
public class DefaultModelInitializer {

    @Inject
    private ClientRestService clientRestService;

    @Inject
    private FlightRestService flightRestService;

    @PostConstruct
    private void postConstructDefaultModelsInstances() {
        addDefaultClientModel();
        addDefaultFlightModel();
        // addDefaultReservationModel();
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
        flight.setArrival(Calendar.getInstance());
        flight.setDeparture(Calendar.getInstance());
        flight.setDestination("DefaultDestination");
        flight.setSource("DefaultSorce");
        flight.setSeats(150);
        flight.setFlightPrice(159.99);

        flightRestService.createFLight(flight);
    }
}
