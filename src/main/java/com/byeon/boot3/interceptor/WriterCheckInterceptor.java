package com.byeon.boot3.interceptor;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.byeon.boot3.board.BoardMapper;
import com.byeon.boot3.board.BoardVO;
import com.byeon.boot3.member.MemberVO;

@Component
public class WriterCheckInterceptor implements HandlerInterceptor{
	
	@Autowired
	private BoardMapper boardMapper;
	
	//post 다 진행되고 빠져 나오기 전
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {//ModelAndView 생성, 여기서 꺼내올수있다!
//		
//		MemberVO memberVO = (MemberVO)request.getSession().getAttribute("member");
//		
//		//꺼내올때 맵타입으로 들어온다
//		Map<String, Object> map = modelAndView.getModel();
//		
//		BoardVO boardVO = (BoardVO)map.get("vo");
//		
//		if(!boardVO.getWriter().equals(memberVO.getId())) {
//			//원래 있던 이름에서 새로운 view로 덮어씌울수 있다!
//			//1) redirect
//			//modelAndView.setViewName("redirect:./list");
//			
//			//2) jsp
//			modelAndView.addObject("msg", "작성자만 가능");
//			modelAndView.addObject("path", "./list");
//			modelAndView.setViewName("common/getResult");
//		}
//		
//		
//	}
	
	
	
	
	
	//pre controller 들어가기전
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//System.out.println("Writer Check Interceptor");
		
		//메서드도 가져올수 있다 get일경우, post일 경우
		//이걸 통해서도 코드가 진행되거나 안되게 만들 수 있다
		//이건 주소로 가지고 오는거기때문에 일단 method가 get이든 post이든 갖고온다
		String method = request.getMethod();
		System.out.println(method);
		
		
		String num = request.getParameter("num");
		
		
		
		Long numLong = Long.parseLong(num);
		
		BoardVO boardVO = new BoardVO();
		boardVO.setNum(numLong);
		boardVO = boardMapper.getDetail(boardVO);
		
		MemberVO memberVO = (MemberVO)request.getSession().getAttribute("member");
		
		//System.out.println(boardVO.getWriter());
		//System.out.println(memberVO.getId());
		
		//boolean check = false; //안넘기는거 꼭 설정해줘야 오류가 안뜬다
		
		if(boardVO.getWriter().equals(memberVO.getId())) {
			//작성자와 로그인 한 사용자의 id가 일치하면 통과
			return true;
		}else {
			//일치하지 않으면 list로 redirect
			response.sendRedirect("./list");
			return false;
		}
		
	}
	
	
	
	
	
	

}





