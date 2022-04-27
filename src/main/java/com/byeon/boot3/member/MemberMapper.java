package com.byeon.boot3.member;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface MemberMapper {
	
	
	//memberFiles
	//insert : setFileAdd
	public int setFileAdd(MemberFilesVO memberFilesVO) throws Exception;
	
	//detail : getFileDetail
	public MemberFilesVO getFileDetail(MemberVO memberVO) throws Exception;
	
	//list   : getFileList
	public List<MemberFilesVO> getFileList(MemberVO memberVO) throws Exception;
	
	//member
	//insert : 회원가입
	public int setJoin(MemberVO memberVO) throws Exception;
	
	//detail : 로그인
	public MemberVO login(MemberVO memberVO) throws Exception;
	
	//detail : 마이페이지
	public MemberVO myPage(MemberVO memberVO) throws Exception;
	
	//update : 회원수정
	public int setUpdate(MemberVO memberVO) throws Exception;
	
	//delete : 회원탈퇴
	public int setDelete(MemberVO memberVO) throws Exception;
	
	
	//list - 이게 필요한가?
	public List<MemberVO> getList() throws Exception;
	
	

}
