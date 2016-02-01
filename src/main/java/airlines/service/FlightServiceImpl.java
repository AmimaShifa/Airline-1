package airlines.service;

import airlines.business.FlightProcessor;
import airlines.model.Flight;
import airlines.model.ReservationInfo;
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

    @Inject
    private FlightProcessor flightProcessor;

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
        flightRepository.save(flight);
    }

    @Override
    public Iterable<Flight> findAll(String source, String destination, String arrival, String departure) {
        ReservationInfo reservationInfo = new ReservationInfo(source, destination, departure, arrival);
        reservationInfo.fixTimeZoneMissMatch(departure, arrival);
        List<Flight> paginatedFlights = flightProcessor.findWantedFlights((List<Flight>) flightRepository.findAll(), reservationInfo);

        return paginatedFlights;
    }

    @Override
    public Flight findOne(long id) {
        return flightRepository.findOne(id);
    }

    public void setFlightRepository(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }
}
