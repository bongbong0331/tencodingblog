package com.tencoding.blog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.tencoding.blog.auth.PrincipalDetail;



@Controller
public class BoardController {

	/**
	 * 
	 * 로그인 인증되면 컨트롤러에서 어떻게 세션을 찾을까??
	 * 
	 * */
	
	
	@GetMapping({"", "/"})
	public String index(@AuthenticationPrincipal PrincipalDetail principal) {
		if(principal != null) {
			System.out.println(principal.getUsername());
			System.out.println(principal.getAuthorities());
		}
		return "index";
	}
}
