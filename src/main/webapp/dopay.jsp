<%@page import="com.yc.damai.bean.Orderitem"%>
<%@page import="com.yc.damai.bean.Product"%>
<%@page import="com.yc.damai.dao.CartitemMapper"%>
<%@page import="com.yc.damai.dao.CartMapper"%>
<%@page import="com.yc.damai.bean.Cartitem"%>
<%@page import="java.util.List"%>
<%@page import="com.yc.damai.dao.MyBatisHelper"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="com.yc.damai.bean.Orders"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	SqlSession sess =  MyBatisHelper.getSession();

	Orders order =new Orders();
	
	String count 	= request.getParameter("count");
	String soid  	= request.getParameter("order.oid");
	String stotal	= request.getParameter("order.total");
	String addr		= request.getParameter("order.addr");
	String phone	= request.getParameter("order.phone");
	String suid		= request.getParameter("order.uid");
	String name 	= request.getParameter("order.name");
	
	
	Long oid = Long.parseLong(soid);
	Long uid = Long.parseLong(suid);
	Float total = Float.parseFloat(stotal);
	Timestamp ordertime = new Timestamp(System.currentTimeMillis());
	
	
	order.setOid(oid);
	order.setTotal(total);
	order.setOrdertime(ordertime);
	order.setState(1);
	order.setAddr(addr);
	order.setName(name);
	order.setPhone(phone);
	order.setUid(uid);
	
	CartMapper cm = sess.getMapper(CartMapper.class);
	CartitemMapper cim = sess.getMapper(CartitemMapper.class);
	List<Cartitem> cartitem = cim.selectOne(uid);
	Product product = new Product();
	Orderitem oitm = new Orderitem();
	
	try{
		
		for(Cartitem cit : cartitem){
			product=sess.selectOne("com.yc.damai.dao.ProductMapper.selectByPid",cit);
			oitm.setCount(cit.getCount());
			//oitm.setOid(oid);
			oitm.setPid(cit.getPid());
			oitm.setSubtotal((float)(product.getShopPrice()*cit.getCount()));
			sess.insert("com.yc.damai.dao.OrderitemMapper.insertOderitem",oitm	);
		}
		
		
		
		
		
		sess.insert("com.yc.damai.dao.OrdersMapper.insertOder",order);
		
		sess.commit();
		
		request.getRequestDispatcher("msg.jsp").forward(request, response);
	}catch(Exception e){
		e.printStackTrace();
		sess.rollback();
		request.setAttribute("msg", "系统繁忙");
		
		request.getRequestDispatcher("pay.jsp").forward(request, response);
	}finally{
		sess.close();
	}
	
	
%>