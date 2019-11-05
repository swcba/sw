<%@page import="com.yc.damai.bean.Category"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="com.yc.damai.dao.MyBatisHelper"%>
<%@page import="com.yc.damai.bean.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>大麦商城</title>
<link href="css/slider.css" rel="stylesheet" type="text/css"/>
<link href="css/common.css" rel="stylesheet" type="text/css"/>
<link href="css/index.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<div class="container header">
	
	
<div class="span5">
		<div class="logo">
			<a href="index.jsp">
				<img src="image/r___________renleipic_01/logo.png" alt="依依不舍"/>
			</a>
		</div>
	</div>
	<div class="span9">
<div class="headerAd">
	<img src="image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障"/>
</div>	</div>
<div class="span10 last">
		<div class="topNav clearfix">
			<ul>
				
					<li id="headerLogin" class="headerLogin" style="display: list-item;">
						${user.getUsername()}|
					</li>
					<li id="headerLogin" class="headerLogin" style="display: list-item;">
						<a href="olist.jsp">我的订单</a>|
					</li>
					<li id="headerRegister" class="headerRegister" style="display: list-item;">
					<a href="index.jsp">退出</a>|
				</li>
				
				
				
				
				<li id="headerUsername" class="headerUsername"></li>
				<li id="headerLogout" class="headerLogout">
					<a>[退出]</a>|
				</li>
						<li>
							<a>会员中心</a>
							|
						</li>
						<li>
							<a>购物指南</a>
							|
						</li>
						<li>
							<a>关于我们</a>
							
						</li>
			</ul>
		</div>
		<div class="cart">
			<a  href="cart.html">购物车</a>
		</div>
			<div class="phone">
				客服热线:
				<strong>96008/53277764</strong>
			</div>
	</div>
	


<div class="span24">
		<ul class="mainNav">
					<li>
						<a href="index.html">首页</a>
						|
					</li>
							
		<%
		SqlSession sess =  MyBatisHelper.getSession();
		List<Category> list= sess.selectList("com.yc.damai.dao.CategoryMapper.selectAll");
		
		
		
		%>
				<%for(Category category : list  ) {%>	
					<li>
<a href="clist.html?<%=category.getCid() %>&pageIndex=1">
					<%=category.getCname() %>
</a>
					|</li>
					
					
					<%} %>		
		</ul>
		
		
	</div>


</div>	

<div class="container index">
		

		<div class="span24">
			<div id="hotProduct" class="hotProduct clearfix">
					<div class="title">
						<strong>热门商品</strong>
						<!-- <a  target="_blank"></a> -->
					</div>
					<ul class="tab">
							<li class="current">
								<a href="./蔬菜分类.htm?tagIds=1" target="_blank"></a>
							</li>
							<li>
								<a  target="_blank"></a>
							</li>
							<li>
								<a target="_blank"></a>
							</li>
					</ul>
						<ul class="tabContent" style="display: block;">
							
									<li>
										<a target="_blank" href="detail.html?1"><img src="products/1/cs10001.jpg" style="display: block;"></a>
									</li>
							
									<li>
										<a target="_blank" href="detail.html?21"><img src="products/1/cs30001.png" style="display: block;"></a>
									</li>
							
									<li>
										<a target="_blank" href="detail.html?35"><img src="products/1/cs40005.png" style="display: block;"></a>
									</li>
							
									<li>
										<a target="_blank" href="detail.html?41"><img src="products/1/cs40011.png" style="display: block;"></a>
									</li>
							
									<li>
										<a target="_blank" href="detail.html?42"><img src="products/1/cs50001.png" style="display: block;"></a>
									</li>
							
									<li>
										<a target="_blank" href="detail.html?44"><img src="products/1/cs50003.png" style="display: block;"></a>
									</li>
							
									<li>
										<a target="_blank" href="detail.html?47"><img src="products/1/cs50006.png" style="display: block;"></a>
									</li>
							
									<li>
										<a target="_blank" href="detail.html?51"><img src="products/1/cs50010.png" style="display: block;"></a>
									</li>
							
									<li>
										<a target="_blank" href="detail.html?53"><img src="products/1/cs60002.png" style="display: block;"></a>
									</li>
							
									<li>
										<a target="_blank" href="detail.html?57"><img src="products/1/cs60006.png" style="display: block;"></a>
									</li>
							
						</ul>
			</div>
		</div>
		<div class="span24">
			<div id="newProduct" class="newProduct clearfix">
					<div class="title">
						<strong>最新商品</strong>
						<a  target="_blank"></a>
					</div>
					<ul class="tab">
							<li class="current">
								<a href="./蔬菜分类.htm?tagIds=2" target="_blank"></a>
							</li>
							<li>
								<a  target="_blank"></a>
							</li>
							<li>
								<a target="_blank"></a>
							</li>
					</ul>				
						 <ul class="tabContent" style="display: block;">
						 
									<li>
										<a href="detail.html?72" target="_blank"><img src="products/1/cs10011.jpg" style="display: block;"></a>
									</li>
						
									<li>
										<a href="detail.html?57" target="_blank"><img src="products/1/cs60006.png" style="display: block;"></a>
									</li>
						
									<li>
										<a href="detail.html?4" target="_blank"><img src="products/1/cs10004.jpg" style="display: block;"></a>
									</li>
						
									<li>
										<a href="detail.html?45" target="_blank"><img src="products/1/cs50004.png" style="display: block;"></a>
									</li>
						
									<li>
										<a href="detail.html?51" target="_blank"><img src="products/1/cs50010.png" style="display: block;"></a>
									</li>
						
									<li>
										<a href="detail.html?68" target="_blank"><img src="products/1/cs70007.png" style="display: block;"></a>
									</li>
						
									<li>
										<a href="detail.html?60" target="_blank"><img src="products/1/cs60009.png" style="display: block;"></a>
									</li>
						
									<li>
										<a href="detail.html?30" target="_blank"><img src="products/1/cs30010.png" style="display: block;"></a>
									</li>
						
									<li>
										<a href="detail.html?6" target="_blank"><img src="products/1/cs10006.jpg" style="display: block;"></a>
									</li>
						
									<li>
										<a href="detail.html?2" target="_blank"><img src="products/1/cs10002.jpg" style="display: block;"></a>
									</li>
						
						</ul>
			</div>
		</div>
		<div class="span24">
			<div class="friendLink">
				<dl>
					<dd>新手指南</dd>
							<dd>
								<a  target="_blank">支付方式</a>
								|
							</dd>
							<dd>
								<a  target="_blank">配送方式</a>
								|
							</dd>
							<dd>
								<a  target="_blank">售后服务</a>
								|
							</dd>
							<dd>
								<a  target="_blank">购物帮助</a>
								|
							</dd>
							<dd>
								<a  target="_blank">蔬菜卡</a>
								|
							</dd>
							<dd>
								<a  target="_blank">礼品卡</a>
								|
							</dd>
							<dd>
								<a target="_blank">银联卡</a>
								|
							</dd>
							<dd>
								<a  target="_blank">亿家卡</a>
								|
							</dd>
							
					<dd class="more">
						<a >更多</a>
					</dd>
				</dl>
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
						<a>关于我们</a>
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
						<a target="_blank">配送方式</a>
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
		<div class="copyright">Copyright © 2005-2013 大麦商城 版权所有</div>
	</div>
</div>
</body></html>