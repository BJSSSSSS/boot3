package com.byeon.boot3.cart;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.byeon.boot3.member.MemberVO;
import com.byeon.boot3.util.Pager;

@Controller
//@RestController // 모든 메서드가 @Responsebody가 있으면 선언, 이후 각각 메서드에 @Responsebody 생략 가능!
public class CartController {
	
	@Autowired
	private CartService cartService;

	@PostMapping("/cart/{productNum}/{count}")
	@ResponseBody //데이터 자체를 return 시키게 만들수 있음(이때 스프링에서 자동으로 JSON형태로 바꿔서 리턴해줌!)
	public int setAdd(HttpSession session, @PathVariable Long productNum, @PathVariable Long count) throws Exception{
		
		//System.out.println("ProductNum : " + productNum);
		
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		
		CartVO cartVO = new CartVO();
		cartVO.setId(memberVO.getId());
		cartVO.setProductNum(productNum);
		cartVO.setCount(count);
		
		int result = cartService.setAdd(cartVO);
		//result를 JSON 형태로 바꿔주는 라이브러리를 사용
		
		
		return result;
	}
	
	
	@DeleteMapping("/cart/{cartNum}")
	public ModelAndView setDelete(@PathVariable Long cartNum) throws Exception{
		ModelAndView mv = new ModelAndView();
		System.out.println("CartNum : " + cartNum);
		return mv;
	}
	
	
	@GetMapping("/cart/{pn}")
	@ResponseBody
	public CartVO getList(HttpSession session, @PathVariable Long pn) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		//System.out.println("PN : " + pn);
		
		CartVO cartVO = new CartVO();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		cartVO.setId(memberVO.getId());
		
		List<CartVO> ar = cartService.getList(cartVO);
		
		return ar.get(0);
		
	}
	
	
}
