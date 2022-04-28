package com.byeon.boot3.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.byeon.boot3.board.BoardMapper;
import com.byeon.boot3.board.BoardVO;
import com.byeon.boot3.member.MemberVO;

//이거안씀
@Component
public class DeleteCheckInterceptor implements HandlerInterceptor {

	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String num = request.getParameter("num");
		BoardVO boardVO = new BoardVO();
		boardVO.setNum(Long.parseLong(num));
		boardVO = boardMapper.getDetail(boardVO);
		
		MemberVO memberVO = (MemberVO)request.getSession().getAttribute("member");
		
		if(boardVO.getWriter().equals(memberVO.getId())) {
			return true;
		}else {
			request.setAttribute("msg", "작성자만 가능합니다");
			request.setAttribute("path", "./list");
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/getResult.jsp");
			view.forward(request, response);
			return false;
		}
	}
	
	
	
	
	
}
