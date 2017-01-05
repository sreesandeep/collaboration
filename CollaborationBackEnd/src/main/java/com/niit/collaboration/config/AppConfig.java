package com.niit.collaboration.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages= "com.niit.collaboration")
public class AppConfig  extends WebMvcConfigurerAdapter {

	private static final Logger Logger = LoggerFactory.getLogger(AppConfig.class);
	@Bean
	public ViewResolver viewResolver(){
		Logger.debug("Starting of the method viewResolver");
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/");
		viewResolver.setSuffix(".html");
		Logger.debug("Ending of the method viewResolver");
		return viewResolver;
	}

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() 
	{
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		return resolver;
	}
	
	
	
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
		configurer.enable();
	}
	
}
