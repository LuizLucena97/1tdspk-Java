package br.com.fiap.resource;

import br.com.fiap.bo.ProfissionalBo;
import br.com.fiap.exception.ProfissionalDaoException;
import br.com.fiap.models.Profissional;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

@Path("/profissional")
public class ProfissionalResource {

    private ProfissionalBo profissionalBo;

    public ProfissionalResource() {
        this.profissionalBo = new ProfissionalBo();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Profissional> listarProfissionais() throws ProfissionalDaoException {
        List<Profissional> profissionais = profissionalBo.listarProfissionais();
        return profissionais;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarProfissional(@PathParam("id") int id) throws ProfissionalDaoException {
        Profissional profissional = profissionalBo.consultarProfissional(id);
        if (profissional != null) {
            return Response.status(Response.Status.OK).entity(profissional).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}