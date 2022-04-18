package com.byeon.boot3.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.byeon.boot3.util.Pager;

@Service
public class BoardService {

	@Autowired
	public BoardMapper boardMapper;
	
	public List<BoardVO> getList(Pager pager) throws Exception{
		pager.makeRow();
		return boardMapper.getList(pager);
	}
	
	
}
