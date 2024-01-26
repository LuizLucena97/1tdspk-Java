package br.com.fiap.exception;

@SuppressWarnings("serial")
public class DoacaoDaoException extends Exception {
	
	public DoacaoDaoException(String mensagem) {
        super(mensagem);
    }

    public DoacaoDaoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
