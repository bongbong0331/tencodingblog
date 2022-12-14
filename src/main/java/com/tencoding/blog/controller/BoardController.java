package com.tencoding.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tencoding.blog.auth.PrincipalDetail;
import com.tencoding.blog.dto.Board;
import com.tencoding.blog.service.BoardService;

@Controller
public class BoardController {

	/**
	 * 
	 * 로그인 인증되면 컨트롤러에서 어떻게 세션을 찾을까??
	 * 
	 */

	@Autowired
	private BoardService boardService;

	@GetMapping({ "", "/" })
	public String index(Model model) {
		
		List<Board> boards = boardService.getBoardList();
		model.addAttribute("boards", boards);   // jsp 파일에서 model 추상화객체를 이용하여 컨트롤러에서 내려 준 데이터를 접근할 수 있다.
		
		return "index";
	}

	@GetMapping("/board/save_form")
	public String saveForm() {
		return "/board/save_form";
	}

}
