package com.niit.collaboration.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

  public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	private static final Logger Logger = LoggerFactory.getLogger(AppInitializer.class);
	
	@Override
	protected Class[] getRootConfigClasses() {
		Logger.debug("Starting of the method getRootConfigClasses");
		return new Class[] {AppConfig.class , WebSocketConfig.class};
	}
	
	@Override
	protected Class[] getServletConfigClasses() {
		Logger.debug("Starting of the method getServletConfigClasses");
		return null;
	}
	
	@Override
	protected String[] getServletMappings() {
		Logger.debug("Starting of the method getServletMappings");
		return new String[] {"/"};
	}
}
