package webf.service;

import webf.dao.DAOFactory;
/**
 * Super classe Service
 * 
 * @author Fernando
 *
 */
public abstract class Service {
	
	protected ServiceFactory serviceFactory;
	protected DAOFactory daoFactory;
	
	protected Service() {
		this.serviceFactory = ServiceFactory.getInstance();
		this.daoFactory = DAOFactory.getInstance();
	}
}
