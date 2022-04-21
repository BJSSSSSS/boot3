package com.byeon.boot3.util;

import java.io.File;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager {

	@Autowired
	private ServletContext servletContext;
	

	public boolean remove(String path, String fileName) throws Exception{
		
		path = servletContext.getRealPath(path);
		 
		File file = new File(path, fileName);
		
		return file.delete();
	}
	
	
	
	public String fileSave(MultipartFile mf, String path) throws Exception{
		
		//path = "프로젝트 상의 파일을 저장할 폴더의 경로"
		//1. 파일을 HDD에 저장하고
		//2. 저장된 파일명을 리턴(파일명은 중복 X)
		path = servletContext.getRealPath(path);
		System.out.println(path);
		
		File file = new File(path);
		//파일경로에 파일이 없으면 만들자
		if(!file.exists()) {
			//makeDirectories
			file.mkdirs();
		}
		
		String fileName = UUID.randomUUID().toString();
		fileName = fileName + "_" + mf.getOriginalFilename();
		
		file = new File(file, fileName);
		mf.transferTo(file);
	
		return fileName;
	}
	
	
	
}
