package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import br.com.fiap.entity.Produto;

public class ProdutoDao {
	
	private Connection conexao;
	
	//inserir
	public void inserir(Produto prod) {
		conexao = GerenciadorBd.obterConexao();
		PreparedStatement comandoSql = null;
		try {
			comandoSql = conexao.prepareStatement("insert into produtos (codigo,titulo,preco,quantidade,descricao)"
					+ " values(?, ?, ?, ?,?);");
			comandoSql.setInt(1, prod.getCodigo());
			comandoSql.setString(2, prod.getTitulo());
			comandoSql.setDouble(3, prod.getPreco());
			comandoSql.setInt(4, prod.getQuantidade());
			comandoSql.setString(5, prod.getDescricao());
			comandoSql.executeUpdate();
			conexao.close();
			comandoSql.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//alterar
		public void atualizar(Produto prod) {
			conexao = GerenciadorBd.obterConexao();
			PreparedStatement comandoSql = null;
			try {
				comandoSql = conexao.prepareStatement("update produtos set titulo=?, preco=?, quantidade=?, descricao=?"
						+ " where codigo=?;");
				
				comandoSql.setString(1, prod.getTitulo());
				comandoSql.setDouble(2, prod.getPreco());
				comandoSql.setInt(3, prod.getQuantidade());
				comandoSql.setString(4, prod.getDescricao());
				comandoSql.setInt(5, prod.getCodigo());
				comandoSql.executeUpdate();
				conexao.close();
				comandoSql.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	//buscarTodos
	public List<Produto> buscarTodosProdutos(){
		List<Produto> listaProdutos = new ArrayList<Produto>();
		conexao = GerenciadorBd.obterConexao();
		PreparedStatement comandoSql = null;
		try {
			comandoSql = conexao.prepareStatement("select * from produtos");
			ResultSet rs = comandoSql.executeQuery();
			while(rs.next()) {
				Produto p = new Produto();
				p.setCodigo(rs.getInt(1));
				p.setTitulo(rs.getString(2));
				p.setPreco(rs.getDouble(3));
				p.setQuantidade(rs.getInt(4));
				p.setDescricao(rs.getString(5));
				listaProdutos.add(p);
			}
			conexao.close();
			comandoSql.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaProdutos;
	}
	
	//buscarPorId
	public Produto buscarProdutoPorId(int id){
		Produto p = new Produto();
		conexao = GerenciadorBd.obterConexao();
		PreparedStatement comandoSql = null;
		try {
			comandoSql = conexao.prepareStatement("select * from produtos where codigo = ?");
			comandoSql.setInt(1, id);
			ResultSet rs = comandoSql.executeQuery();
			if(rs.next()) {
				p.setCodigo(rs.getInt(1));
				p.setTitulo(rs.getString(2));
				p.setPreco(rs.getDouble(3));
				p.setQuantidade(rs.getInt(4));
				p.setDescricao(rs.getString(5));
			}
			conexao.close();
			comandoSql.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

}
