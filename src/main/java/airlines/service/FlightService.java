package airlines.service;

import airlines.model.Flight;

import java.util.List;

/**
 * Created by winio_000 on 2015-12-13.
 */
public interface FlightService {

    void deleteAll();

    void delete(Flight flight);

    void delete(long id);

    void save(Flight flight);

    Iterable<Flight> findAll(String source, String destination, String arrival, String departure);

    Flight findOne(long id);
}
