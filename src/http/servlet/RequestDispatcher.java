package http.servlet;

import http.HttpServlet;
import http.HttpServletRequest;
import http.HttpServletResponse;

public class RequestDispatcher {

	private  String webPath;
	
	public RequestDispatcher(String webPath) {
		this.webPath=webPath;
	}
	public void forward(HttpServletRequest request,HttpServletResponse response) {
		request.setRequest(webPath);
	
	}
}
