package com.aearwood.coordinator;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter {


	public static void main(String[] args) {
		 SpringApplication.run(Application.class, args);
	}
	 @Override
	 public void addInterceptors(InterceptorRegistry  registry){
		 registry.addInterceptor(csrfTokenAddingInterceptor()).addPathPatterns("views/**");
	 }
	 @Bean
	   public HandlerInterceptor csrfTokenAddingInterceptor() {
	      return new HandlerInterceptorAdapter() {
	         @Override
	         public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView view) {
	            CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
	            if (token != null && null != view) {
	               view.addObject(token.getParameterName(), token);
	            }
	         }
	      };
	   }
	 @Bean
	 public Jackson2ObjectMapperBuilder jacksonBuilder() {
	 	Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
	 	builder.indentOutput(true).dateFormat(new SimpleDateFormat("yyyy-MM-dd"));
	 	return builder;
	 }
}
