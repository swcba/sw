<%@page import="com.yc.damai.bean.*"%>
<%@page import="com.yc.damai.dao.CartitemMapper"%>
<%@page import="com.yc.damai.dao.CartMapper"%>
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
<link href="css/common.css" rel="stylesheet" type="text/css"/>
<link href="css/cart.css" rel="stylesheet" type="text/css"/>
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
	
</div>	
<%
				List<Orders>  list2 = sess.selectList("com.yc.damai.dao.OrdersMapper.selectAll");
				Long newOderid = list2.get(list2.size()-1).getOid()+1;
				%>
<div class="container cart">

		<div class="span24">
		
			<div class="step step1">
				<ul>
					
					<li  class="current"></li>
					<li  >生成订单成功</li>
					<li  >订单号:<%=newOderid %></li>
				</ul>
			</div>
			<%
			CartMapper cm = sess.getMapper(CartMapper.class);
			CartitemMapper cim = sess.getMapper(CartitemMapper.class);
			
			String suid =session.getAttribute("uid").toString() ;
			Long uid=Long.parseLong(suid);
			//System.out.print("uid==="+uid.TYPE);
			List<Cartitem> cartitem = cim.selectOne(uid);
			
			Product product = new Product();
			
			double money =0;
			
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
					商品金额: <strong id="effectivePrice">￥<%=money %>元</strong>
				</div>
			<form id="orderForm" action="dopay.jsp" method="post">
				<input type="hidden" name="order.oid" value="<%=newOderid%>"/>
				<input type="hidden" name="order.total" value="<%=money %>"/>
				<input type="hidden" name="order.uid" value="${user.getUid() }"/>
				<div class="span24">
					<p>
							收货地址：<input name="order.addr" type="text" value="${user.getAddr()} " style="width:350px" />
								<br />
							收货人&nbsp;&nbsp;&nbsp;：<input name="order.name" type="text" value="${user.getUsername() }" style="width:150px" />
								<br /> 
							联系方式：<input name="order.phone" type="text" value="${user.getPhone() }" style="width:150px" />

						</p>
						<hr />
						<p>
							选择银行：<br/>
							<input type="radio" name="pd_FrpId" value="ICBC-NET-B2C" checked="checked"/>工商银行
							<img src="bank_img/icbc.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="pd_FrpId" value="BOC-NET-B2C"/>中国银行
							<img src="bank_img/bc.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="pd_FrpId" value="ABC-NET-B2C"/>农业银行
							<img src="bank_img/abc.bmp" align="middle"/>
							<br/>
							<input type="radio" name="pd_FrpId" value="BOCO-NET-B2C"/>交通银行
							<img src="bank_img/bcc.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="pd_FrpId" value="PINGANBANK-NET"/>平安银行
							<img src="bank_img/pingan.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="pd_FrpId" value="CCB-NET-B2C"/>建设银行
							<img src="bank_img/ccb.bmp" align="middle"/>
							<br/>
							<input type="radio" name="pd_FrpId" value="CEB-NET-B2C"/>光大银行
							<img src="bank_img/guangda.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="pd_FrpId" value="CMBCHINA-NET-B2C"/>招商银行
							<img src="bank_img/cmb.bmp" align="middle"/>
						</p>
						<hr />
						<p style="text-align:right">
							<a href="javascript:document.getElementById('orderForm').submit();">
								<img src="images/finalbutton.gif" width="204" height="51" border="0" />
							<%=request.getAttribute("msg")%>
							</a>
						</p>
				</div>
			</form>
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