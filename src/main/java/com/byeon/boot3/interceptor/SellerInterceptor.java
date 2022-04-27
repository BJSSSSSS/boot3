package com.byeon.boot3.interceptor;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.byeon.boot3.member.MemberVO;
import com.byeon.boot3.member.RoleVO;


@Component
public class SellerInterceptor implements HandlerInterceptor{

	//properties에서 가져 옴
	@Value("${member.role.seller}")
	private String roleName;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		boolean check = false;
		
		//로그인 한 사용자의 ROLE이 ROLE_SELLER 라면 통과 아니면 거절
		//MemberVO memberVO = (MemberVO)request.getSession().getAttribute("member");
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");//다형성에 의해 object타입으로 옴
		
		System.out.println(memberVO);
		
//		if(memberVO == null) {
//			request.setAttribute("msg", "로그인이 필요합니다");
//			request.setAttribute("path", "../member/login");
//			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/getResult.jsp");
//			view.forward(request, response);
//			return check;
//		}
		
		//강사 코드
		if(memberVO != null) {
			List<RoleVO> role = memberVO.getRoleVOs();
			
			for(RoleVO r : role) {
				if(r.getRoleName().equals(roleName)) {
					check = true;
				}
			}
		}
		
		//check -> false면 거절 : Servlet 코드 사용
		
		if(!check) {
			//1) redirect : response.sendRedirect("url주소");
			//if(!check) {
			//	response.sendRedirect("../member/login"); 이게 여기가 아니네
			//}
			
			//2) forward 방식
			//2_1) mv.addObject("키", 밸류);
			request.setAttribute("msg", "권한이 없습니다");
			request.setAttribute("path", "../");
			
			//WEB-INF/views 경로와 .jsp까지 직접 작성
			//RequestDispatcher view = request.getRequestDispatcher("url 주소");
			//2_2) mv.setViewName(); 에서 jsp바로 찾아가는 형식
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/getResult.jsp");
			view.forward(request, response); //view의 이름을 직접 넣음
		}

		//check -> true면 통과
		
		return check;
	}
	
	
	

}
