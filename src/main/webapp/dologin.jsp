<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="com.yc.damai.dao.MyBatisHelper"%>
<%@page import="com.yc.damai.bean.User"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>执行登录</title>

	<%
	String rvocde = (String) session.getAttribute("captchaImage");
	
	String cvocde = request.getParameter("captcha");
	
	/*if(cvocde.equals(rvocde)==false) {
		request.setAttribute("msg", "请输入正确的验证码");
		request.getRequestDispatcher("login.jsp").forward(request, response);
			
	}*/

	String username = request.getParameter("username");
	
	String password = request.getParameter("password");
	User user = new User();
	user.setUsername(username);
	user.setPassword(password);
	
	SqlSession sqlsession = MyBatisHelper.getSession();

	List<User> list = sqlsession.selectList("com.yc.damai.dao.UserMapper.selectLogin",user );
	System.out.print(list.get(0).toString());
	if(list.size()!=0 ) {
		request.setAttribute("msg", "登录成功");
		request.getSession().setAttribute("user", list.get(0));
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}else {
		request.setAttribute("msg", "用户名或密码错误");
		
		request.getRequestDispatcher("login.jsp").forward(request, response);
	
	}
	%>