package com.byeon.boot3.member;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	
	//find id form
	@GetMapping("findId")	
	public void getFindId() throws Exception{}
	
	
	//find id DB
	@PostMapping("findId")	
	public ModelAndView getFindId(MemberVO memberVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		memberVO = memberService.getFindId(memberVO);
		
		//System.out.println(memberVO.getId());
		mv.addObject("idResult", memberVO);
		mv.setViewName("member/findIdResult");
		return mv;
	}
	
	
	//join form 이동
	@GetMapping("join")
	public ModelAndView join(@ModelAttribute MemberVO memberVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/join");
		return mv;
	}
	
	//join DB
	@PostMapping("join")
	public ModelAndView join(@Valid MemberVO memberVO, BindingResult bindingResult, MultipartFile photo) throws Exception{
		//System.out.println("photo : " + photo.getOriginalFilename());
		ModelAndView mv = new ModelAndView();
		
//		if(bindingResult.hasErrors()) {
//			mv.setViewName("member/join");
//			return mv;
//		}
		
		//사용자 정의 검증 메서드 호출
		if(memberService.memberError(memberVO, bindingResult)) {
			mv.setViewName("member/join");
			return mv;
		}
		
		
		int result = memberService.setJoin(memberVO, photo);
	
		mv.setViewName("redirect:../");

		return mv;
	}
	
	//login form 이동
	@GetMapping("login")
	public ModelAndView login(@ModelAttribute MemberVO memberVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		//mv.addObject("vo", new MemberVO()); 이렇게 하거나 위에 @ModelAttribute에 넣기
		mv.setViewName("member/login");
		return mv;
	}
		
	//login DB
	@PostMapping("login")									//순서 중요! 꼭 검증할 VO 바로 뒤에 선언
	public ModelAndView login(@Valid MemberVO memberVO, BindingResult bindingResult, HttpSession session) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
//		if(bindingResult.hasErrors()) { //로그인할때 검증을 빼야 작동된다! 연습용이고 제대로는 join때
//			mv.setViewName("member/login");
//			return mv;
//		}
		
		
		memberVO = memberService.getLogin(memberVO);
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
		memberVO = memberService.getLogin(memberVO);
		
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
