package com.byeon.boot3.product;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.byeon.boot3.util.Pager;

@SpringBootTest
class ProductMapperTest {

	@Autowired
	private ProductMapper productMapper;
	
	//insert
	//@Test
	void setAddTest() throws Exception{
		
		ProductVO productVO = new ProductVO();
		productVO.setProductName("p1");
		productVO.setProductPrice(10000L);
		productVO.setProductCount(1000L);
		productVO.setProductDetail("d1");
		
		int result = productMapper.setAdd(productVO);
		
		assertEquals(1, result);
	}
	
	//getList
	//@Test
	void getListTest() throws Exception{
		Pager pager = new Pager();
		pager.makeRow();
		List<ProductVO> ar = productMapper.getList(pager);
		
		System.out.println(ar);
		
	}

}
