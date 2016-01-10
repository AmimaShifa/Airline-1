package airlines.rest;

import airlines.model.Flight;
import airlines.service.FlightService;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by winio_000 on 2015-12-13.
 */
@Named
@Path("/flights")
public class FlightRestService {

    private static Logger logger = Logger.getLogger(FlightRestService.class);

    @Inject
    private FlightService flightService;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFlight(@PathParam("id") long id) {
        Flight flight = flightService.findOne(id);
        if (flight == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("flight not found for id : " + id)
                    .build();
        }
        return Response.ok(flight, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Flight> getAllFlights(@QueryParam("source") String source, @QueryParam("destination") String destination,
                                      @QueryParam("arrival") String arrival, @QueryParam("departure") String departure) {

        return (List<Flight>) flightService.findAll(source,destination,arrival,departure);
    }

    @DELETE
    @Path("{id}")
    public Response deleteFlight(@PathParam("id") long id) {
        try {
            flightService.delete(id);
            return Response.status(200).entity("flight with id : " + id + " deleted").build();
        } catch (DataAccessException de) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Could not delete flight for id : " + id + ", such flight not found")
                    .build();
        }
    }

    @DELETE
    public Response deleteAllFlights() {
        try {
            flightService.deleteAll();
            return Response.status(200).entity("All flights deleted").build();
        } catch (DataAccessException de) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("There is no reservations to delete")
                    .build();
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createFLight(Flight flight) {
        try {
            flightService.save(flight);
            return Response.status(200).entity("flight created").build();
        } catch (DataIntegrityViolationException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("such flight already exists").build();
        }
    }

    public void setFlightService(FlightService flightService) {
        this.flightService = flightService;
    }
}
