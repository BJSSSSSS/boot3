package com.byeon.boot3.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
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
	//여기서 판매자의 회원가입을 넣어줄때는 controller에서 판매자용 회원가입 만들어주고
	//properties 파일의 member.role.member와 member.role.seller을 가지고오고
	//setJoinSeller등 메서드를 하나 더 만들어 준뒤 result를 두번 해줘야할듯(일단 실험해보면 가능)
//	@Value("${member.role.seller}")
//	private String sellerRole;
	
	//사용자 정의 검증 메서드 선언
	public boolean memberError(MemberVO memberVO, BindingResult bindingResult) throws Exception{
		
		boolean check = false;
		//check=false : 검증 성공(error 없음)
		//check=true  : 검증 실패(error 있음)
		
		//1. annotation 기본 검증 결과 
		check = bindingResult.hasErrors();
		
		//2. Password가 일치하는지 수동 검증
		if(!memberVO.getPw().equals(memberVO.getPwC())) {
			check=true;
			bindingResult.rejectValue("pwC", "member.password.notEqual");
		}
		
		//3. Id 중복검사
		
		MemberVO idCheck = memberMapper.myPage(memberVO);
		if(idCheck != null) {
			check=true;
			bindingResult.rejectValue("id", "member.id.equal");
		}
		
		return check;
	}
	
	
	
	
	
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
		
		//밑에는 내가 해보는것 대충 이런식일듯? roleName 바뀐걸 한번 더 사용해줌 대신에 메서드를 다른걸 쓰는게 맞는듯 controller도!
//		map.put("roleName", sellerRole);
//		result = memberMapper.setRoleAdd(map);
		
		
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
	
	//이메일로 회원정보 찾기
	public MemberVO getFindId(MemberVO memberVO) throws Exception{
		return memberMapper.getFindId(memberVO);
	}
	

}
