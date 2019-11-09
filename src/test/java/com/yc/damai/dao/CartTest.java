package com.yc.damai.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.yc.damai.bean.Cart;
import com.yc.damai.bean.Cartitem;

public class CartTest {
	private SqlSession  session;
	
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
	
	@Test
	public void selectTest() {
		CartMapper cm = session.getMapper(CartMapper.class);
		List<Cart> c = cm.selectAll();
		System.out.println(c.get(0).toString());
		
	}
	
	@Test
	public void SelectTest() {
		CartitemMapper cim = session.getMapper(CartitemMapper.class);
		List<Cartitem> list = cim.selectAll();
		
		System.out.println(list.get(0).toString());
		
	}
	
	@Test
	public void insetTest() {
		
		Cartitem	c = new Cartitem();
		
		c.setUid(1);
		c.setCount(1);
		c.setPid(3L);
		
		session.insert("com.yc.damai.dao.CartitemMapper.create",c);
		
		session.commit();
	}
	@After
	public void after() {
		session.close();
	}

}
