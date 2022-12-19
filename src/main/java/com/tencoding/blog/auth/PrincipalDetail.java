package com.tencoding.blog.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tencoding.blog.dto.User;

/**
 * 시큐리티가 로그인 요청을 가로채서 로그인을 처리하고(DB...) 완료 되면 
 * UserDetails 타입의 오브젝트를 시큐리티의 고유한 세션
 * 저장소에 저장을 해준다 ! 즉 ( 우리가 새롭게 정의한 Object 로 처리할 예정 )
 */

public class PrincipalDetail implements UserDetails {

	private User user;

	public PrincipalDetail(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 계정의 권한을 반환 처리
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Collection<GrantedAuthority> collection = new ArrayList<GrantedAuthority>();
//		collection.add(new GrantedAuthority() {
//			@Override
//			public String getAuthority() {
//				// "ROLE_" 는 스프링 시큐리티 사용 시 prefix 로 무조건 넣어야 한다 !!
//				return "ROLE_" + user.getRole();
//			}
//		});
		// 똑같은것
		collection.add(() -> {
			return "ROLE_" + user.getRole();
		});
		return collection;
	}

	@Override
	public String getPassword() {

		return user.getPassword();
	}

	@Override
	public String getUsername() {

		return user.getUsername();
	}

	// 계정이 만료되지 '않았는지' 리턴한다. (false면 로그인 안됨)
	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	// 계정이 잠김 여부 확인 (false면 로그인 안됨)
	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	// 비밀번호 만료 여부를 알려 준다. 새비밀번호로 바꾸면 false 로바꿔야하는데 웬만하면쓰지마 (false면 로그인 안됨)
	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	// 계정 활성화 여부 확인 (false면 로그인 안됨)
	@Override
	public boolean isEnabled() {

		return true;
	}

}
