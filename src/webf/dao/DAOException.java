package webf.dao;
/**
 * 
 * Classe que herda de Exception - O objetivo � identificar problemas de DAO
 * Encapsula de niveis mais baixo - neste caso, o hibernate- gerenciador de persistencia
 * e lan�a DAOException, uma excess�o gen�rica
 * 
 * @author Fernando
 *
 */
public class DAOException extends Exception {

	public DAOException() {
	}
	public DAOException(String message, Throwable cause) {
		super(message,cause);
	}
	public DAOException(String message) {
		super(message);
	}
	public DAOException(Throwable cause) {
		super(cause);
	}
}

