package com.yc.damai.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.yc.damai.bean.Product;
import com.yc.damai.bean.User;

public class ProductMapperTest {
	
	private SqlSession  session;
	private SqlSession  session1;

	@Before
	public void before() throws IOException{
		// 初始化 MyBatis 矿建
		String resource = "mybatis.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// SqlSessionFactoryBuilder 会话工厂的构建器
		// SqlSessionFactory  会话工厂
		// SqlSession   会话
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		session = sqlSessionFactory.openSession();
	}
	
	
	public void testSelectAll() throws IOException{
		List<Product> list = session.selectList("com.yc.damai.dao.ProductMapper.selectAll");
		System.out.println(list.size());
		System.out.println(list.get(0));
	}
	
	
	@Test
	public void testSelectByPid() throws IOException{
		Product p = session.selectOne("com.yc.damai.dao.ProductMapper.selectByPid", 56);
		System.out.println(p);
		System.out.println("===========1===========");
		session.update("com.yc.damai.dao.ProductMapper.modify",p);
		session.selectOne("com.yc.damai.dao.ProductMapper.selectByPid", 56);
		System.out.println("===========2===========");
		session.selectOne("com.yc.damai.dao.ProductMapper.selectByPid", 56);
		System.out.println("===========3===========");
		System.out.println(p);
	}
	
	public void testInsert() throws IOException{
		
		// 数据库事务管理代码：成功则提交，失败就回滚
		
		try{
			Product p = new Product();
			p.setPname("男秋装");
			p.setMarketPrice(110d);
			p.setShopPrice(90d);
			p.setImage("01.jpg");
			p.setPdesc("好衣服");
			p.setIsHot(1);
			Timestamp t = new Timestamp(System.currentTimeMillis());
			p.setPdate(t);
			int count = session.insert("com.yc.damai.dao.ProductMapper.create", p);
			
			Assert.assertEquals(1, count);
			
			Assert.assertNotNull(p.getPid());
			// 提交
			session.commit();
			
		} catch (Exception e){
			e.printStackTrace();
			// 回滚
			session.rollback();
		} finally {
			// 关闭会话
			session.close();
		}
	}
	
	@Test
	public void testSelect() {
		List<User> list=session.selectList("com.yc.damai.dao.ProductMapper.selectByuid","s");
		System.out.println("查询结果有："+list.size());
		for(User p : list) {
			
			System.out.println(p.toString());
			
		}
	}

	@Test
	public void testLogin() {
		Map<String,String> m = new HashMap<>();
		m.put("username", "song");
		m.put("password", "a");
		List<User> list=session.selectList("com.yc.damai.dao.ProductMapper.selectLogin",m);
		if(list.size()==0) {
			System.out.println("该用户信息不存在");
		}else {
			System.out.println(list.size());
			System.out.println("欢迎宁！");
		}
		
		
	}
	
	
	@Test
	public void createUser() {
		
		
			try {
				User user = new User();
				user.setUid(5L);
				user.setUsername("sw");
				user.setPassword("yc");
				user.setName("CBA");
				user.setEmail("2463079095@qq.com"	);
				user.setPhone("15197650636");
				user.setSex("男");
				user.setCode(null);
				user.setAddr("hunan");
				user.setState(0);
				
				int count=session.insert("com.yc.damai.dao.ProductMapper.createUser",user);
				Assert.assertEquals(1, count);
				
				// 提交
				session.commit();
				
			} catch (Exception e){
				e.printStackTrace();
				// 回滚
				session.rollback();
			} finally {
				// 关闭会话
				session.close();
			}
	}
	
	@Test
	public void updateUser() {
		
		try {
		List<User> list = session.selectList("com.yc.damai.dao.ProductMapper.selectUid", "sw");	
		User user = list.get(0);
		System.out.println(user.toString());
		user.setAddr("湖南");
		System.out.println(user.toString());
		 int count = session.update("com.yc.damai.dao.ProductMapper.update",user);
		 Assert.assertEquals(1, count);
			// 提交
		session.commit();
			
		} catch (Exception e){
			e.printStackTrace();
			// 回滚
			session.rollback();
		} finally {
			// 关闭会话
			session.close();
		
		}
	}
	
	
	
