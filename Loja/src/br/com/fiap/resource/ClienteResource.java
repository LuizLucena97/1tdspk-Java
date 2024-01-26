package br.com.fiap.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import br.com.fiap.bo.ClienteBo;
import br.com.fiap.entity.Cliente;

@Path("/clientes")
public class ClienteResource {
	

	private ClienteBo cliBo;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cliente> listar(){
		cliBo = new ClienteBo();
		return cliBo.buscarClientes();
	}
	
	//Inserir
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(Cliente cli, @Context UriInfo uriInfo) {
		cliBo = new ClienteBo();
		cliBo.cadastrar(cli);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString(cli.getId()));
		
		return Response.created(builder.build()).build();
	}
	
	//ValidarUsuario
	@GET
	@Path("/validar/{cpf}/{senha}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response validarLogin(@PathParam("cpf") String cpf, @PathParam("senha") String senha){
		cliBo = new ClienteBo();
		if(cliBo.validarUsuario(cpf, senha))
			 return Response.status(Response.Status.OK).entity("Usuário válido").build();
		else 
			return Response.status(Response.Status.UNAUTHORIZED).entity("Usuário inválido").build();
	}
	
	 @GET
	 @Path("/validar")
	 public Response validarUsuario(
	     @QueryParam("cpf") String cpf,
	     @QueryParam("senha") String senha) {

		 cliBo = new ClienteBo();
		 if(cliBo.validarUsuario(cpf, senha)) {
	            return Response.status(Response.Status.OK).entity("Usuário válido").build();
	        } else {
	            return Response.status(Response.Status.UNAUTHORIZED).entity("Usuário inválido").build();
	        }
	    }
	//http://localhost:8080/Loja/rest/clientes/validar?cpf=123456787&senha=senha123

	

}
