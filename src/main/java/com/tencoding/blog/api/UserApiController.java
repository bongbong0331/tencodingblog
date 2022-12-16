package com.tencoding.blog.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tencoding.blog.dto.ResponseDto;
import com.tencoding.blog.dto.User;
import com.tencoding.blog.service.UserService;

@RestController
public class UserApiController {

	// DI @Autowired
	@Autowired
	private UserService userService;
	@Autowired
	private HttpSession session;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	

	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {

		// 1, -1 ..
		int result = userService.saveUser(user);

		return new ResponseDto<Integer>(HttpStatus.OK, result); // 자바 OBJECT --> JSON 형식으로
	}
	
	
	@PutMapping("/api/user")
	public ResponseDto<Integer> update(@RequestBody User user){
		
		//여기까지 오기전에 벨리데이션 처리... 아니면 예외 잡아서 사용자에게 떨궈 주면 된다.
		userService.updateUser(user);
		///////////////////////////////////////////세이브 처리
		
		// 목표 : Authentication 접근해서 담겨 있는 Object 값을 수정해야 한다. 
		//    1 . Authentication 객체 생성
		//    2 . AuthenticationManager 메모리에 올려서 authenticate 메서드에 Authentication 을 저장 한다.
		//    3 . SecurityContexHolder.getContext().setAuthentication(우리가 만든 Authentication());    
		//    Securityconfig 에있따 
		
		// UsernamePasswordAuthenticationToken 생성 !! 
		// 1. 번
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), 
						user.getPassword()));
		// 2. 번 + 3. 번
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		
		
		return new ResponseDto<Integer>(HttpStatus.OK, 1);
	}
	
	
	

//	@PostMapping("/user/login")
//	public ResponseDto<Integer> login(@RequestBody User user) {
//		System.out.println("UserApiController login 호출 됨 : " + user);
//		// principal - 접근 주체
//		User principal = userService.login(user);
////		System.out.println("principal : " + principal);
//		
//		if(principal != null) {
//			session.setAttribute("principal",	 principal);
//		}
//		
//		return new ResponseDto<Integer>(HttpStatus.OK, 1);  // 자바 OBJECT --> JSON 형식으로 
//	}

}
