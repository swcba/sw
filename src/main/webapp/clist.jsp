<%@page import="com.yc.damai.bean.Product"%>
<%@page import="com.yc.damai.bean.Categorysecond"%>
<%@page import="java.util.List"%>
<%@page import="com.yc.damai.dao.MyBatisHelper"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="com.yc.damai.bean.Category"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>大麦商城</title>
<link href="css/common.css" rel="stylesheet" type="text/css"/>
<link href="css/product.css" rel="stylesheet" type="text/css"/>
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
<div class="container productList">
	

		<div class="span6">
			<div class="hotProductCategory">
			<%for(Category category : list  ) {%>
						<dl>
							<dt>
								<a href="clist.jsp?cid=<%=category.getCid() %>"><%=category.getCname() %></a>
							</dt>
									<%for(Categorysecond cas : category.getCsItems()) {%>
									<dd>
										<a href="clist.jsp?csid=<%=cas.getCsid() %>"><%=cas.getCsname() %></a>
									</dd>
							
									
							<%} %>
						</dl>
			<%} %>
						
			
		</div>
</div>
		
		<div class="span18 last">
			
			<form id="productForm" action="image/蔬菜 - Powered By Mango Team.htm" method="get">
				<input type="hidden" id="brandId" name="brandId" value=""/>
				<input type="hidden" id="promotionId" name="promotionId" value=""/>
				<input type="hidden" id="orderType" name="orderType" value=""/>
				<input type="hidden" id="pageNumber" name="pageNumber" value="1"/>
				<input type="hidden" id="pageSize" name="pageSize" value="20"/>
					
				<div id="result" class="result table clearfix">
						<ul>
						<%
						Product product = new Product();
						//String s=request.getParameter("csid");
						if(request.getParameter("csid")!=null){
						
							int csid = Integer.parseInt(request.getParameter("csid"));
							product.setCsid(csid);
						}
						
						
						
						
						List<Product> list2 = sess.selectList("com.yc.damai.dao.ProductMapper.selectByObj",product);
						//System.out.print(list2.size());
						
						
						%>
						<%for(Product p : list2){ %>
						<li>
										<a href="detail.jsp?pid=<%=p.getPid()%>">
											<img src="<%=p.getImage() %>" width="170" height="170"  style="display: inline-block;"/>
											   
											<span style='color:green'>
										<%=p.getPname() %>
											</span>
											 
											<span class="price">
												商城价： ￥<%=p.getShopPrice() %>/份
											</span>
											 
										</a>
						</li>
						
						<%} %>
						</ul>
				</div>
	
	<div class="pagination">
	第1/6
	
	
				
					<span class="currentPage">1</span>
				
				
			
				
				
					<a href="clist.jsp?1&pageIndex=2">2</a>
				
			
				
				
					<a href="clist.jsp?1&pageIndex=3">3</a>
				
			
				
				
					<a href="clist.jsp?1&pageIndex=4">4</a>
				
			
				
				
					<a href="clist.jsp?1&pageIndex=5">5</a>
				
			
				
				
					<a href="clist.jsp?1&pageIndex=6">6</a>
				
			
				
			
				<a class="nextPage" href="clist.jsp?1&pageIndex=2">&nbsp;</a>
				<a class="lastPage" href="clist.jsp?1&pageIndex=6">&nbsp;</a>
				
	</div>
			</form>
		</div>
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势"/>
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
						<a >诚聘英才</a>
						|
					</li>
					<li>
						<a >法律声明</a>
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
						<a >SHOP++官网</a>
						|
					</li>
					<li>
						<a >SHOP++论坛</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 2005-2013 Mango商城 版权所有</div>
	</div>
</div>
</body></html>