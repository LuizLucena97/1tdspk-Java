package br.com.fiap.exception;

@SuppressWarnings("serial")
public class ProfissionalDaoException extends Exception {

	public ProfissionalDaoException(String mensagem) {
        super(mensagem);
    }

    public ProfissionalDaoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
    
}
