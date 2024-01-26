package br.com.fiap.bo;

import br.com.fiap.dao.ProfissionalDao;
import br.com.fiap.exception.ProfissionalDaoException;
import br.com.fiap.models.Profissional;

import java.util.List;

public class ProfissionalBo {

    private ProfissionalDao profissionalDao;

    // Consultar um Profissional pelo ID
    public Profissional consultarProfissional(int id) throws ProfissionalDaoException {
    	profissionalDao = new ProfissionalDao();
        return profissionalDao.consultarProfissional(id);
    }

    // Listar todos os Profissionais
    public List<Profissional> listarProfissionais() throws ProfissionalDaoException {
    	profissionalDao = new ProfissionalDao();
        return profissionalDao.listarProfissionais();
    }

}
