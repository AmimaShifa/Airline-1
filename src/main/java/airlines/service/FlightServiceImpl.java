package airlines.service;

import airlines.business.FlightComparator;
import airlines.model.Flight;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by winio_000 on 2015-12-13.
 */
@Named
@Transactional
public class FlightServiceImpl implements FlightService {

    private static Logger logger = Logger.getLogger(FlightServiceImpl.class);

    @Inject
    private FlightRepository flightRepository;

    @Override
    public void deleteAll() {
        flightRepository.deleteAll();
    }

    @Override
    public void delete(Flight flight) {
        flightRepository.delete(flight);
    }

    @Override
    public void delete(long id) {
        flightRepository.delete(id);
    }

    @Override
    public void save(Flight flight) {
//        flight.setDeparture(DateFormatter.formatDate(flight.getDeparture()).toString());
//        flight.setArrival(DateFormatter.formatDate(flight.getArrival()).toString());
        flightRepository.save(flight);
    }

    @Override
    public Iterable<Flight> findAll(String source, String destination, String arrival, String departure) {
        System.out.println("source : "+ source);
        System.out.println("destination : "+ destination);
        System.out.println("arrival : "+ arrival);
        System.out.println("departure : "+ departure);
        return flightRepository.findAll();
    }

    @Override
    public Flight findOne(long id) {
        return flightRepository.findOne(id);
    }

    @Override
    public List<Flight> findWantedFlights(Flight flight) {
        List<Flight> foundFlights = FlightComparator.findWantedFlights(flight, flightRepository.findAll());

        return foundFlights;
    }

    public void setFlightRepository(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }
}
