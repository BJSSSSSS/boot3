package com.byeon.boot3.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.byeon.boot3.util.Pager;

@Mapper
public interface ProductMapper {

	//detail
	public ProductVO getDetail(ProductVO productVO) throws Exception;
	
	//insert
	public int setAdd(ProductVO productVO) throws Exception; 
	
	//insert : file
	public int setFileAdd(ProductFilesVO productFilesVO) throws Exception;
		
	//list
	public List<ProductVO> getList(Pager pager) throws Exception;
	
	//total
	public Long total(Pager pager) throws Exception;
	
	//delete
	public int setDelete(ProductVO productVO) throws Exception;
	
	//fileList(지우려고)
	public List<ProductFilesVO> getFileList(ProductVO productVO) throws Exception;
	


}
