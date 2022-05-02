package com.byeon.boot3.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration //Legacy ***-context.xml spring legacy와 다르게 자바에서 자바언어로 만들어줄것
public class MessageConfig implements WebMvcConfigurer {
	
	@Bean 	//<bean class=""> 객체 생성
	public LocaleResolver localeResolver() {
	
		//1. Session
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(Locale.KOREAN); //기본으로 보여지는 언어
		
		//2. Cookie
		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		cookieLocaleResolver.setDefaultLocale(Locale.KOREAN);
		cookieLocaleResolver.setCookieName("lang");
		
		//둘중 하나 사용
		return cookieLocaleResolver; //sessionLocaleResolver;
	}
	
	
	@Bean //interceptor 생성
	public LocaleChangeInterceptor localeChangeInterceptor () {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		
		localeChangeInterceptor.setParamName("lang");//파라미터 이름
		//파라미터를 받아서 언어를 구분
		//ex) url?lang=en
		return localeChangeInterceptor;
	}
	
	
	
}









