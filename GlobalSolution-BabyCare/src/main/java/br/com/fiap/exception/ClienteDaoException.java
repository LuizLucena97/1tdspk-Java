package br.com.fiap.exception;

@SuppressWarnings("serial")
public class ClienteDaoException extends Exception {

    public ClienteDaoException(String mensagem) {
        super(mensagem);
    }

    public ClienteDaoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
