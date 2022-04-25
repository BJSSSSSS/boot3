package com.byeon.boot3.start;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String start() throws Exception{
		return "index";
	}
	
	@GetMapping("/getTest")
	public String getTest(String msg) throws Exception{
		System.out.println("GetTest 요청 발생");
		System.out.println("msg : "+msg);
		return "common/getResult";
	}
	
	@PostMapping("/postTest")
	public String postTest(String msg) throws Exception{
		System.out.println("PostTest 요청 발생");
		System.out.println("msg: " + msg);
		return "common/getResult";
		
	}
	
	@PostMapping("/arrayTest")
	public String arrayTest(String msg, String [] numbers, String [] nums) throws Exception{
		System.out.println("ArrayTest 요청 발생");
		System.out.println("msg: " + msg);
		for(String str : numbers) {
			System.out.println(str);
		}
		
		for(String num : nums) {
			System.out.println(num);
		}
		
		return "common/getResult";
		
	}
	
	
	
	
	
}
