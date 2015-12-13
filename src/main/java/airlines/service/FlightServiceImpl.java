package airlines.service;

import airlines.model.Flight;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

/**
 * Created by winio_000 on 2015-12-13.
 */
@Named
@Transactional
public class FlightServiceImpl implements FlightService {

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
        flightRepository.save(flight);
    }

    @Override
    public Iterable<Flight> findAll() {
        return flightRepository.findAll();
    }

    @Override
    public Flight findOne(long id) {
        return flightRepository.findOne(id);
    }

    public void setFlightRepository(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }
}
