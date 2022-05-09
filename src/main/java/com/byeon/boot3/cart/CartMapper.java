package com.byeon.boot3.cart;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartMapper {

	public int setAdd(CartVO cartVO) throws Exception;
	
	public List<CartVO> getList(CartVO cartVO) throws Exception;
	
	
	
}
