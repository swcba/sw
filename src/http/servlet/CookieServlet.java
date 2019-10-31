package http.servlet;

import java.io.PrintWriter;

import http.HttpServlet;
import http.HttpServletRequest;
import http.HttpServletResponse;
/**
 * 实现响应输出流 输出 html 代码
 */
public class CookieServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		/**
		 * 作业
		 */
		
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter pw = response.getWriter();
		
		pw.print("<h1>测试cookie!</h1>");
		
		Cookie cookie = new Cookie("username","zhangsan");
		response.addCookie(cookie);
		
		cookie = new Cookie("level","100");
		cookie.setMaxAge(60*60);
		response.addCookie(cookie);
		
		cookie = new Cookie("page","1");
		cookie.setPath("/page");
		response.addCookie(cookie);
		
		cookie = new Cookie("user","test");
		cookie.setPath("/user");
		response.addCookie(cookie);
		
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
	

	
}
