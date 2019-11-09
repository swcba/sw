<%@page import="com.yc.damai.bean.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.yc.damai.bean.Cartitem"%>
<%@page import="com.yc.damai.dao.CartitemMapper"%>
<%@page import="com.yc.damai.dao.CartMapper"%>
<%@page import="com.yc.damai.bean.Category"%>
<%@page import="java.util.List"%>
<%@page import="com.yc.damai.dao.MyBatisHelper"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>大麦商城</title>
<link href="css/common.css" rel="stylesheet" type="text/css">
<link href="css/cart.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/common.js"></script>

</head>
<body>
<div class="container header">
	
<%@include file="header.jsp" %>	
	


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


</div>	<div class="container cart">
		<div class="span24">
		
		
		<div class="step step1">
				购物车列表
			</div>
			<%
			CartMapper cm = sess.getMapper(CartMapper.class);
			CartitemMapper cim = sess.getMapper(CartitemMapper.class);
			
			String suid =session.getAttribute("uid").toString() ;
			Long uid=Long.parseLong(suid);
			System.out.print("uid==="+uid.TYPE);
			List<Cartitem> cartitem = cim.selectOne(uid);
			
			Product product = new Product();
			double money=0;
			
			
			%>
				<table>
				<%if(cartitem!=null){%>
					
					
				
					<tbody>
					<tr>
						<th>图片</th>
						<th>商品</th>
						<th>价格</th>
						<th>数量</th>
						<th>小计</th>
						<th>操作</th>
					</tr>
					<% for(Cartitem cit : cartitem){
						product=sess.selectOne("com.yc.damai.dao.ProductMapper.selectByPid",cit);
						
					%>
						<tr>
							<td width="60">
								<img src="<%=product.getImage()%>">
							</td>
							<td>
								<a target="_blank"><%=product.getPname() %></a>
							</td>
							<td>
								￥<%=product.getShopPrice() %>
							</td>
							<td class="quantity" width="60">
								<%=cit.getCount() %>
							</td>
							<td width="140">
							<%double price = (cit.getCount())*(product.getShopPrice());   %>
								<span class="subtotal">￥<%=price %></span>
							<%money+=price; %>
							</td>
							<td>
								<a href="cart.jsp?pid=1" class="delete">删除</a>
							</td>
						</tr>
				<%} %>
				
						
				</tbody>
				<% } %>
				</table>
				
				<dl id="giftItems" class="hidden" style="display: none;">
				</dl>
				<div class="total">
					<em id="promotion"></em>
							<em>
								登录后确认是否享有优惠
							</em>
					赠送积分: <em id="effectivePoint"><%=money %></em>
					商品金额: <strong id="effectivePrice"><%=money %></strong>
				</div>
				<div class="bottom">
					<a href="cart.jsp" id="clear" class="clear">清空购物车</a>
					<a href="pay.jsp" id="submit" class="submit">提交订单</a>
				</div>
			
		</div>
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势">
</div>	</div>
	<div class="span24">
		<ul class="bottomNav">
				<li>
						<a >关于我们</a>
						|
					</li>
					<li>
						<a>联系我们</a>
						|
					</li>
					<li>
						<a>招贤纳士</a>
						|
					</li>
					<li>
						<a>法律声明</a>
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
						<a  target="_blank">配送方式</a>
						|
					</li>
					<li>
						<a>服务声明</a>
						|
					</li>
					<li>
						<a>广告声明</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 2005-2013 Mango商城 版权所有</div>
	</div>
</div>
</body></html>