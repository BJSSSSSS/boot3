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
public class BoardInterceptor implements HandlerInterceptor{

	//properties에서 가져 옴
	@Value("${member.role.member}")
	private String roleName;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		boolean check = false;
		
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");


		if(memberVO != null) {
			for(RoleVO r : memberVO.getRoleVOs()) {
				if(r.getRoleName().equals(roleName)) {
					check = true;
				}
			}
		}

		if(!check) {	
			request.setAttribute("msg", "권한이 없습니다");
			request.setAttribute("path", "../");
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/getResult.jsp");
			view.forward(request, response); 
		}
		
		return check;
	}
	
	
	

}
