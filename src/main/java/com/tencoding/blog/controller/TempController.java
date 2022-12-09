package com.tencoding.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempController {

	
	@GetMapping("/temp/home")
	public String tempHome() {

		return "/home.html";
	}
	
	
	@GetMapping("/temp/img") 
	public String tempImage() {
		
		return "/a.png";  // 슬러쉬 안붙이면 오류남
	}
	
	
	@GetMapping("/temp/test")
	public String tempJsp() {
		// prefix : main --> /Web-INF/views/
		// /test.jsp
		// subfix : .jsp 붙어 있기때문에 .jsp 안붙여도됨.
		// /test
		// /WEB-INF/views/test.jsp
		return "test";  // 슬러쉬 안붙이면 오류남
	}

}
