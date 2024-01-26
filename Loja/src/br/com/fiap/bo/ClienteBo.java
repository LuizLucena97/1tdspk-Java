package br.com.fiap.bo;

import java.util.List;

import br.com.fiap.dao.ClienteDao;
import br.com.fiap.entity.Cliente;

public class ClienteBo {
	
private ClienteDao clienteDao;
	
	//inserir
	public void cadastrar(Cliente cli) {
		clienteDao = new ClienteDao();
		clienteDao.inserir(cli);
	}
	
	//buscarTodos
	public List<Cliente> buscarClientes(){
		clienteDao = new ClienteDao();
		return clienteDao.listarCliente();
	}
	
	//buscarPorId
	public boolean validarUsuario(String cpf, String senha) {
		clienteDao = new ClienteDao();
		return clienteDao.validarUsuario(cpf, senha);
	}

}
