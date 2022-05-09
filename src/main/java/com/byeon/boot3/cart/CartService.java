package com.byeon.boot3.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class CartService {
	
	@Autowired
	private CartMapper cartMapper;
	
	public int setAdd(CartVO cartVO) throws Exception{
		return cartMapper.setAdd(cartVO);
	}
	
	public List<CartVO> getList(CartVO cartVO) throws Exception{
		return cartMapper.getList(cartVO);
	}


}
