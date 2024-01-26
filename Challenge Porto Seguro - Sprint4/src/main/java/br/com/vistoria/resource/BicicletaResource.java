package br.com.vistoria.resource;

import br.com.vistoria.bo.BicicletaBo;
import br.com.vistoria.models.Bicicleta;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/bicicletas")
public class BicicletaResource {

    private BicicletaBo bicicletaBo = new BicicletaBo();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrar(Bicicleta bicicleta, @Context UriInfo uriInfo) {
        bicicletaBo.cadastrarBicicleta(bicicleta);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(bicicleta.getId()));

        return Response.created(builder.build()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Bicicleta> listar() {
        List<Bicicleta> bicicletas = bicicletaBo.listarBicicletas();
        return bicicletas;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Bicicleta buscarPorId(@PathParam("id") int id) {
        Bicicleta bicicleta = bicicletaBo.buscarBicicleta(id);
        return bicicleta;
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterar(Bicicleta bicicleta, @PathParam("id") int id) {
        Bicicleta bicicletaExistente = bicicletaBo.buscarBicicleta(id);
        if (bicicletaExistente != null) {
            bicicletaBo.atualizarBicicleta(bicicleta);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluir(@PathParam("id") int id) {
        Bicicleta bicicletaExistente = bicicletaBo.buscarBicicleta(id);
        if (bicicletaExistente != null) {
            bicicletaBo.excluirBicicleta(id);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}

