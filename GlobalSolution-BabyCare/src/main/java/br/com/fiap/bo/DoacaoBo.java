package br.com.fiap.bo;

import br.com.fiap.dao.DoacaoDao;
import br.com.fiap.exception.DoacaoDaoException;
import br.com.fiap.models.Doacao;


public class DoacaoBo {

    private DoacaoDao doacaoDAO;

    // Cadastrar
    public void cadastrarDoacao(Doacao doacao) throws DoacaoDaoException {
    	doacaoDAO = new DoacaoDao();
        doacaoDAO.cadastrarDoacao(doacao);
    }

}
