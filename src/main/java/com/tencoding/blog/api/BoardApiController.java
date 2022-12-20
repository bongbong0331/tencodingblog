package com.tencoding.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tencoding.blog.auth.PrincipalDetail;
import com.tencoding.blog.dto.Board;
import com.tencoding.blog.dto.Reply;
import com.tencoding.blog.dto.ResponseDto;
import com.tencoding.blog.service.BoardService;

@RestController
public class BoardApiController {

	@Autowired
	private BoardService boardService;

	@Autowired
	private AuthenticationManager authenticationManager;

	
//	//아작스 통신
//	// 약속 - aplication/json
//	// x-www-formurllexm ||
//	@PostMapping("/api/board")
//	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail detail) {
//		
//		// 아작스 통신으로 넘겨 받아서 받은 데이터 콘솔에 뿌려 보기
//
//		boardService.write(board, detail.getUser());
//
//		return new ResponseDto<Integer>(HttpStatus.OK, 1);
//	}
	
	
	
//	// form 으로 접속 해보기
//	@PostMapping("/api/board")
//	public ModelAndView save(Board board, @AuthenticationPrincipal PrincipalDetail detail) {
//
//		// 아작스 통신으로 넘겨 받아서 받은 데이터 콘솔에 뿌려 보기
//		System.out.println("rrrggewgweewgwgwgwgwgwgewgewgewgewg");
//		boardService.write(board, detail.getUser());
//
//		ModelAndView mav = new ModelAndView("redirect:/");
//		
//		return mav;
//	}
	
	
	

	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id) {

		boardService.deleteById(id);
		return new ResponseDto<Integer>(HttpStatus.OK, 1);
	}

	@PutMapping("/api/board/{boardId}")
	public ResponseDto<Integer> update(@PathVariable int boardId, @RequestBody Board board) {

		int result = boardService.modifyBoard(boardId, board);
		return new ResponseDto<Integer>(HttpStatus.OK, result);
	}

	@PostMapping("/api/board/{boardId}/reply")
	public ResponseDto<Integer> replySave(@PathVariable int boardId, @RequestBody Reply requestReply,
			@AuthenticationPrincipal PrincipalDetail principalDetail) {
		// 서비스
		boardService.writeReply(boardId, requestReply, principalDetail.getUser());

		return new ResponseDto<Integer>(HttpStatus.OK, 1);
	}

	// url: `/api/board/${boardId}/reply/${replyId}`,
	// 검증 (현재 삭제 요청자, db 저장된 사용자의 id역시 비교하여 처리를 해주어야 한다 )
	// 문제
	@DeleteMapping("/api/board/{boardId}/reply/{replyId}")
	public ResponseDto<?> deleteReplyById(@PathVariable String boardId, @PathVariable int replyId,
			@AuthenticationPrincipal PrincipalDetail principalDetail) {

		try {

			boardService.deleteReplyById(replyId, principalDetail.getUser().getId());
		} catch (Exception e) {

		}

//		System.out.println("boardId " + boardId);
//		System.out.println("replyId " + replyId);
		// 서비스요청처리

		return new ResponseDto<Integer>(HttpStatus.OK, 1);
	}
	
	

}
