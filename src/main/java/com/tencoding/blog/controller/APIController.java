package com.tencoding.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class APIController {

	@GetMapping("/hello")
	public String get() {
		return "test1";
	}
}