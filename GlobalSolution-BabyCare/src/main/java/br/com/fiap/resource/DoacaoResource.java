package br.com.fiap.resource;

import br.com.fiap.bo.DoacaoBo;
import br.com.fiap.exception.DoacaoDaoException;
import br.com.fiap.models.Doacao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/doacao")
public class DoacaoResource {

	private DoacaoBo doacaoBo;

    public DoacaoResource() {
        this.doacaoBo = new DoacaoBo();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarDoacao(Doacao doacao) throws SQLException, DoacaoDaoException {
        DoacaoBo doacaoBo = new DoacaoBo();
        doacaoBo.cadastrarDoacao(doacao);
        return Response.status(Response.Status.CREATED).entity("Doação cadastrada com sucesso.").build();
    }

}