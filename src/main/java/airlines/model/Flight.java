package airlines.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Calendar;

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
    private long id;

    @Column(name = "airlineName", nullable = false)
    private String airlineName;

    @Column(name = "departure", nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar departure;

    @Column(name = "arrival", nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar arrival;

    @Column(name = "source", nullable = false)
    private String source;

    @Column(name = "destination", nullable = false)
    private String destination;

    @Column(name = "flightPrice", nullable = false)
    private Double flightPrice;

    @Column(name = "seats", nullable = false)
    private Integer seats;

    public Flight() {
    }

    public Flight(String airlineName, Calendar departure, Calendar arrival, String source, String destination, Double flightPrice, Integer seats) {
        this.airlineName = airlineName;
        this.departure = departure;
        this.arrival = arrival;
        this.source = source;
        this.destination = destination;
        this.flightPrice = flightPrice;
        this.seats = seats;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public Calendar getDeparture() {
        return departure;
    }

    public void setDeparture(Calendar departure) {
        this.departure = departure;
    }

    public Calendar getArrival() {
        return arrival;
    }

    public void setArrival(Calendar arrival) {
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
}
