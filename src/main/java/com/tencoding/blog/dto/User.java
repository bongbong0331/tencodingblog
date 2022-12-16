package com.tencoding.blog.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import com.tencoding.blog.model.RoleType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// 1. mysql server 실행 ! 
// 2. 테이블 생성
// 3. 제약 추가

@Data
@NoArgsConstructor    //반드시 넣어라
@AllArgsConstructor     //반드시 넣어라
@Builder
@ToString
@Entity
//@DynamicInsert    null 필드가 들어 올때 무시하라 ---> 디폴트 값 선언하면 적용 된다.!!
public class User {

	@Id // Primary key PK 로 지정한다.
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // 프로젝트에서 연결된 DB 넘버링 전략을 따라 간다.(auto_increment)
	private int id;
	
	// unique = true 제약 추가 !!!!
	@Column(nullable = false, length = 50, unique = true)	
	private String username;
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = true, length = 50)
	private String email;
	
//	@ColumnDefault(" 'user' ") // 문자 라는 것을 알려주어야 한다. -->>> (' ')
	@Enumerated(EnumType.STRING) // DB에게 String 타입이라고 알려줘야 한다.
//	private String role;  // admin, user, manager  
	private RoleType role; // ---< 이넘 타입으로 변경
	
	
	private String oauth; // kakao쓰면 카카오, google 은 google 최소한 두개 만들기 
	
	
	
	@CreationTimestamp // 시간이 자동으로 입력 된다.
	private Timestamp createDate;
}
