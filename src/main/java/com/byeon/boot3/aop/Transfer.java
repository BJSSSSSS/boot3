package com.byeon.boot3.aop;

import org.springframework.stereotype.Component;

@Component
public class Transfer {

	public void bus() {
		System.out.println("버스 타기");
	}
	
	public void subway() {
		System.out.println("지하철 타기");
	}
	
	
	
}
