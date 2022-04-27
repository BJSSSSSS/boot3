package com.byeon.boot3.member;

import java.util.List;

import lombok.Data;

@Data
public class MemberVO {

	private String id;
	private String pw;
	private String name;
	private String email;
	private String phone;
	
	//멤버는 하나의 프로필 파일을 가진다
	private MemberFilesVO memberFilesVO;
	
	//멤버는 여러개의 role을 가진다
	private List<RoleVO> roleVOs;
	
}
