package com.byeon.boot3.board;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.byeon.boot3.util.FileManager;
import com.byeon.boot3.util.Pager;

@Service
@Transactional(rollbackFor = Exception.class)
public class BoardService {

	@Autowired
	public BoardMapper boardMapper;
	
	@Autowired
	public FileManager fileManager;
	
	//summerFile에서 파일 삭제
	public boolean setSummerFileDelete(String fileName) throws Exception{
		
		//위 fileName은 전체정보가 있어서 substring으로 문자열을 자름
		//index번호 입력시 fileName.lastIndexOf("/") 사용
		fileName = fileName.substring(fileName.lastIndexOf("/")+1);
		System.out.println(fileName);
		
		return fileManager.remove("/resources/upload/board/", fileName);

	}
	
	//summerFile에서 파일 업로드
	public String setSummerFileUpload(MultipartFile files) throws Exception{
		//file HDD에 저장하고 저장된 파일명을 return
		String fileName = fileManager.fileSave(files, "resources/upload/board/");
		fileName = "/resources/upload/board/"+fileName;
		return fileName;
	}
	
	
	
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
		
		if(files != null && result > 0) { //위에 insert가 안되면 밑에 작동 안함
		
			//이걸 반복해서 넣어주기
			for(MultipartFile mf : files) {
				
				//파일이 비어있으면 위로 올라가세요
				if(mf.isEmpty()) {
					continue;
				}
				
//				if(result > 0) {//일부로 exception 발생 이 순간 멈추기때문에 밑에 코드 진행 안됨
//					throw new SQLException();
//				}
				
				//1. File을 HDD에 저장
				String fileName = fileManager.fileSave(mf, "resources/upload/board/");
				System.out.println(fileName);
				
				//2. 저장된 정보를 DB에 저장
				BoardFilesVO boardFilesVO = new BoardFilesVO();
				boardFilesVO.setNum(boardVO.getNum());
				boardFilesVO.setFileName(fileName);
				boardFilesVO.setOriName(mf.getOriginalFilename());
				result = boardMapper.setFileAdd(boardFilesVO);
				
				if(result < 1) {
					//board table insert 한 것도 rollback
					//file insert가 안되면 강제로 exception 발생시키기
					throw new SQLException();
				}
				
			}
		}
		
		return result;
	}
	
	
}
