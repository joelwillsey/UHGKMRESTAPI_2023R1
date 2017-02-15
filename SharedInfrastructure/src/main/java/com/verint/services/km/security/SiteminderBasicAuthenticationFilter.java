/**
 * 
 */
package com.verint.services.km.security;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.NullRememberMeServices;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;





/**
 * @author jmiller
 * 
 */
public class SiteminderBasicAuthenticationFilter extends OncePerRequestFilter {
	private final Logger LOGGER = LoggerFactory.getLogger(SiteminderBasicAuthenticationFilter.class);
	private AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource = new WebAuthenticationDetailsSource();
	private RememberMeServices rememberMeServices = new NullRememberMeServices();
	private AuthenticationEntryPoint authenticationEntryPoint;
	private AuthenticationManager authenticationManager;
	private boolean ignoreFailure = false;
	private String credentialsCharset = "UTF-8";
	private static final String USERNAME = "USERNAME",
								FIRST_NAME = "FIRST_NAME", 
								LAST_NAME = "LAST_NAME",
								GROUPS = "GROUPS",
								SSO_FIRST_NAME = "SSO_FIRST_NAME",
								SSO_LAST_NAME = "SSO_LAST_NAME",
								KB_NAMES = "KB_NAMES";
	private static final String[] SSO_GROUPS = { 	"KIQkiqadmin","KIQACME Agent NCST", "KIQOHCSES Agent", "KIQOAdminCall_Agent",
		     										"KIQOClinical_Agent", "KIQOProductSupport_Agent",
		     										"KIQOPaymentIntegrity_Agent", "KIQORX_Agent", "KIQMedica NAMS" };

	/**
	 * 
	 */
	public SiteminderBasicAuthenticationFilter() {
		super();
		LOGGER.info("Entering SiteminderBasicAuthenticationFilter()");
		LOGGER.info("Exiting SiteminderBasicAuthenticationFilter()");
	}

