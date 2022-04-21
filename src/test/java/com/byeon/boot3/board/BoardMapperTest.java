package com.byeon.boot3.board;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.intThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.byeon.boot3.util.Pager;

@SpringBootTest
class BoardMapperTest{

	@Autowired
	private BoardMapper boardMapper;
	
	//boardFiles
	//insert
	//@Test
	void setFileAdd() throws Exception{
		
		BoardFilesVO boardFilesVO = new BoardFilesVO();
		boardFilesVO.setFileName("fileName");
		boardFilesVO.setOriName("oriName");
		boardFilesVO.setNum(3L);
		
		int result = boardMapper.setFileAdd(boardFilesVO);
		assertEquals(1, result);
		
	}
	
	//delete
	//@Test
	void setFileDelete() throws Exception{
		
		BoardFilesVO boardFilesVO = new BoardFilesVO();
		boardFilesVO.setFileNum(3L);
		
		int result = boardMapper.setFileDelete(boardFilesVO);
		assertEquals(1, result);
		
	}
	
	
	
	
	//board
	//detail
	@Test
	void getDetailTest() throws Exception {
		
		BoardVO boardVO = new BoardVO();
		boardVO.setNum(113L);
		boardVO = boardMapper.getDetail(boardVO);
		
		System.out.println(boardVO.toString());
		assertNotNull(boardVO);
		
	}
	
	//list
	//@Test
	void getListTest() throws Exception{
		Pager pager = new Pager();
		pager.makeRow();
		List<BoardVO> ar = boardMapper.getList(pager);
		
		System.out.println(ar);
		assertEquals(10, ar.size());
		
	}
	
	//insert
	//@Test
	void setAddTest() throws Exception{
		
		for(int i=0; i<100; i++) {
			
			if(i%10 == 0) {//10번째 씩 1초 쉬기
				Thread.sleep(1000);
			}
			
			BoardVO boardVO = new BoardVO();
			boardVO.setTitle("addTitle"+i);
			boardVO.setWriter("addWriter"+i);
			boardVO.setContents("addContents"+i);
			int result = boardMapper.setAdd(boardVO);
			
		}
		
		System.out.println("Finish");
		
		//assertEquals(1, result);
		
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
