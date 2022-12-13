package com.tencoding.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration   // IoC 관리
@EnableWebSecurity   // 시큐리티 필터로 등록이 된다 ( 필터 커스텀 )
@EnableGlobalMethodSecurity(prePostEnabled = true)   // 특정 주소로 접근하면 권한 및 인증 처리를 미리 체크 하겠다.
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	
	// IoC 관리하고 싶어서 여기서 선언
	@Bean
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	
	
	
	//   /auth/login_form, auth/join_form --> /auth/** auth 뒤에 오는것은 다 허용 하겠다 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();    // 개발 완료 전 테스트 시 사용 ( 실 서비스 시 풀어 사용하지 말길 권장 )
		
		http
			.authorizeHttpRequests()
				.antMatchers("/auth/**", "/", "/js/**", "/image/**", "/css/**")
				.permitAll()
				.anyRequest()
				.authenticated()
			.and()
				.formLogin()
				.loginPage("/auth/login_form");
	}
}
