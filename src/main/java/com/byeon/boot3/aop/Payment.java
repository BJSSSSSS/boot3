package com.byeon.boot3.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Payment {
			//맨 앞 *은 리턴타입 잡기, 아무거나 상관없음 설정
	@Around("execution(* com.byeon.boot3.aop.Transfer.*())")
	public Object cardCheck(ProceedingJoinPoint joinPoint) throws Throwable{//exception보다 한단계 위 예외 던지기
		//join point 핵심 로직 메서드(bus, subway)
		System.out.println("탑승 전 카드 체크");
		
		Object obj = joinPoint.proceed();
		//obj는 핵심 로직 메서드가 리턴하는 Data		
		System.out.println("하차 시 카드 체크");
		
		return obj;
	}
	
	//get으로 시작하는 메서드가 호출되면 boardservice에서 select가 호출될수 있게 표현식을 만들것
	@Before("execution(* com.byeon.boot3.board.BoardService.get*(..))")
	public void before() {
		System.out.println("before");
	}
	
	@AfterReturning("execution(* com.byeon.boot3.aop.Transfer.*())")
	public void afterReturning() {
		System.out.println("afterReturning");
	}
	
	@AfterThrowing("execution(* com.byeon.boot3.aop.Transfer.*())")
	public void afterThrowing() {
		System.out.println("afterThrowing");
	}
	
	@After("execution(* com.byeon.boot3.board.BoardService.get*(..))")
	public void after() {
		System.out.println("AfterReturning + AfterThrowing");
	}
	
	//@Around("execution(* com.byeon.boot3.board.BoardService.get*(..))")
	public void around() {
		System.out.println("before + after");
	}
	
	
}
