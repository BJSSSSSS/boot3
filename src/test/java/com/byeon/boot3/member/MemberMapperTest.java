package com.byeon.boot3.member;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberMapperTest {

	@Autowired
	private MemberMapper memberMapper;
	
	//detail : login
	//@Test
	void loginTest() throws Exception{
		MemberVO memberVO = new MemberVO();
		memberVO.setId("id1");
		memberVO.setPw("pw1");
		memberVO = memberMapper.login(memberVO);
		assertNotNull(memberVO);
	}
	
	//detail : myPage
	//@Test
	void myPageTest() throws Exception{
		MemberVO memberVO = new MemberVO();
		memberVO.setId("id1");
		memberVO = memberMapper.myPage(memberVO);
		assertNotNull(memberVO);
	}
	
	//insert
	//@Test
	void joinTest() throws Exception{
		MemberVO memberVO = new MemberVO();
		memberVO.setId("id2");
		memberVO.setPw("pw2");
		memberVO.setName("n2");
		memberVO.setEmail("e2");
		memberVO.setPhone("p2");
		int result = memberMapper.setJoin(memberVO);
		assertEquals(1, result);
	}
	
	//update
	//@Test
	void setUpdateTest() throws Exception{
		MemberVO memberVO = new MemberVO();
		memberVO.setId("id1");
		memberVO.setName("n1 update");
		memberVO.setEmail("e1 update");
		memberVO.setPhone("p1 update");
		
		int result = memberMapper.setUpdate(memberVO);
		
		assertNotEquals(0, result);
	}
	
	//delete
	//@Test
	void setDeleteTest() throws Exception{
		MemberVO memberVO = new MemberVO();
		memberVO.setId("id1");
		
		int result= memberMapper.setDelete(memberVO);
		
		assertEquals(1, result);
		
	}
	
	
	
	
}


