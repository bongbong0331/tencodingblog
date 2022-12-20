package com.tencoding.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	// 등록
	// 객체

	// 루시 안써서 우ㅡ선 주석
//	@Bean
//	public FilterRegistrationBean<XssEscapeServletFilter> filterRegistrationBean() {
//
//		FilterRegistrationBean<XssEscapeServletFilter> filterRegistrationBean = new FilterRegistrationBean<>();
//		filterRegistrationBean.setFilter(new XssEscapeServletFilter());
//		filterRegistrationBean.setOrder(1);
//		filterRegistrationBean.addUrlPatterns("/*");
//		
//		System.out.println("WebMvcConfigurer 생성 >>>>>>>>>>");
//		return filterRegistrationBean;
//	}

}