	public void delete() {
		try {
			
			int count = session.delete("com.yc.damai.dao.ProductMapper.delete",5);
			Assert.assertEquals(1, count);
			// 提交
			session.commit();
		 } catch (Exception e){
			e.printStackTrace();
			// 回滚
			session.rollback();
		} finally {
			// 关闭会话
			session.close();
		
		}
	}
	
	@Test
	public void selectByObj() {
		session.selectList("com.yc.damai.dao.ProductMapper.selectByObj", null);
		session.selectList("com.yc.damai.dao.ProductMapper.selectByObj");
		Product p = session.selectOne("com.yc.damai.dao.ProductMapper.selectByObj", 56);
		System.out.println(p);
		p = new Product();
		p.setPname("%zs%");
		session.selectList("com.yc.damai.dao.ProductMapper.selectByObj",p);

		p.setPdesc("%好衣服%");
		session.selectList("com.yc.damai.dao.ProductMapper.selectByObj",p);
		
		
		
	}
	
	
	
	@Test
	public void testSelectByFlag() {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("isHot", 1);
		param.put("flag", 1);
		session.selectList("com.yc.damai.dao.ProductMapper.selectByFlag", param);
		param.put("flag", 2);
		session.selectList("com.yc.damai.dao.ProductMapper.selectByFlag", param);

		// 加入排序字段
		param.put("ordername","shop_price");

		param.put("flag", 3);
		session.selectList("com.yc.damai.dao.ProductMapper.selectByFlag", param);

		param.put("flag", 4);
		session.selectList("com.yc.damai.dao.ProductMapper.selectByFlag", param);

		
		
		
		
	}
	
	
	
	@Test
	public void testUpdate() {
		Product p =new Product();
		p.setPid(2);
		p.setPname("七匹狼");
		session.update("com.yc.damai.dao.ProductMapper.modify", p);
		session.commit();
	}
	@Test
	public void testSelectInCsid(){
		//int[] csids = {1,2,3,4};

		List<Integer> csids = new ArrayList<>();
		csids.add(1);
		csids.add(2);
		csids.add(3);
		csids.add(4);
		csids.add(5);
	List<Product > list = 	session.selectList("com.yc.damai.dao.ProductMapper.selectInCsid",csids);
	System.out.println(list.get(0).toString());
	}

	@Test
	public void testSelect2(){
		String sql = "select * from user";
		List<?> list = session.selectList("com.yc.damai.dao.ProductMapper.select",sql);
		System.out.println(list);
	}
	@Test
	public void testCache() throws IOException{

		// Cache Hit Ratio [com.yc.damai.dao.ProductMapper]: 0.0
		// 缓存命中

		Product p = session.selectOne("com.yc.damai.dao.ProductMapper.selectByPid", 56);
		System.out.println(p);
		System.out.println("==============1==========");

		// 必须关闭或提交，才能二级缓存才会缓存会话的数据
		session.close();

		session1.selectOne("com.yc.damai.dao.ProductMapper.selectByPid", 56);
		System.out.println("==============2==========");

	}
	
	@Test
	public void selectAllForInterface() {
		ProductMapper pm= session.getMapper(ProductMapper.class);
		pm.selectAll();
	//	pm.selectByPid(1);
		pm.selectByPnameAndIsHot("冬装外套棉衣立领修身商务大码男装潮牌上衣", 1);
	}
	@Test
	public void testSelectBySQL(){
		ProductMapper pm = session.getMapper(ProductMapper.class);
		String sql = "select max(shop_price)  maxp , min(shop_price)  minp from product";
		List<Map<String,Object>> list = pm.selectBySql(sql);
		System.out.println(list);
	}
	@After
	public void after() {
		session.close();
	}
	
}