package com.springdemo.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Spring Web MVC initializer
 * 
 * @author kenny
 * 
 */
public class WebMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * read configurations to initialize Spring application context
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { DatabaseConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebMvcConfig.class };

	}

	/**
	 * define the root path which will be processed by Spring Web MVC
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };

	}

	/**
	 * define the filter to process encoding of request parameters
	 */
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return new Filter[] { characterEncodingFilter };
	}

}
