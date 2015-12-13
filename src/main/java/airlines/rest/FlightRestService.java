package airlines.rest;

import airlines.model.Flight;
import airlines.service.FlightService;
import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
            return Response.status(Response.Status.NOT_FOUND).entity("flight not found for id : " + id).build();
        }
        return Response.ok(flight, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Flight> getAllFlights() {
        return (List<Flight>) flightService.findAll();
    }

    @DELETE
    @Path("{id}")
    public Response deleteFlight(@PathParam("id") long id) {
        flightService.delete(id);
        return Response.status(200).entity("flight with id : " + id + " deleted").build();
    }

    @DELETE
    public Response deleteAllFlights() {
        flightService.deleteAll();
        return Response.status(200).entity("All flights deleted").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createFLight(Flight flight) {
        try {
            flightService.save(flight);
        } catch (DataIntegrityViolationException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("such flight already exists").build();
        }
        return Response.status(200).entity("flight created").build();
    }

    public void setFlightService(FlightService flightService) {
        this.flightService = flightService;
    }
}
