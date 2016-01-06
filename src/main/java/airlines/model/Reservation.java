package airlines.model;

import org.apache.log4j.Logger;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by winio_000 on 2016-01-05.
 */

@Entity
@Table(name = "reservation")
@XmlRootElement
public class Reservation {
    private static final Logger LOGGER = Logger.getLogger(Reservation.class);

    @Id
    @GeneratedValue
    @Column(name = "reservationId")
    private Long reservationId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reservation")
    private List<Flight> flights;

    public Reservation() {
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public void addFlight(Flight flight) {
        if (flights.contains(flight))
            LOGGER.error("Reservation already has this flight ! : " + flight);
        else
            flights.add(flight);
    }
}

