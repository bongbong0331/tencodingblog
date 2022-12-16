package com.tencoding.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tencoding.blog.auth.PrincipalDetailService;

@Configuration   // IoC 관리
@EnableWebSecurity   // 시큐리티 필터로 등록이 된다 ( 필터 커스텀 )
@EnableGlobalMethodSecurity(prePostEnabled = true)   // 특정 주소로 접근하면 권한 및 인증 처리를 미리 체크 하겠다.
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	
	// IoC 관리하고 싶어서 여기서 선언
	@Bean
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private PrincipalDetailService principalDetailService; 
	
	
	/**
	 * 회원 정보 수정시에 Authentication 객체를 생성하기 위해
	 * AUthenticationManager 매니저가 필요하다 ! 이 시점에서 IoC 처리 ! 
	 * 
	 * */
	// bean 해줘야함 
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {

		return super.authenticationManagerBean();
	}
	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 1. userDetailsService 들어갈 Object 만들어 주어야 한다.
		// 2. passwordEncoder 우리가 사용하는 해시 암호화 함수를 알려주어야 한다. 스프링시큐리티 에게
		
		// 1. 우리가 커스텀한 녀석을 넣어야 한다.
		// 2. BCryptPasswordEncoder 사용하여 암호화 하였다.
//		System.out.println("auth >>>>>: " + auth);
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
		
	}
	
	
	
	
	//   /auth/login_form, auth/join_form --> /auth/** auth 뒤에 오는것은 다 허용 하겠다 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();    // 개발 완료 전 테스트 시 사용 ( 실 서비스 시 풀어 사용하지 말길 권장 )
		
		http
			.authorizeHttpRequests()
				.antMatchers("/auth/**", "/", "/js/**", "/image/**", "/css/**", "/test/**")
				.permitAll()
				.anyRequest()
				.authenticated()
			.and()
				.formLogin()
				.loginPage("/auth/login_form")
				.loginProcessingUrl("/auth/loginProc")
				.defaultSuccessUrl("/")
				;
	}
}
