package webf.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webf.action.Action;
/**
 * Todas as requisiçoes feitas no meu framework que terminam com ./action
 * são executadas nesse servlets, ele decide onde vai o fluxo de execução,
 * através do action.properties e das actions implementadas na aplicação
 * @author Fernando
 *
 */
@WebServlet("*.action")
public class ControllerServlet extends HttpServlet {
	
	private static Properties actions;
	
	static {
		try {
			InputStream is = ControllerServlet.class.getResourceAsStream("/action.properties");// carrega o mapeamento de todas açoes para as classes
			actions = new Properties();
			actions.load(is);
			is.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		process(request,response);
	}
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		String path = request.getServletPath();//vê o caminho que foi chamado
		path = path.substring(1,path.indexOf("."));// procura a partir do 2 caracter e pára até encontrar o ponto
		
		String actionClass = actions.getProperty(path); // obtem o valor associado a chave path
		
		if(actionClass == null) {
			throw new ServletException("A action '"+path+"' não está mapeada");
		}
		try {
			Action action = (Action) Class.forName(actionClass).newInstance(); // instancia o objeto da ação
			action.setRequest(request);
			action.setResponse(response);
			action.runAction();// vai disparar e executar a ação
			
		}catch(Exception e) {
			request.setAttribute("exception", e);
			throw new ServletException(e);
		}
	}
}
