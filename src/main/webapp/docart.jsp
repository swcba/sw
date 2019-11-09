<%@page import="com.yc.damai.dao.MyBatisHelper"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="com.yc.damai.bean.Cartitem"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%	
	String cuid = request.getParameter("uid");
	String cpid = request.getParameter("pid");
	String num = request.getParameter("count");
	Integer uid = Integer.parseInt(cuid);
	Long pid = Long.parseLong(cpid);
	Integer count = Integer.parseInt(num);
	Cartitem	c = new Cartitem();
	SqlSession sess =  MyBatisHelper.getSession();
	c.setUid(uid);
	c.setCount(count);
	c.setPid(pid);

	try{
		
		sess.insert("com.yc.damai.dao.CartitemMapper.create",c);
		
		sess.commit();
		
		request.getSession().setAttribute("uid",uid);
		request.getRequestDispatcher("cart.jsp").forward(request, response);

	}catch(Exception e){
		e.printStackTrace();
		sess.rollback();
		request.setAttribute("msg", "系统繁忙");
		
		request.getRequestDispatcher("detail.jsp").forward(request, response);
	
		
	}
	
	





%>