	/**
	 * 
	 * @param authenticationManager
	 */
	public SiteminderBasicAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		LOGGER.info("Entering SiteminderBasicAuthenticationFilter()");
		this.authenticationManager = authenticationManager;
		ignoreFailure = true;
		LOGGER.info("Exiting SiteminderBasicAuthenticationFilter()");
	}

	/**
	 * 
	 * @param authenticationManager
	 * @param authenticationEntryPoint
	 */
	public SiteminderBasicAuthenticationFilter(AuthenticationManager authenticationManager,
			AuthenticationEntryPoint authenticationEntryPoint) {
		super();
		LOGGER.info("Entering SiteminderBasicAuthenticationFilter()");
		this.authenticationEntryPoint = authenticationEntryPoint;
		this.authenticationManager = authenticationManager;
		LOGGER.info("Exiting SiteminderBasicAuthenticationFilter()");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		LOGGER.info("Entering doFilterInternal()");
		final boolean debug = logger.isDebugEnabled();
		String 	ssoUserName = null,
				dummyPassword = "admin12345";
		        //ssoUserName ="kmmanager";
// Check if on url
		
		// NOTE: Back-door way in
		if (debug) {
			ssoUserName = request.getParameter("username");
			dummyPassword = request.getParameter("password");
		}

		if (ssoUserName != null && ssoUserName.length() > 0 &&
				dummyPassword != null && dummyPassword.length() > 0) {
			// We have valid parameters
			LOGGER.info("Valid username/password");
		} else {
			String header = request.getHeader("x-km-authorization");
			if (header == null || !header.startsWith("Basic ")) {
				chain.doFilter(request, response);
				return;
			}
			String[] tokens = extractAndDecodeHeader(header, request);
			assert tokens.length == 2;
			ssoUserName = tokens[0];
			dummyPassword = tokens[1];
			LOGGER.debug("Basic Authentication Authorization header found for user '" + ssoUserName + "'");
		}


		try {
			// Check if authentication is required
			if (authenticationIsRequired(ssoUserName)) {
				UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
						ssoUserName, dummyPassword);
				authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
				Authentication authResult = authenticationManager.authenticate(authRequest);
				LOGGER.debug("Authentication success: " + authResult);
				request.setAttribute("kmuser", ssoUserName);
				request.setAttribute("kmpassword", dummyPassword);
				SecurityContextHolder.getContext().setAuthentication(authResult);
				rememberMeServices.loginSuccess(request, response, authResult);
				onSuccessfulAuthentication(request, response, authResult);
			}
		}
		catch (AuthenticationException failed) {
			SecurityContextHolder.clearContext();
			LOGGER.debug("Authentication request for failed: " + failed);
			rememberMeServices.loginFail(request, response);
			onUnsuccessfulAuthentication(request, response, failed);
			if (ignoreFailure) {
				chain.doFilter(request, response);
			}
			else {
				authenticationEntryPoint.commence(request, response, failed);
			}
			return;
		}
		LOGGER.info("Exiting doFilterInternal()");
		chain.doFilter(request, response);
	}

	/**
	 * Decodes the header into a username and password.
	 *
	 * @throws BadCredentialsException if the Basic header is not present or is not valid
	 * Base64
	 */
	private String[] extractAndDecodeHeader(String header, HttpServletRequest request)
			throws IOException {

		byte[] base64Token = header.substring(6).getBytes("UTF-8");
		byte[] decoded;
		try {
			decoded = Base64.decode(base64Token);
		}
		catch (IllegalArgumentException e) {
			throw new BadCredentialsException(
					"Failed to decode basic authentication token");
		}

		String token = new String(decoded, getCredentialsCharset(request));
		int delim = token.indexOf(":");
		if (delim == -1) {
			throw new BadCredentialsException("Invalid basic authentication token");
		}
		return new String[] { token.substring(0, delim), token.substring(delim + 1) };
	}

	/**
	 * 
	 * @param username
	 * @return
	 */
	private boolean authenticationIsRequired(String username) {
		// Only reauthenticate if username doesn't match SecurityContextHolder and user
		// isn't authenticated
		// (see SEC-53)
		Authentication existingAuth = SecurityContextHolder.getContext()
				.getAuthentication();

		if (existingAuth == null || !existingAuth.isAuthenticated()) {
			return true;
		}

		// Limit username comparison to providers which use usernames (ie
		// UsernamePasswordAuthenticationToken)
		// (see SEC-348)

		if (existingAuth instanceof UsernamePasswordAuthenticationToken
				&& !existingAuth.getName().equals(username)) {
			return true;
		}

		// Handle unusual condition where an AnonymousAuthenticationToken is already
		// present
		// This shouldn't happen very often, as BasicProcessingFitler is meant to be
		// earlier in the filter
		// chain than AnonymousAuthenticationFilter. Nevertheless, presence of both an
		// AnonymousAuthenticationToken
		// together with a BASIC authentication request header should indicate
		// reauthentication using the
		// BASIC protocol is desirable. This behaviour is also consistent with that
		// provided by form and digest,
		// both of which force re-authentication if the respective header is detected (and
		// in doing so replace
		// any existing AnonymousAuthenticationToken). See SEC-610.
		if (existingAuth instanceof AnonymousAuthenticationToken) {
			return true;
		}

		return false;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param authResult
	 * @throws IOException
	 */
	protected void onSuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, Authentication authResult) throws IOException {
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param failed
	 * @throws IOException
	 */
	protected void onUnsuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException failed)
			throws IOException {
	}
	
	/**
	 * 
	 * @return
	 */
	protected AuthenticationEntryPoint getAuthenticationEntryPoint() {
		return authenticationEntryPoint;
	}

	/**
	 * 
	 * @return
	 */
	protected AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	/**
	 * 
	 * @param aep
	 */
	public void setAuthenticationEntryPoint(AuthenticationEntryPoint aep) {
		this.authenticationEntryPoint = aep;
	}

	/**
	 * 
	 * @param am
	 */
	public void setAuthenticationManager(AuthenticationManager am) {
		this.authenticationManager = am;
	}

	/**
	 * 
	 * @return
	 */
	protected boolean isIgnoreFailure() {
		return ignoreFailure;
	}

	/**
	 * 
	 * @param authenticationDetailsSource
	 */
	public void setAuthenticationDetailsSource(
			AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource) {
		Assert.notNull(authenticationDetailsSource,
				"AuthenticationDetailsSource required");
		this.authenticationDetailsSource = authenticationDetailsSource;
	}

	/**
	 * 
	 * @param rememberMeServices
	 */
	public void setRememberMeServices(RememberMeServices rememberMeServices) {
		Assert.notNull(rememberMeServices, "rememberMeServices cannot be null");
		this.rememberMeServices = rememberMeServices;
	}

	/**
	 * 
	 * @param credentialsCharset
	 */
	public void setCredentialsCharset(String credentialsCharset) {
		Assert.hasText(credentialsCharset, "credentialsCharset cannot be null or empty");
		this.credentialsCharset = credentialsCharset;
	}

	/**
	 * 
	 * @param httpRequest
	 * @return
	 */
	protected String getCredentialsCharset(HttpServletRequest httpRequest) {
		return credentialsCharset;
	}
	
	private static String parseKMGroups(String kmGroup) 
	{
		String group = "";
		if (kmGroup != null) 
		{
			for (int i = 0; i < SSO_GROUPS.length; i++) 
			{
				if (kmGroup.contains(SSO_GROUPS[i])) 
				{
					group += SSO_GROUPS[i];
					group += ",";
				}
			}
		}
		if(group=="") return "default";
		return group;
	}
}