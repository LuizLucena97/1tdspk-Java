package br.com.fiap.bo;

import java.util.List;

import br.com.fiap.dao.ProdutoDao;
import br.com.fiap.entity.Produto;

public class ProdutoBo {

	private ProdutoDao produtoDao;
	
	//inserir
	public void cadastrar(Produto prod) {
		produtoDao = new ProdutoDao();
		produtoDao.inserir(prod);
	}
	
	//buscarTodos
	public List<Produto> buscarProdutos(){
		produtoDao = new ProdutoDao();
		return produtoDao.buscarTodosProdutos();
	}
	
	//buscarPorId
	public Produto buscarProdutoPorCodigo(int codigo) {
		produtoDao = new ProdutoDao();
		return produtoDao.buscarProdutoPorId(codigo);
	}
	
	//alterar
	public void alterar(Produto prod) {
		produtoDao = new ProdutoDao();
		produtoDao.atualizar(prod);
	}
	
}
