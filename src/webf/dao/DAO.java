package webf.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;



/**
 * Super classe de todas os daos que forem criados na aplicação
 * 
 *@author Fernando
 */

public abstract class DAO<T> {

	private Session session;
	private Class<T> clazz;
	
	protected DAO(Class<T> clazz) {
		session = HibernateUtil.getSession();
		this.clazz = clazz;
	}
	
	@SuppressWarnings("unchecked")
	public T load(Serializable id) throws DAOException{
		try {
			return (T) session.load(clazz,id);
		}catch(HibernateException e) {
			throw new DAOException(e);
		}
	}
	public void save(T obj) throws DAOException {
		try {
			session.save(obj);
		}catch (HibernateException e) {
			throw new DAOException(e);
		}
	}
	public void delete(T obj) throws DAOException {
		try {
			session.delete(obj);
		}catch (HibernateException e) {
			throw new DAOException(e);
		}
	}
	public void update(T obj) throws DAOException{
		try {
			session.update(obj);
		}catch(HibernateException e) {
			throw new DAOException(e);
		}
	}
	protected List<?> list(String hql) throws DAOException {
		try {
			Query q = query(hql);
			return q.list();
		}catch ( HibernateException e) {
			throw new DAOException(e);
		}		
	}
	@SuppressWarnings("unchecked")
	protected T result(String hql, Class<T> clazz) throws DAOException{
		List<?> results = list(hql);
		if(results.siza()==0) {
			return null;
		}
		return (T) results.get(0);
	}
	
	protected Query query(String hql) throws DAOException {
		try {
			return session.createQuery(hql);
		}catch(HibernateException e) {
			throw new DAOException(e);
		}
	}
}
