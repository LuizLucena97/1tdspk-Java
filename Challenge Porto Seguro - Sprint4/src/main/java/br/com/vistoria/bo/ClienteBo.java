package br.com.vistoria.bo;
import br.com.vistoria.dao.ClienteDao;
import br.com.vistoria.models.Cliente;

public class ClienteBo {

    private ClienteDao clienteDao;

    public void cadastrarCliente(Cliente cliente) {
        clienteDao = new ClienteDao();
        clienteDao.cadastrarCliente(cliente);
    }

    public void atualizarCliente(Cliente cliente) {
        clienteDao = new ClienteDao();
        clienteDao.atualizarCliente(cliente);
    }

    public Cliente buscarCliente(int cd_cliente) {
        clienteDao = new ClienteDao();
        return clienteDao.buscarCliente(cd_cliente);
    }

    public void excluirCliente(int cd_cliente) {
        clienteDao = new ClienteDao();
        clienteDao.excluirCliente(cd_cliente);
    }

}
