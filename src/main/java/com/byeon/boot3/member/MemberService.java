package com.byeon.boot3.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.byeon.boot3.util.FileManager;


@Service
@Transactional(rollbackFor = Exception.class)
public class MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private FileManager fileManager;
	
	//properties에 있는걸 java쪽으로 가지고 올수 있다! value annotation 사용
	//properties 파일의 member.role.member 속성값 반환
	@Value("${member.role.member}")
	private String memberRole;
	
	//회원가입
	public int setJoin(MemberVO memberVO, MultipartFile photo) throws Exception{
		
		int result = memberMapper.setJoin(memberVO);
		
		//MEMBERROLE TABLE INSERT ** map 만들기!! **
		Map<String, String> map = new HashMap<>();
		//ID, ROLEID KEY, VALUE로 넣어놓기
		map.put("id", memberVO.getId());
		//여기서 roldName을 문자열로 받고 이후 mapper에서 서브쿼리로 숫자로 만들어줄것 밑에 값을 위에 value로 넣음
		map.put("roleName", memberRole);
		
		result = memberMapper.setRoleAdd(map);
		
		
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
	public MemberVO getLogin(MemberVO memberVO) throws Exception{
		return memberMapper.getLogin(memberVO);
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
