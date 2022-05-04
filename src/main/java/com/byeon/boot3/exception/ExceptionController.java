package com.byeon.boot3.exception;

import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionController {
	
	//예외처리 메서드 ++++++++++++++++++++++++++++++++++++++++++++++
	@ExceptionHandler(BindException.class)
	public ModelAndView ex1(Exception e) {
		ModelAndView mv = new ModelAndView();
		System.out.println(e.getMessage());
		
		System.out.println("===================================");
		
		e.printStackTrace();
		
		System.out.println("예외 발생 처리");
		
		mv.addObject("msg", "불편을 죄송!");
		mv.addObject("path", "../");
		mv.setViewName("common/getResult");
		
		//mv.setViewName("error/error");
		return mv;
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ModelAndView ex2() {
		ModelAndView mv = new ModelAndView();
		
		System.out.println("NullPointException 예외 발생 처리");
		
		mv.setViewName("error/error");
		return mv;
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView ex3() {
		ModelAndView mv = new ModelAndView();
		
		System.out.println("Exception 예외 발생 처리");
		
		mv.setViewName("error/error");

		return mv;
	}

	@ExceptionHandler(Throwable.class)
	public ModelAndView ex4() {
		ModelAndView mv = new ModelAndView();
		
		System.out.println("Throwable 예외 발생 처리");
		
		mv.setViewName("error/error");
		return mv;
	}
	
	
	//400번대 에러 처리
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView ex5() {
		
		ModelAndView mv = new ModelAndView();
		
		System.out.println("4XX 예외 발생 처리");
		mv.addObject("message", "Page를 찾을 수 없습니다");
		mv.setViewName("error/error");
		
		return mv;
	}
	
	
	
	

}
