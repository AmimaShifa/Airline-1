package airlines.business;

import airlines.model.Flight;
import airlines.model.FlightInfo;

import javax.inject.Named;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by winio_000 on 2016-01-10.
 */
@Named
public class FlightProcessor {

    private List<Flight> paginatedFligts = new ArrayList<>();

    public List<Flight> findWantedFlights(Iterable<Flight> allFlights, FlightInfo flightInfo) {
        paginatedFligts = (List<Flight>) allFlights;

        if (paginatedFligts.isEmpty()) {
            return Collections.emptyList();
        }

        if (isFlightInfoEmpty(flightInfo))
            return (List<Flight>) allFlights;

        processFlightInfoCredentials(flightInfo);

        return paginatedFligts;

    }

    private void processFlightInfoCredentials(FlightInfo flightInfo) {
        List<Method> flightInfoMethods1 = getFlightInfoGetters(flightInfo);

        if (flightInfo.getSource() != null) {
            paginatedFligts = paginatedFligts
                    .stream()
                    .filter(flight -> flight.getSource().equals(flightInfo.getSource()))
                    .collect(Collectors.toList());
        }

        if (flightInfo.getDestination() != null) {
            paginatedFligts = paginatedFligts
                    .stream()
                    .filter(flight -> flight.getDestination().equals(flightInfo.getDestination()))
                    .collect(Collectors.toList());
        }
    }

    private List<Method> getFlightInfoGetters(FlightInfo flightInfo) {
        List<Method> flightInfoMethods = new ArrayList<>();
        try {
            for (PropertyDescriptor propertyDescriptor :
                    Introspector.getBeanInfo(flightInfo.getClass(), Object.class).getPropertyDescriptors()) {
                flightInfoMethods.add(propertyDescriptor.getReadMethod());
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return flightInfoMethods;
    }

    private boolean isFlightInfoEmpty(FlightInfo flightInfo) {
        return flightInfo.getArrival() == null
                && flightInfo.getDeparture() == null
                && flightInfo.getSource() == null
                && flightInfo.getDestination() == null;
    }

}
