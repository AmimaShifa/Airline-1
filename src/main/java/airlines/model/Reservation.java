package airlines.model;

import org.apache.log4j.Logger;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
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

    @NotNull
    @OneToMany(targetEntity = Flight.class, mappedBy = "reservation", fetch = FetchType.EAGER)
    private List<Flight> flights;

    @NotNull
    @Column(name = "passengers", nullable = false)
    private HashMap<Integer, Integer> passengers;

    @Column(name = "class", nullable = false)
    private String flightClass;

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

    public HashMap<Integer, Integer> getPassengers() {
        return passengers;
    }

    public void setPassengers(HashMap<Integer, Integer> passengers) {
        this.passengers = passengers;
    }

    public String getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }
}

