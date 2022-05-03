package com.byeon.boot3.member;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class MemberVO {
	//로그인이랑 회원가입이랑 valid 겹치는 경우가 지금 생기는데 이때는 그냥 상속받아서 사용하면 될듯
	//@NotEmpty
	//@NotNull
	@NotBlank
	private String id;
	@Size(min = 3, max = 12)
	private String pw;
	
	//체크용 pw 하나 더 추가함!
	private String pwC;
	
	@NotBlank
	private String name;
	@NotBlank
	@Email
	private String email;
	private String phone;
	
	//멤버는 하나의 프로필 파일을 가진다
	private MemberFilesVO memberFilesVO;
	
	//멤버는 여러개의 role을 가진다
	private List<RoleVO> roleVOs;
	
}
