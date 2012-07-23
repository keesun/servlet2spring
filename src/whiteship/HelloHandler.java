package whiteship;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloHandler implements Handler {

	@Override
	public String handle(HttpServletRequest req, HttpServletResponse res) {
		req.setAttribute("hello", "Hello Whiteship");
		return "/WEB-INF/views/hello.jsp";
	}

}
