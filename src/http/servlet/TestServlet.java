package http.servlet;

import java.io.PrintWriter;

import http.HttpServlet;
import http.HttpServletRequest;
import http.HttpServletResponse;

public class TestServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		/**
		 * 作业
		 */
		
		response.setContentType("text/html");
		
		PrintWriter pw = response.getWriter();
		
		pw.print("<h1>Hello world!</h1>");
		
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
	

	
}
