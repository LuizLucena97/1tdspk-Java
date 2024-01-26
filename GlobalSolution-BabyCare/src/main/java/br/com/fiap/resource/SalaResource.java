package br.com.fiap.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.fiap.bo.SalaBo;
import br.com.fiap.exception.SalaDaoException;
import br.com.fiap.models.Sala;

@Path("/sala")
public class SalaResource {
	
	private SalaBo salaBo;

    public SalaResource() {
        this.salaBo = new SalaBo();
    }
	
	@PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterarSala(Sala sala) throws SalaDaoException {
        salaBo.alterarSala(sala);
        return Response.status(Response.Status.OK).build();
    }
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sala> buscarTodasSalas() throws SalaDaoException {
        List<Sala> salas = salaBo.buscarTodasSalas();
        return salas;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarSala(@PathParam("id") int id) throws SalaDaoException {
        Sala sala = salaBo.consultarSala(id);
        if (sala != null) {
            return Response.status(Response.Status.OK).entity(sala).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
