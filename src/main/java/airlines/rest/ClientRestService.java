package airlines.rest;

import airlines.model.Client;
import airlines.service.ClientService;
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
@Path("/clients")
public class ClientRestService {

    private static Logger logger = Logger.getLogger(ClientRestService.class);

    @Inject
    private ClientService clientService;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClient(@PathParam("id") long id) {
        Client client = clientService.findOne(id);
        if (client == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Client not found for id : " + id).build();
        }
        return Response.ok(client, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Client> getClients() {
        return (List<Client>) clientService.findAll();
    }

    @DELETE
    @Path("{id}")
    public Response deleteClient(@PathParam("id") long id) {
        try {
            clientService.delete(id);
            return Response.status(200).entity("Client deleted").build();
        } catch (DataAccessException de) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Could not delete client for id : " + id + ", such client not found")
                    .build();
        }
    }

    @DELETE
    public Response deleteAllClients() {
        try {
            clientService.deleteAll();
            return Response.status(200).entity("All clients deleted").build();
        } catch (DataAccessException de) {
            return Response.status(Response.Status.NOT_FOUND).entity("There is no client to delete").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createClient(Client client) {
        try {
            if (client.getId() != null)
                return Response.status(Response.Status.BAD_REQUEST).entity("Specifying ID in POST forbidden!! Live it blank").build();
            clientService.save(client);
        } catch (DataIntegrityViolationException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Client with this PESEL/EMAIL  already exists").build();
        }
        return Response.status(200).entity("Client created").build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response updateClient(@PathParam("id") long id, Client client) {
        clientService.update(id, client);
        return Response.status(200).entity("Client updated").build();
    }

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }
}
