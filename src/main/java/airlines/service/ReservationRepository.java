package airlines.service;

import airlines.model.Reservation;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by winio_000 on 2016-01-05.
 */
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
}
