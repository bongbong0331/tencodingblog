package com.tencoding.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.blog.dto.User;
import com.tencoding.blog.model.RoleType;
import com.tencoding.blog.repository.BoardRepository;
import com.tencoding.blog.repository.ReplyRepository;
import com.tencoding.blog.repository.UserRepository;

import lombok.Setter;

// 스프링이 컴포넌트 스캔을 통하여 Bean 으로 등록해 준다 ( IoC )
@Service
public class UserService {

	/**
	 * 서비스를 만드는 이유
	 * 
	 * 트랜 잭션 관리를 하기 위해서
	 * 
	 * */
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	/*
	 * 작업의 단위
	 * 하나의 기능 + 하나의 기능 들을 묶어서 단위의 기능을 처리
	 * DB 수정 시 롤백 처리도 가능
	 * */
	
	@Transactional
	public int saveUser(User user) {

		try {
			// 비밀번호를 넣을 때 여기서 암호화 처리 하고 DB 저장 하기 !
			String rawPassword = user.getPassword();
			String encPassword = encoder.encode(rawPassword); //암호화 처리
			user.setPassword(encPassword);
			user.setRole(RoleType.USER);
			userRepository.save(user);
//			System.out.println("encPassword : " + encPassword);
			// $2a$10$kesKOWA9SSvU1LsxVoaBS.e/UCqB.f39VvmCyU2l4bSR7endWfq1m
			// $2a$10$lkr.rTM.kEMYHtoNu86DJuGnThSx3gLNAG5HJrxz.ozfqPTv7XNQq
			
			return 1;
		} catch (Exception e) {
			System.out.println("세이브유저 에러발생!!!!!!!");
			e.printStackTrace();
		}
		return -1;
	}
	
	

	@Transactional
	public void updateUser(User reqUser) {
		
		User userEntity = userRepository.findById(reqUser.getId())
				.orElseThrow(() -> {
					return new IllegalArgumentException("해당 유저를 찾을수 없습니다");
				});
		
		if(userEntity.getOauth() == null || userEntity.getOauth().equals("")) {
			// 우리 사이트 회원 가입 한 자
			String rawPassword = reqUser.getPassword();
			String encPassword = encoder.encode(rawPassword);
			
			// 두개만 변경가능
			userEntity.setUsername(reqUser.getUsername());
			userEntity.setPassword(encPassword);
			userEntity.setEmail(reqUser.getEmail());
//			userEntity.setCreateDate(userEntity.getCreateDate());
			// 더티 체킹 해서 업데이트 시킬 예정
		}
		
		
	}
	
	
	@Transactional
	public User searchUserName(String username) {
		
		return userRepository.findByUsername(username).orElseGet(() -> {
			return new User();
		});
		
		
		
	}
	

	
//	public User login(User user) {
//		// 기본 Repository 에 필요한 함수가 없을 경우 직접 생성하면 된다.
////		userRepository.get
////		User userEntity = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//		User userEntity = userRepository.login(user.getUsername(), user.getPassword());
//		System.out.println("userEntity : " + userEntity);
//		return userEntity;
//	}
}
