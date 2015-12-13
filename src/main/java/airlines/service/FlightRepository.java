package airlines.service;

import airlines.model.Flight;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by winio_000 on 2015-12-13.
 */
public interface FlightRepository extends CrudRepository<Flight, Long> {
}
