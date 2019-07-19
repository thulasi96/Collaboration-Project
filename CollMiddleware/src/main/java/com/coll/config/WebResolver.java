package com.coll.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.coll.*")
public class WebResolver {
	
	@Bean
	public InternalResourceViewResolver getViewResolver()
	{
	System.out.println("****View Resolver Bean Creation****");
	InternalResourceViewResolver newResolver= new InternalResourceViewResolver();
	newResolver.setPrefix("/WEB-INF/jsp");
	newResolver.setSuffix(".jsp");
	return newResolver;
	}
	
	@Bean(name="multipartResolver")
	public CommonsMultipartResolver getMultipartResolver() {
		CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(1000000);
		return multipartResolver;
	}

}
