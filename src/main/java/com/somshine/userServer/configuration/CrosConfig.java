//package com.somshine.userServer.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Component
//public class CrosConfig {
//	@Bean
//	public WebMvcConfigurer getCrossConfiguration() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/*").allowedOrigins("http://localhost:3000").allowedMethods("*").allowedHeaders("*");
//			}
//		};
//	}
//}
