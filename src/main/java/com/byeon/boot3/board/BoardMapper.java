package com.byeon.boot3.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {

	//detail
	public BoardVO getDetail(BoardVO boardVO) throws Exception;
	
	//list : getList
	public List<BoardVO> getList() throws Exception;
	
	//insert : setAdd
	public int setAdd(BoardVO boardVO) throws Exception;
	
	//update : setUpate
	public int setUpdate(BoardVO boardVO) throws Exception;
	
	//delete : setDelete
	public int setDelete(BoardVO boardVO) throws Exception;
	
}
