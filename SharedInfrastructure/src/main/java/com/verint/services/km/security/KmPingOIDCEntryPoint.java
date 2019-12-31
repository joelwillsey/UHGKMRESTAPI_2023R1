package com.verint.services.km.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.FilterInvocation;
import org.springframework.web.filter.GenericFilterBean;

public class KmPingOIDCEntryPoint  extends GenericFilterBean implements AuthenticationEntryPoint{
	private final Logger LOGGER = LoggerFactory.getLogger(KmPingOIDCEntryPoint.class);
	/**
	 * Url this filter should get activated on.
	 */
	protected String filterProcessesUrl = FILTER_URL;

	/**
	 * Default name of path suffix which will invoke this filter.
	 */
	public static final String FILTER_URL = "/pingoidc/login";
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		LOGGER.info("Entering doFilter()");
		FilterInvocation fi = new FilterInvocation(request, response, chain);

		if (!processFilter(fi.getRequest())) {
			chain.doFilter(request, response);
			return;
		}

		commence(fi.getRequest(), fi.getResponse(), null);
		LOGGER.info("Exiting doFilter()");
	}
	
	protected boolean processFilter(HttpServletRequest request) {
		boolean result = false;
		LOGGER.info("Entering processFilter()");
		LOGGER.debug("FilterProcessesUrl: " + filterProcessesUrl + " RequestURI: " + request.getRequestURI().toString());
		LOGGER.info("Exiting processFilter()");
		if (filterProcessesUrl == request.getRequestURI().toString()){
			result=true;
		}
		
		return result;
	}
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
			throws IOException, ServletException {
		LOGGER.info("Entering commence()");
		try {
			
		} catch (Exception ex) {
			
		}
		LOGGER.info("Exiting commence()");
	}
		
}
