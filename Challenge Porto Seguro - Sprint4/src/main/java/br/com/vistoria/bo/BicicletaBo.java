package br.com.vistoria.bo;

import br.com.vistoria.dao.BicicletaDao;
import br.com.vistoria.models.Bicicleta;

import java.util.List;

public class BicicletaBo {

    private BicicletaDao bicicletaDao;

    public void cadastrarBicicleta(Bicicleta bicicleta) {
        bicicletaDao = new BicicletaDao();
        bicicletaDao.cadastrarBicicleta(bicicleta);
    }

    public List<Bicicleta> listarBicicletas() {
        bicicletaDao = new BicicletaDao();
        return bicicletaDao.listarBicicletas();
    }

    public Bicicleta buscarBicicleta(int id) {
        bicicletaDao = new BicicletaDao();
        return bicicletaDao.buscarBicicleta(id);
    }

    public void atualizarBicicleta(Bicicleta bicicleta) {
        bicicletaDao = new BicicletaDao();
        bicicletaDao.atualizarBicicleta(bicicleta);
    }

    public void excluirBicicleta(int id) {
        bicicletaDao = new BicicletaDao();
        bicicletaDao.excluirBicicleta(id);
    }
}
