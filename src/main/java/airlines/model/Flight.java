package airlines.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by winio_000 on 2015-12-13.
 */
@Entity
@Table(name = "flight")
@XmlRootElement
public class Flight implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "airlineName", nullable = false)
    private String airlineName;

    @NotNull
    @Column(name = "departure", nullable = false)
    private String departure;

    @NotNull
    @Column(name = "arrival", nullable = false)
    private String arrival;

    @Column(name = "source", nullable = false)
    private String source;

    @Column(name = "destination", nullable = false)
    private String destination;

    @Column(name = "flightPrice", nullable = false)
    private Double flightPrice;

    @Column(name = "seats", nullable = false)
    private Integer seats;

    @JoinColumn(name = "reservationId")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Reservation reservation;

    public Flight() {
    }

    public Flight(String airlineName, String departure, String arrival, String source, String destination, Double flightPrice, Integer seats) {
        this.airlineName = airlineName;
        this.departure = departure;
        this.arrival = arrival;
        this.source = source;
        this.destination = destination;
        this.flightPrice = flightPrice;
        this.seats = seats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Double getFlightPrice() {
        return flightPrice;
    }

    public void setFlightPrice(Double flightPrice) {
        this.flightPrice = flightPrice;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", airlineName='" + airlineName + '\'' +
                ", departure=" + departure +
                ", arrival=" + arrival +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", flightPrice=" + flightPrice +
                ", seats=" + seats +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        if (airlineName != null ? !airlineName.equals(flight.airlineName) : flight.airlineName != null) return false;
        if (departure != null ? !departure.equals(flight.departure) : flight.departure != null) return false;
        if (arrival != null ? !arrival.equals(flight.arrival) : flight.arrival != null) return false;
        if (source != null ? !source.equals(flight.source) : flight.source != null) return false;
        if (destination != null ? !destination.equals(flight.destination) : flight.destination != null) return false;
        if (flightPrice != null ? !flightPrice.equals(flight.flightPrice) : flight.flightPrice != null) return false;
        if (seats != null ? !seats.equals(flight.seats) : flight.seats != null) return false;
        return !(reservation != null ? !reservation.equals(flight.reservation) : flight.reservation != null);

    }

    @Override
    public int hashCode() {
        int result = airlineName != null ? airlineName.hashCode() : 0;
        result = 31 * result + (departure != null ? departure.hashCode() : 0);
        result = 31 * result + (arrival != null ? arrival.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (destination != null ? destination.hashCode() : 0);
        result = 31 * result + (flightPrice != null ? flightPrice.hashCode() : 0);
        result = 31 * result + (seats != null ? seats.hashCode() : 0);
        result = 31 * result + (reservation != null ? reservation.hashCode() : 0);
        return result;
    }
}
