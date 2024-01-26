package br.com.fiap.resource;

import br.com.fiap.bo.ClienteBo;
import br.com.fiap.exception.ClienteDaoException;
import br.com.fiap.models.Cliente;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/cliente")
public class ClienteResource {

    private ClienteBo clienteBo;

    public ClienteResource() {
        this.clienteBo = new ClienteBo();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarCliente(Cliente cliente) throws ClienteDaoException {
        clienteBo.cadastrarCliente(cliente);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterarCliente(Cliente cliente) throws ClienteDaoException {
        clienteBo.alterarCliente(cliente);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarCliente(@PathParam("id") int cdCliente) throws ClienteDaoException {
        Cliente cliente = clienteBo.consultarCliente(cdCliente);
        if (cliente != null) {
            return Response.status(Response.Status.OK).entity(cliente).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletarCliente(@PathParam("id") int cdCliente) {
        clienteBo.deletarCliente(cdCliente);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> consultarTodosClientes() throws ClienteDaoException {
        List<Cliente> clientes = clienteBo.consultarTodosClientes();
        return clientes;
    }
}
