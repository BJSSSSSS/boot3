package com.byeon.boot3.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@ModelAttribute("board")
	public String getBoard() {
		return "member";
	}
	
	//join form 이동
	@GetMapping("join")
	public void join() throws Exception{}
	
	//join DB
	@PostMapping("join")
	public ModelAndView join(MemberVO memberVO, MultipartFile photo) throws Exception{
		//System.out.println("photo : " + photo.getOriginalFilename());
		ModelAndView mv = new ModelAndView();
		
		int result = memberService.setJoin(memberVO, photo);
	
		mv.setViewName("redirect:../");

		return mv;
	}
	
	//login form 이동
	@GetMapping("login")
	public ModelAndView login() throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/login");
		return mv;
	}
		
	//login DB
	@PostMapping("login")
	public ModelAndView login(MemberVO memberVO, HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		memberVO = memberService.login(memberVO);
		mv.setViewName("member/login");
		
		if(memberVO!=null) {
			session.setAttribute("member", memberVO);
			mv.setViewName("redirect:../");
		}
		
		return mv;
	}
	
	//logout
	@GetMapping("logout")
	public String logout(HttpSession session) throws Exception {
		session.invalidate();
		return "redirect:../";
	}
	
	//mypage
	@GetMapping("mypage")
	public ModelAndView myPage(HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		memberVO = memberService.myPage(memberVO);
		
		mv.addObject("vo", memberVO);
		mv.setViewName("member/mypage");
		
		return mv;

	}
	
	//회원수정 form
	@GetMapping("update")
	public ModelAndView setUpdate(HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		memberVO = memberService.myPage(memberVO);
		
		mv.addObject("vo", memberVO);
		mv.setViewName("member/update");
		
		return mv;
	}
	
	//회원수정 DB
	@PostMapping("update")
	public ModelAndView setUpdate(MemberVO memberVO, HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		MemberVO vo = (MemberVO)session.getAttribute("member");
		memberVO.setId(vo.getId());
		int result = memberService.setUpdate(memberVO);
		
		//수정후에 session으로 다시 넣어줌
		memberVO = memberService.login(memberVO);
		
		if(memberVO!=null) {
			session.setAttribute("member", memberVO);
			mv.setViewName("redirect:../");
		}
		
		mv.setViewName("redirect:./mypage");
		
		return mv;
	}
	

	//delete
	@GetMapping("delete")
	public ModelAndView setDelete(MemberVO memberVO, HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		memberVO = (MemberVO)session.getAttribute("member");
		
		int result = memberService.setDelete(memberVO);
		
		session.invalidate();
		
		mv.setViewName("redirect:../");
		
		return mv;

	}
	
	
	
	
	
	
	
	
	
	

}
