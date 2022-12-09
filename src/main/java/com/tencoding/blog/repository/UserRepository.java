package com.tencoding.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tencoding.blog.dto.User;


// DAO
// 여기서는 굳이 Bean 으로 등록 요청을 하지않아도 등록을 시켜준다 --> JpaRepository에 있기에
//@Repository

public interface UserRepository extends JpaRepository<User, Integer>{ // 테이블명, PK 데이터타입
	
	
	
}
