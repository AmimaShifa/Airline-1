package airlines.model;

/**
 * Created by winio_000 on 2016-01-11.
 */
public class FlightInfo {
    private String source;
    private String destination;
    private String arrival;
    private String departure;

    public FlightInfo() {

    }

    public FlightInfo(String arrival, String source, String destination, String departure) {
        this.arrival = arrival;
        this.source = source;
        this.destination = destination;
        this.departure = departure;
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

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    @Override
    public String toString() {
        return "FlightInfo{" +
                "source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", arrival='" + arrival + '\'' +
                ", departure='" + departure + '\'' +
                '}';
    }
}
