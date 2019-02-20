package com.stackroute.keepnote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.stackroute.keepnote.jwtfilter.JwtFilter;

/*
 * The @SpringBootApplication annotation is equivalent to using @Configuration, @EnableAutoConfiguration 
 * and @ComponentScan with their default attributes
 */

@SpringBootApplication
public class UserServiceApplication {

	
	
	
	/*
	 * Define the bean for Filter registration. Create a new FilterRegistrationBean
	 * object and use setFilter() method to set new instance of JwtFilter object.
	 * Also specifies the Url patterns for registration bean.
	 */
	@Bean
	public FilterRegistrationBean<JwtFilter> jwtFilter() {
		FilterRegistrationBean<JwtFilter> filterRegistrationBean=new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new JwtFilter());
		filterRegistrationBean.addUrlPatterns("/api/v1/*");
		return filterRegistrationBean;
	}

	
	
	
	/*
	 * 
	 * You need to run SpringApplication.run, because this method start whole spring
	 * framework. Code below integrates your main() with SpringBoot.
	 */
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
}
