package airlines.business;

import airlines.model.Flight;
import airlines.model.ReservationInfo;
import org.apache.log4j.Logger;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by winio_000 on 2016-01-10.
 */
@Named
public class FlightProcessor {
    private static final Logger LOGGER = Logger.getLogger(FlightProcessor.class);
    private List<Flight> paginatedFlights = new ArrayList<>();

    public List<Flight> findWantedFlights(List<Flight> storedFlights, ReservationInfo reservationInfo) {
        if (storedFlights.isEmpty()) {
            return new ArrayList<>();
        }
        if (reservationInfo.isEmpty()) {
            return storedFlights;
        }
        paginatedFlights = getProperFlights(storedFlights, reservationInfo);

        return paginatedFlights;
    }

    private List<Flight> getProperFlights(List<Flight> storedFlights, ReservationInfo reservationInfo) {
        String wantedSource = reservationInfo.getSource();
        String wantedDestination = reservationInfo.getDestination();
        String wantedDeparture = reservationInfo.getDeparture();
        String wantedArrival = reservationInfo.getArrival();

        if (wantedSource != null) {
            paginatedFlights = storedFlights
                    .stream()
                    .filter(flight -> flight.getSource().equals(wantedSource))
                    .collect(Collectors.toList());
        }
        if (wantedDestination != null) {
            paginatedFlights = storedFlights
                    .stream()
                    .filter(flight -> (wantedDestination != null) && (flight.getDestination().equals(wantedDestination)))
                    .collect(Collectors.toList());
        }


        if (wantedDeparture != null) {
            paginatedFlights = storedFlights
                    .stream()
                    .filter(flight -> (wantedDeparture != null) && hasSameDeparture(flight, wantedDeparture))
                    .collect(Collectors.toList());
        }

        if (wantedArrival != null) {
            paginatedFlights = storedFlights
                    .stream()
                    .filter(flight -> (wantedArrival != null) && hasSameArrival(flight, wantedArrival))
                    .collect(Collectors.toList());
        }

        return paginatedFlights;
    }

    private boolean hasSameDeparture(Flight flight, String wantedDeparture) {
        String trimmedFlightDeparture = DateFormatter.trimDate(flight.getDeparture());
        String trimmedWantedDeparture = DateFormatter.trimDate(wantedDeparture);

        return trimmedFlightDeparture.equals(trimmedWantedDeparture);
    }

    private boolean hasSameArrival(Flight flight, String wantedArrival) {
        String trimmedFlightArrival = DateFormatter.trimDate(flight.getArrival());
        String trimmedWantedArrival = DateFormatter.trimDate(wantedArrival);

        return trimmedFlightArrival.equals(trimmedWantedArrival);
    }
}
