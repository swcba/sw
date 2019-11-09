package com.yc.damai.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yc.damai.bean.Cart;

public interface CartMapper {
	
	
	@Select("select * from cart")
	public List<Cart> selectAll() ;

	
	@Select("select * from cart where ciid = #{cid}")
	public Cart selectOne(Integer cid) ;
}
