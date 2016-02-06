package airlines.model;

import airlines.business.DateFormatter;

/**
 * Created by winio_000 on 2016-01-26.
 */
public class ReservationInfo {

    private String source;
    private String destination;
    private String arrival;
    private String departure;

    public ReservationInfo() {
    }

    public ReservationInfo(String source, String destination, String departure, String arrival) {
        this.source = source;
        this.destination = destination;
        this.departure = departure;
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

    public boolean isEmpty() {
        return (getSource() == null || getSource() == "")
                && (getDestination() == null || getDestination() == "")
                && (getDeparture() == null || getDeparture() == "")
                && (getArrival() == null || getArrival() == "");
    }

    public void fixTimeZoneMissMatch(String departure, String arrival) {
        this.departure = DateFormatter.addOneDayDueToTimeZoneMissMach(departure);
        this.arrival = DateFormatter.addOneDayDueToTimeZoneMissMach(arrival);
    }
}
