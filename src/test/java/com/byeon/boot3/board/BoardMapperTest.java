package com.byeon.boot3.board;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoardMapperTest{

	@Autowired
	private BoardMapper boardMapper;
	
	//detail
	//@Test
	void getDetailTest() throws Exception {
		BoardVO boardVO = new BoardVO();
		boardVO.setNum(3L);
		boardVO = boardMapper.getDetail(boardVO);
		System.out.println(boardVO.toString());
		assertNotNull(boardVO);
	}
	
	//list
	//@Test
	void getListTest() throws Exception{
		List<BoardVO> ar = boardMapper.getList();
		assertNotEquals(0, ar.size());
	}
	
	//insert
	//@Test
	void setAddTest() throws Exception{
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle("title");
		boardVO.setWriter("writer");
		boardVO.setContents("contents");
		int result = boardMapper.setAdd(boardVO);
		assertEquals(1, result);
	}
	
	//update
	//@Test
	void setUpdateTest() throws Exception{
		BoardVO boardVO = new BoardVO();
		boardVO.setNum(4L);
		boardVO.setTitle("Title Update");
		boardVO.setContents("Contents Update");
		int result = boardMapper.setUpdate(boardVO);
		assertEquals(1, result);
	}
	
	//delete
	//@Test
	void setDeleteTest() throws Exception{
		BoardVO boardVO = new BoardVO();
		boardVO.setNum(4L);
		int result = boardMapper.setDelete(boardVO);
		assertEquals(1, result);
	}
	
	
	
	
	

}
