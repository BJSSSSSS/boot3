package com.byeon.boot3.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.byeon.boot3.interceptor.AdminInterceptor;
import com.byeon.boot3.interceptor.BoardInterceptor;
import com.byeon.boot3.interceptor.SellerInterceptor;

@Configuration //설정 파일임을 명시
public class InterceptorConfig implements WebMvcConfigurer {

	@Autowired
	private SellerInterceptor sellerInterceptor;
	
	@Autowired
	private AdminInterceptor adminInterceptor;
	
	@Autowired
	private BoardInterceptor boardInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//적용할 Interceptor를 registry에 등록
		registry.addInterceptor(sellerInterceptor) //뒤에 ; 안찍고 이어짐 밑에까지
		//Interceptor를 사용할 URL 주소
				.addPathPatterns("/product/manage"); // 만약 더있으면 계속 추가해주고 마지막에 ; 찍기
			//  .addPathPatterns("/product/manage");
		
			//제외할 URL주소
			//	.excludePathPatterns("URL주소");
		
		//추가로 다른 Interceptor 등록
		//registry.addInterceptor(인터셉터 명); 
		//		  .addPathPatterns("URL 주소");
		registry.addInterceptor(adminInterceptor)
				.addPathPatterns("/admin/manage");
		
		//일케 하니까 꽤 쉽네
		registry.addInterceptor(boardInterceptor)
				.addPathPatterns("/board/*")
				.excludePathPatterns("/board/list");
		
		
		//WebMvcConfigurer.super.addInterceptors(registry); 요건 자동생성인데 안해도 됨
	}
	
	
	
}
