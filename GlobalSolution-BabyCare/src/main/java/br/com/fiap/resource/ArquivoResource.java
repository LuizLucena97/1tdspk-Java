package br.com.fiap.resource;

import br.com.fiap.bo.ArquivoBo;
import br.com.fiap.models.Arquivo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/arquivo")
public class ArquivoResource {

    private ArquivoBo arquivoBO;

    public ArquivoResource() {
        this.arquivoBO = new ArquivoBo();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarArquivo(Arquivo arquivo) {
        try {
            arquivoBO.cadastrarArquivo(arquivo);
            return Response.status(Response.Status.CREATED).entity(arquivo).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao cadastrar o arquivo").build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarArquivo(@PathParam("id") int id) {
        try {
            Arquivo arquivo = arquivoBO.consultarArquivo(id);
            if (arquivo != null) {
                return Response.status(Response.Status.OK).entity(arquivo).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Arquivo não encontrado").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao consultar o arquivo").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarTodosArquivos() {
        try {
            List<Arquivo> arquivos = arquivoBO.consultarTodosArquivos();
            return Response.status(Response.Status.OK).entity(arquivos).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao consultar os arquivos").build();
        }
    }
    
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterarArquivo(@PathParam("id") int id, Arquivo novoArquivo) {
        try {
            arquivoBO.alterarArquivo(id, novoArquivo);
            return Response.status(Response.Status.OK).entity(novoArquivo).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao alterar o arquivo").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletarArquivo(@PathParam("id") int id) {
        try {
            arquivoBO.deletarArquivo(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao deletar o arquivo").build();
        }
    }
}
