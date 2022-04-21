package com.byeon.boot3.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.byeon.boot3.util.FileManager;


@Service
public class MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private FileManager fileManager;
	
	//회원가입
	public int join(MemberVO memberVO, MultipartFile photo) throws Exception{
		
		int result = memberMapper.join(memberVO);
		
		if(!photo.isEmpty()) {
		
			String fileName = fileManager.fileSave(photo, "resources/upload/member/");
			
			MemberFilesVO memberFilesVO = new MemberFilesVO();
			memberFilesVO.setId(memberVO.getId());
			memberFilesVO.setFileName(fileName);
			memberFilesVO.setOriName(photo.getOriginalFilename());
			result = memberMapper.setFileAdd(memberFilesVO);
		}
		
		return result;
	}
	
	//로그인
	public MemberVO login(MemberVO memberVO) throws Exception{
		return memberMapper.login(memberVO);
	}
	
	//마이페이지
	public MemberVO myPage(MemberVO memberVO) throws Exception{
		return memberMapper.myPage(memberVO);
	}
	
	//회원수정
	public int setUpdate(MemberVO memberVO) throws Exception{
		return memberMapper.setUpdate(memberVO);
	}
	
	//회원탈퇴
	public int setDelete(MemberVO memberVO) throws Exception{
		
		MemberFilesVO memberFilesVO = memberMapper.getFileDetail(memberVO);
		
		int result = memberMapper.setDelete(memberVO);
		
		boolean fileResult = fileManager.remove("resources/upload/member/", memberFilesVO.getFileName());
		
		return result;
	}
	
	

}
