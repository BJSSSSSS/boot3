package com.byeon.boot3.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.byeon.boot3.util.FileManager;
import com.byeon.boot3.util.Pager;

@Service
public class BoardService {

	@Autowired
	public BoardMapper boardMapper;
	
	@Autowired
	public FileManager fileManager;
	
	
	//file정보를 DB에서 꺼내옴
	public BoardFilesVO getFileDetail(BoardFilesVO boardFilesVO)throws Exception{
		return boardMapper.getFileDetail(boardFilesVO);
	}
	
	//list
	public List<BoardVO> getList(Pager pager) throws Exception{
		pager.makeRow();
		pager.makeNum(boardMapper.getTotalCount(pager));
		return boardMapper.getList(pager);
	}
	
	//detail
	public BoardVO getDetail(BoardVO boardVO) throws Exception{
		return boardMapper.getDetail(boardVO);
	}
	
	//delete
	public int setDelete(BoardVO boardVO) throws Exception{
		
		List<BoardFilesVO> ar = boardMapper.getFileList(boardVO);
		
		int result = boardMapper.setDelete(boardVO);
		if(result > 0) {
			for(BoardFilesVO vo: ar) {
				boolean check = fileManager.remove("resources/upload/board/", vo.getFileName());
			}
		}
	
		return result;
	}
	
	//update
	public int setUpdate(BoardVO boardVO) throws Exception{
		return boardMapper.setUpdate(boardVO);
	}
	
	
	
	//add
	public int setAdd(BoardVO boardVO, MultipartFile [] files) throws Exception{
		//System.out.println("Insert 전 : " + boardVO.getNum());
		int result = boardMapper.setAdd(boardVO);
		//이 과정 전에 boardMapper.xml -> add문에서 num변수 잡아주는것 진행!
		//System.out.println("Insert 후 : " + boardVO.getNum());
		
		if(files != null) {
		
			//이걸 반복해서 넣어주기
			for(MultipartFile mf : files) {
				
				//파일이 비어있으면 위로 올라가세요
				if(mf.isEmpty()) {
					continue;
				}
				
				//1. File을 HDD에 저장
				String fileName = fileManager.fileSave(mf, "resources/upload/board/");
				System.out.println(fileName);
				
				//2. 저장된 정보를 DB에 저장
				BoardFilesVO boardFilesVO = new BoardFilesVO();
				boardFilesVO.setNum(boardVO.getNum());
				boardFilesVO.setFileName(fileName);
				boardFilesVO.setOriName(mf.getOriginalFilename());
				boardMapper.setFileAdd(boardFilesVO);
			}
		}
		
		return result;
	}
	
	
}
