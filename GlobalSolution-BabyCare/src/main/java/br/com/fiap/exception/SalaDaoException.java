package br.com.fiap.exception;

@SuppressWarnings("serial")
public class SalaDaoException extends Exception {

	public SalaDaoException(String mensagem) {
        super(mensagem);
    }

    public SalaDaoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
