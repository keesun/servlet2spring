package whiteship;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServletModel2 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("hello", "Hello Servlet&JSP");
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/hello.jsp");
		dispatcher.forward(req, resp);
	}
	
}
