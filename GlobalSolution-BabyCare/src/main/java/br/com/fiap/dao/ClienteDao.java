package br.com.fiap.dao;

import br.com.fiap.exception.ClienteDaoException;
import br.com.fiap.models.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {

    private Connection conexao;

    //inserir
    
    public void cadastrarCliente(Cliente cliente) throws ClienteDaoException {
    	conexao = GerenciadorBd.obterConexao();
        PreparedStatement comandoSql = null;
        try {
        	comandoSql = conexao.prepareStatement("INSERT INTO cliente (nome_completo, email, senha, telefone, cpf, foto) VALUES (?, ?, ?, ?, ?, ?)");
            comandoSql.setString(1, cliente.getNome_completo());
            comandoSql.setString(2, cliente.getEmail());
            comandoSql.setString(3, cliente.getSenha());
            comandoSql.setString(4, cliente.getTelefone());
            comandoSql.setString(5, cliente.getCpf());
            comandoSql.setString(6, cliente.getFoto());

            comandoSql.executeUpdate();
            conexao.close();
            comandoSql.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ClienteDaoException("Erro ao cadastrar cliente.", e);
        }
    }

    // Consultar Cliente pelo ID
    
    public Cliente consultarCliente(int id) throws ClienteDaoException {
    	Cliente cliente = new Cliente();
        conexao = GerenciadorBd.obterConexao();
        PreparedStatement comandoSql = null;
        
        try {
        	comandoSql = conexao.prepareStatement("SELECT * FROM cliente WHERE cliente_id = ?");
        	comandoSql.setInt(1, id);

            ResultSet resultSet = comandoSql.executeQuery();
            if (resultSet.next()) {
                cliente = new Cliente();
                cliente.setId(resultSet.getInt("cliente_id"));
                cliente.setNome_completo(resultSet.getString("nome_completo"));
                cliente.setEmail(resultSet.getString("email"));
                cliente.setSenha(resultSet.getString("senha"));
                cliente.setTelefone(resultSet.getString("telefone"));
                cliente.setCpf(resultSet.getString("cpf"));
                cliente.setFoto(resultSet.getString("foto"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ClienteDaoException("Erro ao consultar cliente por ID.", e);
        }
        return cliente;
    }


    // alterar
    
    public void alterarCliente(Cliente cliente) throws ClienteDaoException {
    	conexao = GerenciadorBd.obterConexao();
        PreparedStatement comandoSql = null;
        try {
        	comandoSql = conexao.prepareStatement("UPDATE cliente SET nome_completo=?, email=?, senha=?, telefone=?, cpf=?, foto=? WHERE cliente_id=?");
        	comandoSql.setString(1, cliente.getNome_completo());
        	comandoSql.setString(2, cliente.getEmail());
        	comandoSql.setString(3, cliente.getSenha());
        	comandoSql.setString(4, cliente.getTelefone());
        	comandoSql.setString(5, cliente.getCpf());
        	comandoSql.setString(6, cliente.getFoto());
        	comandoSql.setInt(7, cliente.getId());

        	comandoSql.executeUpdate();
            conexao.close();
            comandoSql.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ClienteDaoException("Erro ao alterar cliente.", e);
        }
    }

    // Deletar um Cliente pelo ID
    
    public void deletarCliente(int id){
    	conexao = GerenciadorBd.obterConexao();
        PreparedStatement comandoSql = null;
        try  {
        	comandoSql = conexao.prepareStatement("DELETE FROM cliente WHERE cliente_id=?");
            comandoSql.setInt(1, id);
            comandoSql.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
 // Consultar todos os clientes
    
    public List<Cliente> consultarTodosClientes() throws ClienteDaoException {
        List<Cliente> listaClientes = new ArrayList<>();
        conexao = GerenciadorBd.obterConexao();
        PreparedStatement comandoSql = null;

        try {
            comandoSql = conexao.prepareStatement("SELECT * FROM cliente");
            ResultSet resultSet = comandoSql.executeQuery();

            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(resultSet.getInt("cliente_id"));
                cliente.setNome_completo(resultSet.getString("nome_completo"));
                cliente.setEmail(resultSet.getString("email"));
                cliente.setSenha(resultSet.getString("senha"));
                cliente.setTelefone(resultSet.getString("telefone"));
                cliente.setCpf(resultSet.getString("cpf"));
                cliente.setFoto(resultSet.getString("foto"));

                listaClientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ClienteDaoException("Erro ao consultar cliente por ID.", e);
        } finally {
            try {
                if (comandoSql != null) {
                    comandoSql.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return listaClientes;
    }

}
