package airlines.service;

import airlines.model.Reservation;

/**
 * Created by winio_000 on 2016-01-05.
 */
public interface ReservationService {

    void deleteAll();

    void delete(Reservation reservation);

    void delete(Long id);

    void save(Reservation reservation);

    Iterable<Reservation> findAll();

    Reservation findOne(Long id);
}
