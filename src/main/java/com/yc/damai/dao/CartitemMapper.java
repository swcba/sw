package com.yc.damai.dao;

import java.util.List;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.yc.damai.bean.Cartitem;

public interface CartitemMapper {
	
	@Select("select * from cartitem")
	public List<Cartitem> selectAll();

	@Select("select * from cartitem where uid = #{uid}")
	public List<Cartitem> selectOne(Long uid);
	
	
}
