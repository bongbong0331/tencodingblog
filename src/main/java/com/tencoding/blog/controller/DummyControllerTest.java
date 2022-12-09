package com.tencoding.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tencoding.blog.dto.User;
import com.tencoding.blog.model.RoleType;
import com.tencoding.blog.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/dummy")
public class DummyControllerTest {
	
	@Autowired // 순환 참조 조심 !!!!
	private UserRepository userRepository;
	
	// select all 전체보기
	@GetMapping("/user")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	// page 처리 (1페이지에 5개)
	@GetMapping("/users2")
	public Page<User> pageList(@PageableDefault(size = 5, 
			sort = "id", direction = Direction.DESC) Pageable pageable){
		
		Page<User> userPage = userRepository.findAll(pageable);
		
		List<User> users = userRepository.findAll(pageable).getContent();
		return userPage;
	}
	
	
	
	
	
	@GetMapping("/user/{id}")
	public User detail(@PathVariable int id) {
		
//		User user = userRepository.findById(id).orElseGet(() -> {
//			// 커스텀 한 데이터
//			return new User();
//		});
		
		// 예외처리 포함
		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 유저는 없습니다 : " + id);
		});
		
		
		return user;
	}
	
	// http://localhost:9090/blog/dummy/signup
	@PostMapping("/signup")
	public String signUp(@RequestBody User user) {
		
		log.info(">>>>> User : {}", user);
		// .....
		user.setRole(RoleType.USER);   //  추가
		userRepository.save(user);
		
		
		// 로직 수행 
		return "회원가입이 완료되었습니다.";
		
	}
	
}
