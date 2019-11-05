package cmo.http.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import com.yc.damai.bean.User;
import com.yc.damai.dao.MyBatisHelper;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	
	public void login(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		//获取会话对象
		HttpSession session = request.getSession();
		
		String vocde = (String) session.getAttribute("captchaImage");
		
		String cvocde = request.getParameter("captcha");
		
		if(vocde.equals(cvocde)==false) {
			request.setAttribute("msg", "请输入正确的验证码");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
			
		}
		
		String username = request.getParameter("username");
		
		String password = request.getParameter("password");
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		
		SqlSession sqlsession = MyBatisHelper.getSession();
	
		user = sqlsession.selectOne("com.yc.damai.dao.UserMapper.selectLogin",user );
		
		if(user.getUid() != null ) {
			request.setAttribute("msg", "登录成功");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else {
			request.setAttribute("msg", "用户名或密码错误");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		
		}
		
	}
    

}
