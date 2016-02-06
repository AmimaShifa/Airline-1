package airlines.rest;

import airlines.model.Reservation;
import airlines.service.ReservationService;
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
 * Created by winio_000 on 2016-01-05.
 */

@Named
@Path("/reservations")
public class ReservationRestService {

    private static final Logger LOGGER = Logger.getLogger(ReservationRestService.class);

    @Inject
    private ReservationService reservationService;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReservation(@PathParam("id") Long id) {
        Reservation reservation = reservationService.findOne(id);
        if (reservation == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Reservation not found for id : " + id).build();
        }
        return Response.ok(reservation, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Reservation> getReservations() {
        return (List<Reservation>) reservationService.findAll();
    }

    @DELETE
    @Path("{id}")
    public Response deleteReservation(@PathParam("id") Long id) {
        try {
            reservationService.delete(id);
            return Response.status(200).entity("Reservation deleted").build();
        } catch (DataAccessException de) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Could not delete reservation for id : " + id + ", such reservation not found")
                    .build();
        }
    }

    @DELETE
    public Response deleteAllReservations() {
        try {
            reservationService.deleteAll();
            return Response.status(200).entity("All Reservations deleted").build();
        } catch (DataAccessException de) {
            return Response.status(Response.Status.NOT_FOUND).entity("There is no reservations to delete").build();
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createReservation(Reservation reservation) {
        try {
            reservationService.save(reservation);
            return Response.status(200).entity("reservation created").build();
        } catch (DataIntegrityViolationException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("such reservation already exists").build();
        }
    }


    public void setReservationService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
}
