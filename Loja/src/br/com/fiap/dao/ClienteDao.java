package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fiap.entity.Cliente;


public class ClienteDao {
	
	private Connection conexao;

	public void inserir(Cliente cliente)  {

		conexao = GerenciadorBd.obterConexao();
		PreparedStatement comandoSQL = null;
		try {
			comandoSQL = conexao.prepareStatement("insert into cliente (id_cliente,nome_cliente, cpf_cliente, email_cliente, senha) values(?,?,?,?,?)");
			comandoSQL.setInt(1, cliente.getId());
			comandoSQL.setString(2, cliente.getNome());
			comandoSQL.setString(3, cliente.getCpf());
			comandoSQL.setString(4, cliente.getEmail());
			comandoSQL.setString(5, cliente.getSenha());

			//insert into cliente (id_cliente,nome_cliente, celular_cliente, email_cliente, tipo) values(4,'Ana','11578787878', 'ana@teste.com','amiga');

			comandoSQL.executeUpdate();
			conexao.close();
			comandoSQL.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean validarUsuario(String cpf, String senha){
		
		conexao = GerenciadorBd.obterConexao();
		PreparedStatement comandoSQL = null;
		try {
			comandoSQL = conexao.prepareStatement("select * from cliente where  cpf_cliente = ? and senha=?");
			comandoSQL.setString(1, cpf);
			comandoSQL.setString(2, senha);
			ResultSet rs =  comandoSQL.executeQuery();
			if (rs.next())
			{
				return true;
			}
			conexao.close();
			comandoSQL.close();

		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}


		
	public ArrayList<Cliente> listarCliente(){

			ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
			conexao = GerenciadorBd.obterConexao();
			PreparedStatement comandoSQL = null;
			try {
				comandoSQL = conexao.prepareStatement("select * from cliente order by nome_cliente");
				
				ResultSet rs =  comandoSQL.executeQuery();
				while (rs.next())
				{
					Cliente c = new Cliente();
					c.setId(rs.getInt(1));
					c.setNome(rs.getString(2));
					c.setCpf(rs.getString(3));
					c.setEmail(rs.getString(4));
					c.setSenha(rs.getString(5));
					listaClientes.add(c);
				}
				conexao.close();
				comandoSQL.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return listaClientes;
		}

	

}
