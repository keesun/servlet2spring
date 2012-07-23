package whiteship;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WhiteshipServlet extends HttpServlet {

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		processRequest(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		processRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		processRequest(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		processRequest(req, resp);
	}
	
	protected void processRequest(HttpServletRequest req, HttpServletResponse res) {		
		try {
			Handler handler = findHandler(req);
			String view = execute(handler, req, res);
			dispatch(view, req, res);
		} catch (Exception e) {
			exceptionHandle(e, req, res);
		}
	}

	private void exceptionHandle(Exception e, HttpServletRequest req,
			HttpServletResponse res) {
		e.printStackTrace();
	}

	private void dispatch(String view, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher(view);
		dispatcher.forward(req, res);
	}

	private String execute(Handler handler, HttpServletRequest req, HttpServletResponse res) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Method method = handler.getClass().getMethod("handle", HttpServletRequest.class, HttpServletResponse.class);
		return (String) method.invoke(handler, req, res);
	}

	private Handler findHandler(HttpServletRequest req) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String uri = req.getRequestURI();
		String handlerName = "whiteship." + uri.substring(uri.lastIndexOf("/") + 1) + "Handler";
		Class handlerClass = Class.forName(handlerName);
		return (Handler) handlerClass.newInstance();
	}
	
}
