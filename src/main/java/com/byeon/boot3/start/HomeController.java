package com.byeon.boot3.start;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String start() throws Exception{
		return "index";
	}
}
