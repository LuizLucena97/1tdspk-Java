package br.com.vistoria.resource;

import br.com.vistoria.bo.ClienteBo;
import br.com.vistoria.models.Cliente;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/clientes")
public class ClienteResource {

    private ClienteBo clienteBo = new ClienteBo();

    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrar(Cliente cliente, @Context UriInfo uriInfo) {
        clienteBo.cadastrarCliente(cliente);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(cliente.getCd_cliente()));

        return Response.created(builder.build()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente buscarPorId(@PathParam("id") int id) {
        Cliente cliente = clienteBo.buscarCliente(id);
        return cliente;
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterar(Cliente cliente, @PathParam("id") int id) {
        Cliente clienteExistente = clienteBo.buscarCliente(id);
        if (clienteExistente != null) {
            clienteBo.atualizarCliente(cliente);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluir(@PathParam("id") int id) {
        Cliente clienteExistente = clienteBo.buscarCliente(id);
        if (clienteExistente != null) {
            clienteBo.excluirCliente(id);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
