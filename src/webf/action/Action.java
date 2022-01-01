package webf.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Classe Action - Super classe de todas actions que forem criadas na aplicação
 * @author Fernando
 *
 */
public abstract class Action {
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	protected ServiceFactory serviceFactory;// cria objetos services

	protected Action() {
		this.serviceFactory = ServiceFactory.getInstance();
	}
	//runAction() - executa toda operação transacional
	public void runAction() throws Exception{
		try {
			HibernateUtil.beginTransaction();
			process();
			HibernateUtil.commitTransaction();
		}catch (Excetion e) {
			Hibernateutil.rollbackTransaction();
			throw e;
		}
	}
	public abstract void process() throws Exception;
	
	//redirecionamento interno
	protected void forward(String path) throws Exception{
		request.getRequestDispatcher(path).forward(request, response);
	}	
	//redirecionamento externo
	protected void redirect(String path)throws Exception {
		String contextRoot = request.getContextPath();
		response.sendRedirect(contextRoot+"/"+path);
	}
	protected HttpSession getSession() {
		return request.getSession();
	}
	protected HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	protected HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
}
