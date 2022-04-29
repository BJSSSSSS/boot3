package com.byeon.boot3.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.byeon.boot3.util.Pager;

@Mapper//(원래 interface는 상속받아서 써야함 오버라이딩으로, boot에서는 자체적으로 mapper를 써줘서 만들어준다)
public interface ProductMapper {
	
	//product
	
	//update
	public int setUpdate(ProductVO productVO) throws Exception;
	
	//detail(여러개가 올경우 에러가 발생한다!, XML에 쿼리문을 쓸때 PK로 꼭 WHERE절을 쓰도록 하자)
	public ProductVO getDetail(ProductVO productVO) throws Exception;
	
	//insert
	public int setAdd(ProductVO productVO) throws Exception; 

	//list
	public List<ProductVO> getList(Pager pager) throws Exception;
	
	//total
	public Long total(Pager pager) throws Exception;
	
	//delete
	public int setDelete(ProductVO productVO) throws Exception;
	
	//productFile
	
	//fileList(지우려고) - 전체 지우는 용도
	public List<ProductFilesVO> getFileList(ProductVO productVO) throws Exception;
	
	//fileDetail(지우려고) - HDD에서 하나 지우는 용도(sevice 호출용)
	public ProductFilesVO getFileDetail(ProductFilesVO productFilesVO) throws Exception;
	
	//fileDelete
	public int setFileDelete(ProductFilesVO productFilesVO) throws Exception;
	
	//insert : file
	public int setFileAdd(ProductFilesVO productFilesVO) throws Exception;
	

}
