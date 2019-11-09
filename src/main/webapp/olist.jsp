<%@page import="com.yc.damai.bean.Category"%>
<%@page import="java.util.List"%>
<%@page import="com.yc.damai.bean.Orderitem"%>
<%@page import="com.yc.damai.bean.Orders"%>
<%@page import="com.yc.damai.bean.User"%>
<%@page import="com.yc.damai.dao.MyBatisHelper"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>大麦商城</title>
<link href="css/common.css" rel="stylesheet" type="text/css"/>
<link href="css/cart.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<div class="container header">
	
<%@include file="header.jsp" %>
	<!-- 菜单栏 -->
	

<div class="span24">
		<ul class="mainNav">
					<li>
						<a href="index.jsp">首页</a>
						|
					</li>
							
		<%
		SqlSession sess =  MyBatisHelper.getSession();
		List<Category> list= sess.selectList("com.yc.damai.dao.CategoryMapper.selectAll");
		
		
		
		%>
				<%for(Category category : list  ) {%>	
					<li>
<a href="clist.jsp?<%=category.getCid() %>&pageIndex=1">
					<%=category.getCname() %>
</a>
					|</li>
					
					
					<%} %>
		</ul>
	</div>
	
</div>	

	
</div>	

<div class="container cart">

		<div class="span24">
		
			<div class="step step1">
				<ul>
					
					<li  class="current"></li>
					<li  >订单列表</li>
				</ul>
			</div>
			<%
			

			User user = sess.selectOne("com.yc.damai.dao.UserMapper.selectById",4);
			
			
			%>
			
			<%for(Orders o : user.getOrders()){ %>
				<table>
					<tbody>
					
					<tr>
						<th colspan="5">
						订单号:<%=o.getOid() %> 
						金额:<font color="red"><%=o.getTotal() %> </font>
						状态 :
							<a href="pay.html?oid=<%=o.getOid() %> "><font color="red"><%=o.getState() %></font></a>
						
						
						
						
						</th>
					</tr>
				
					<tr>
						<th>图片</th>
						<th>商品</th>
						<th>价格</th>
						<th>数量</th>
						<th>小计</th>
					</tr>
						<%for(Orderitem oi : o.getItems()){ %>
						<tr>
							<td width="60">
								<img src="<%=oi.getProduct().getImage() %> "/>
							</td>
							<td>
								<a target="_blank"><%=oi.getProduct().getPname() %></a>
							</td>
							<td>
								<%=oi.getSubtotal() %>
							</td>
							<td class="quantity" width="60">
									<%=oi.getCount() %>							
							</td>
							<td width="140">
								<span class="subtotal"><%=oi.getProduct().getShopPrice() %></span>
							</td>
						</tr>
						<%} %>
					<%} %>
				</tbody>
				
			</table>
				
			
		</div>
		
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="image\r___________renleipic_01/footer.jpg" alt="我们的优势" title="我们的优势" height="52" width="950">
</div>
</div>
	<div class="span24">
		<ul class="bottomNav">
					<li>
						<a href="#">关于我们</a>
						|
					</li>
					<li>
						<a href="#">联系我们</a>
						|
					</li>
					<li>
						<a href="#">诚聘英才</a>
						|
					</li>
					<li>
						<a href="#">法律声明</a>
						|
					</li>
					<li>
						<a>友情链接</a>
						|
					</li>
					<li>
						<a target="_blank">支付方式</a>
						|
					</li>
					<li>
						<a target="_blank">配送方式</a>
						|
					</li>
					<li>
						<a >SHOP++官网</a>
						|
					</li>
					<li>
						<a>SHOP++论坛</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 2005-2013 Mango商城 版权所有</div>
	</div>
</div>
</body>
</html>