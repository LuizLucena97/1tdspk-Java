package br.com.fiap.exception;

@SuppressWarnings("serial")
public class ArquivoDaoException extends Exception {

	public ArquivoDaoException(String mensagem) {
        super(mensagem);
    }

    public ArquivoDaoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
