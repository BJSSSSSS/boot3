package com.byeon.boot3.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

//파일을 다운로드하는 jsp, jsp 대신에 쓰는것임!
@Component("fileDown")//이렇게 설정 안해줘도 bean의 이름을 fileDown으로 만들어준다 클래스명 맨 처음 소문자!
public class FileDown extends AbstractView{
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private ResourceLoader resourceLoader;

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//1. 파일이  저장된  경로 설정
		String path = "resources/upload/";
		path = path + (String)model.get("board");
		//실제경로 받아오기
		path = servletContext.getRealPath(path);
		
		
        //2. Model에서 파일 객체 GET
		//Model에서 꺼낼때 FileVO타입으로 꺼내준다(상속 받아서 쓰기 때문) 여기서 이름은 "fileVO"로 보내는거로 통일
		FileVO fileVO = (FileVO)model.get("fileVO");
		
        //3. 위의 정보를 담는 파일 객체 생성(실제 경로 받아와서 사용)
		//File file = resourceLoader.getResource(path).getFile();
		File file = new File(path, fileVO.getFileName());
		
		//한글 처리
		response.setCharacterEncoding("UTF-8");
		
		//총 파일의 크기
		response.setContentLengthLong(file.length());
		
		//다운로드시 파일 이름을 인코딩 처리
		String fileName = URLEncoder.encode(fileVO.getOriName(), "UTF-8");
		
		//header 설정
		response.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\"" );
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		//HDD에서 파일을 읽고
		FileInputStream fi = new FileInputStream(file);
		//Client로 전송 준비
		OutputStream os = response.getOutputStream();
		
		//전송
		FileCopyUtils.copy(fi, os);
		
		os.close();
		fi.close();	
		
	}

}



