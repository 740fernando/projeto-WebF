package webf.dao;

/**
 * Classe responsável por criar  DAO. Sempre que for necessário criar um DAO, é necessario
 * solicitar a criacao para uma DAOFactory
 * Desginer Pattern utilizado - Singleton  - foi criado um construtor privado, exponho um metodo estatico que valida se a instancia for nula - > criau um objeto.
 * 
 * @author Fernando
 *
 */
public class DAOFactory {
	
	private static DAOFactory instance;
	
	private DAOFactory() {
	}
	public static DAOFactory getInstance() {
		if(instance == null) {
			instance = new DAOFactory();
		}
		return instance;
	}
	public <T extends DAO<?>> T getDAO(Class<T> clazz) throws DAOException{
		try {
			T dao = clazz.newInstance();
			return dao;
		}catch(InstantiationException e) {
			throw new DAOException(e);
		}catch(IllegalAccessException e) {
			throw new DAOException(e);
		}
	}
}
