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
 * Todas as requisi�oes feitas no meu framework que terminam com ./action
 * s�o executadas nesse servlets, ele decide onde vai o fluxo de execu��o,
 * atrav�s do action.properties e das actions implementadas na aplica��o
 * @author Fernando
 *
 */
@WebServlet("*.action")
public class ControllerServlet extends HttpServlet {
	
	private static Properties actions;
	
	static {
		try {
			InputStream is = ControllerServlet.class.getResourceAsStream("/action.properties");// carrega o mapeamento de todas a�oes para as classes
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
		String path = request.getServletPath();//v� o caminho que foi chamado
		path = path.substring(1,path.indexOf("."));// procura a partir do 2 caracter e p�ra at� encontrar o ponto
		
		String actionClass = actions.getProperty(path); // obtem o valor associado a chave path
		
		if(actionClass == null) {
			throw new ServletException("A action '"+path+"' n�o est� mapeada");
		}
		try {
			Action action = (Action) Class.forName(actionClass).newInstance(); // instancia o objeto da a��o
			action.setRequest(request);
			action.setResponse(response);
			action.runAction();// vai disparar e executar a a��o
			
		}catch(Exception e) {
			request.setAttribute("exception", e);
			throw new ServletException(e);
		}
	}
}
