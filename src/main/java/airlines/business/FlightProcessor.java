package airlines.business;

import airlines.business.date.DateFormatter;
import airlines.model.Flight;
import airlines.model.FlightInfo;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by winio_000 on 2016-01-10.
 */
@Named
public class FlightProcessor {

    private List<Flight> paginatedFlights = new ArrayList<>();

    public List<Flight> findWantedFlights(Iterable<Flight> allFlights, FlightInfo flightInfo) {
        checkMethodParamaters(allFlights, flightInfo);
        processQueryParameters(flightInfo);

        return paginatedFlights;
    }

    private void checkMethodParamaters(Iterable<Flight> allFlights, FlightInfo flightInfo) {
        if (Arrays.asList(allFlights).isEmpty())
            paginatedFlights = Collections.emptyList();
        else
            paginatedFlights = (List<Flight>) allFlights;
    }

    private void processQueryParameters(FlightInfo flightInfo) {
        processSourceParam(flightInfo);
        processArrivalParam(flightInfo);
        processDepartureParam(flightInfo);
        processDestinationParam(flightInfo);
    }

    private void processDestinationParam(FlightInfo flightInfo) {
        if (flightInfo.getDestination() != null) {
            paginatedFlights = paginatedFlights
                    .stream()
                    .filter(flight -> flight.getDestination() != null)
                    .filter(flight -> flight.getDestination().equals(flightInfo.getDestination()))
                    .collect(Collectors.toList());
        }
    }

    private void processDepartureParam(FlightInfo flightInfo) {
        if (flightInfo.getDeparture() != null) {
            paginatedFlights = paginatedFlights
                    .stream()
                    .filter(flight -> flight.getDeparture() != null)
                    .filter(flight -> DateFormatter.trimDate(flight.getDeparture()).equals(DateFormatter.trimDate(flightInfo.getDeparture())))
                    .collect(Collectors.toList());
        }
    }

    private void processArrivalParam(FlightInfo flightInfo) {
        if (flightInfo.getArrival() != null) {
            paginatedFlights = paginatedFlights
                    .stream()
                    .filter(flight -> flight.getArrival() != null)
                    .filter(flight -> DateFormatter.trimDate(flight.getArrival()).equals(DateFormatter.trimDate(flightInfo.getArrival())))
                    .collect(Collectors.toList());
        }
    }

    private void processSourceParam(FlightInfo flightInfo) {
        if (flightInfo.getSource() != null) {
            paginatedFlights = paginatedFlights
                    .stream()
                    .filter(flight -> flight.getSource() != null)
                    .filter(flight -> flight.getSource().equals(flightInfo.getSource()))
                    .collect(Collectors.toList());
        }
    }

    private boolean isFlightInfoEmpty(FlightInfo flightInfo) {
        return flightInfo.getArrival() == null
                && flightInfo.getDeparture() == null
                && flightInfo.getSource() == null
                && flightInfo.getDestination() == null;
    }
}
