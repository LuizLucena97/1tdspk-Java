package br.com.fiap.bo;

import java.util.List;

import br.com.fiap.dao.ClienteDao;
import br.com.fiap.exception.ClienteDaoException;
import br.com.fiap.models.Cliente;

public class ClienteBo {

    private ClienteDao clienteDao;

    public void cadastrarCliente(Cliente cliente) throws ClienteDaoException {
        clienteDao = new ClienteDao();
        clienteDao.cadastrarCliente(cliente);
    }

    public void alterarCliente(Cliente cliente) throws ClienteDaoException {
        clienteDao = new ClienteDao();
        clienteDao.alterarCliente(cliente);
    }

    public Cliente consultarCliente(int cd_cliente) throws ClienteDaoException {
        clienteDao = new ClienteDao();
        return clienteDao.consultarCliente(cd_cliente);
    }

    public void deletarCliente(int cd_cliente) {
        clienteDao = new ClienteDao();
        clienteDao.deletarCliente(cd_cliente);
    }
    
    public List<Cliente> consultarTodosClientes() throws ClienteDaoException {
    	clienteDao = new ClienteDao();
        return clienteDao.consultarTodosClientes();
    }


}