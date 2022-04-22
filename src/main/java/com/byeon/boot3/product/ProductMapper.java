package com.byeon.boot3.product;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.byeon.boot3.util.Pager;

@Mapper
public interface ProductMapper {
	
	//insert
	public int setAdd(ProductVO productVO) throws Exception; 
	
	//insert : file
	public int setFileAdd(ProductFilesVO productFilesVO) throws Exception;
		
	//list
	public List<ProductVO> getList(Pager pager) throws Exception;
	
	//total
	public Long total(Pager pager) throws Exception;
	
	
	
	

}
