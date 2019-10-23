package http.servlet;

import http.HttpServlet;
import http.HttpServletRequest;
import http.HttpServletResponse;

public class ForwardServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		RequestDispatcher rd = request.getRequestDispatcher("/index.html");
		rd.forward(request,response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		super.doPost(request, response);
	}

	

}
