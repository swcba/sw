package com.yc.damai.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.yc.damai.bean.Product;

public interface ProductMapper {
	
	@Select("select * from product")
	public List<Product> selectAll();	
	
	@ResultMap("baseMapper")
	@Select("select * from product where pid = #{pid}")
	public Product selectByPid(Long i);
	
	/**
	 * MyBatis 方法传入多个参数时，要使用 Param 注解标注参数名
	 * @param p
	 * @param i
	 * @return
	 */
	
	@Results(id="myRM",value={
			
			@Result(property="marketPrice", column="market_price"),
			@Result(property="shopPrice" ,column="shop_price"),
			@Result(property="isHot" ,column="is_Hot"),
	})
	@Select("select * from product where pname=#{pname} and is_hot=#{isHot}")
	public List<Product> selectByPnameAndIsHot(@Param("pname") String p,@Param("isHot") Integer i);

	@Select("${sql}")
	public List<Map<String,Object>> selectBySql(String sql);
	
}
