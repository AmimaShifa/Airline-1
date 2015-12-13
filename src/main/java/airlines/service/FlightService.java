package airlines.service;

import airlines.model.Flight;

/**
 * Created by winio_000 on 2015-12-13.
 */
public interface FlightService {

    void deleteAll();

    void delete(Flight flight);

    void delete(long id);

    void save(Flight flight);

    Iterable<Flight> findAll();

    Flight findOne(long id);
}
