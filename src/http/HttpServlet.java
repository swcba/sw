package http;

import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpServlet {

	public void service(HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated constructor stub)
		
		switch (request.getMethod()) {
		case "GET":
			doGet(request, response);
			break;
		case "Post":
			doPost(request, response);
			break;
		case "Put":
			doPut(request, response);
			break;
		//...
		default:
			break;
		}
		
		
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
				
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated constructor stub
	}
	public void doPut(HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated constructor stub
	}

}
