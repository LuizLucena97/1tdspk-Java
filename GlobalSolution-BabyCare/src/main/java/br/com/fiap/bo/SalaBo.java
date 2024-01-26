package br.com.fiap.bo;

import java.util.List;

import br.com.fiap.dao.SalaDao;
import br.com.fiap.exception.SalaDaoException;
import br.com.fiap.models.Sala;

public class SalaBo {

	private SalaDao salaDao;

    public void alterarSala(Sala sala) throws SalaDaoException {
        salaDao = new SalaDao();
        salaDao.alterarSala(sala);
    }

    public Sala consultarSala(int sala_id) throws SalaDaoException {
        salaDao = new SalaDao();
        return salaDao.consultarSala(sala_id);
    }
    
    public List<Sala> buscarTodasSalas() throws SalaDaoException {
    	salaDao = new SalaDao();
        return salaDao.buscarTodasSalas();
    }

}
