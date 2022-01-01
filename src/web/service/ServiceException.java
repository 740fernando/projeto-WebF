package web.service;

/**
 * 
 * Classe que herda de Exception - O objetivo é identificar problemas de Service
 * 
 * @author Fernando
 *
 */

public class ServiceException extends Exception {
	
	public ServiceException() {
	}
	
	public ServiceException(String message, Throwable cause) {
		super(message,cause);
	}
	public ServiceException(String message) {
		super(message);
	}
	public ServiceException(Throwable cause) {
		super(cause);
	}
}
