package com.byeon.boot3.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.byeon.boot3.util.Pager;

@Service
public class ProductService {
	
	@Autowired
	private ProductMapper productMapper;
	
	//list
	public List<ProductVO> getList(Pager pager) throws Exception{
		
		pager.makeRow();
		return productMapper.getList(pager);
	}
	
	

}
