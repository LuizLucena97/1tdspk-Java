package br.com.fiap.bo;

import br.com.fiap.dao.ArquivoDao;
import br.com.fiap.exception.ArquivoDaoException;
import br.com.fiap.models.Arquivo;

import java.util.List;

public class ArquivoBo {

    private ArquivoDao arquivoDao;

    public void cadastrarArquivo(Arquivo arquivo) throws ArquivoDaoException {
    	arquivoDao = new ArquivoDao();
        arquivoDao.cadastrarArquivo(arquivo);
    }

    public Arquivo consultarArquivo(int id) throws ArquivoDaoException {
    	arquivoDao = new ArquivoDao();
        return arquivoDao.consultarArquivo(id);
    }

    public List<Arquivo> consultarTodosArquivos() throws ArquivoDaoException {
    	arquivoDao = new ArquivoDao();
        return arquivoDao.consultarTodosArquivos();
    }
    
    public void alterarArquivo(int id, Arquivo novoArquivo) throws ArquivoDaoException {
    	arquivoDao = new ArquivoDao();
        arquivoDao.alterarArquivo(id, novoArquivo);
    }

    public void deletarArquivo(int id) throws ArquivoDaoException {
    	arquivoDao = new ArquivoDao();
        arquivoDao.deletarArquivo(id);
    }

    // Outras operações de negócios podem ser adicionadas conforme necessário
}
