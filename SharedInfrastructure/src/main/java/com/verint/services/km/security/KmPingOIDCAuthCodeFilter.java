package com.verint.services.km.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jose4j.json.internal.json_simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.NullRememberMeServices;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.GenericData;

public class KmPingOIDCAuthCodeFilter  extends OncePerRequestFilter {
	private final Logger LOGGER = LoggerFactory.getLogger(KmPingOIDCAuthCodeFilter.class);
	private AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource = new WebAuthenticationDetailsSource();
	private RememberMeServices rememberMeServices = new NullRememberMeServices();
	private AuthenticationEntryPoint authenticationEntryPoint;
	private AuthenticationManager authenticationManager;
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	private AuthenticationFailureHandler authenticationFailureHandler;
	private boolean ignoreFailure = false;
	private String clientId;
	private String clientSecret;
	private String jsonWebKeysURL;
	private String tokenURL;
	private String kmGroups;
	private String redirectURI;
	private String redirectURIUnAuthorized;
	private String redirectURIGeneralError;
	private static final String USERNAME = "USERNAME";
	private static final String	FIRST_NAME = "FIRST_NAME"; 
	private static final String	LAST_NAME = "LAST_NAME";
	private static final String	SSO_FIRST_NAME = "SSO_FIRST_NAME";
	private static final String	SSO_LAST_NAME = "SSO_LAST_NAME";
	private static final String KB_NAMES = "KB_NAMES";
	private static final String X_KM_AUTHORIZATION = "x-km-authorization";
	private static final String AUTH_TOKEN_COOKIE_NAME = "AuthToken";
	private static final String AGENT_INFO_COOKIE_NAME = "AgentInfo";
	/**
	 * The auhtorization code query parameter.
	 */
	private static final String HEADER_CODE = "code";

	/**
	 * Url this filter should get activated on.
	 */
	protected String filterProcessesUrl = FILTER_URL;

	/**
	 * Default name of path suffix which will invoke this filter.
	 */
	public static final String FILTER_URL = "/km/ping/authcode/";

	
	private static final NetHttpTransport httpTransport = new NetHttpTransport();
	

	
	/**
	 * 
	 */
	public KmPingOIDCAuthCodeFilter() {
		super();
		LOGGER.info("Entering KmPingOIDCAuthCodeFilter()");
		LOGGER.info("Exiting KmPingOIDCAuthCodeFilter()");
	}

	/**
	 * 
	 * @param authenticationManager
	 */
	public KmPingOIDCAuthCodeFilter(AuthenticationManager authenticationManager) {
		super();
		LOGGER.info("Entering KmPingOIDCAuthenticationFilter()");
		this.authenticationManager = authenticationManager;
		ignoreFailure = true;
		LOGGER.info("Exiting KmPingOIDCAuthenticationFilter()");
	}

	/**
	 * 
	 * @param authenticationManager
	 * @param authenticationEntryPoint
	 */
	public KmPingOIDCAuthCodeFilter(AuthenticationManager authenticationManager,
			AuthenticationEntryPoint authenticationEntryPoint) {
		super();
		LOGGER.info("Entering KmPingOIDCAuthenticationFilter()");
		this.authenticationEntryPoint = authenticationEntryPoint;
		this.authenticationManager = authenticationManager;
		//LOGGER.info("2authenticateURL::: " + authenticateURL);
		LOGGER.info("Exiting KmPingOIDCAuthenticationFilter()");
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		LOGGER.info("Entering doFilterInternal()");
		FilterInvocation fi = new FilterInvocation(request, response, chain);

		if (!processFilter(fi.getRequest())) {
			chain.doFilter(request, response);
			return;
		}

		commence(fi.getRequest(), fi.getResponse(), null);
		LOGGER.info("Exiting doFilterInternal)");
		chain.doFilter(request, response);
	}
	
	protected boolean processFilter(HttpServletRequest request) {
		boolean result = false;
		LOGGER.info("Entering processFilter()");
		result = request.getRequestURI().contains(filterProcessesUrl);
		LOGGER.debug("FilterProcessesUrl: " + filterProcessesUrl + " match: " + result);
		LOGGER.info("Exiting processFilter()");
		return result;
	}
	

	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
			throws IOException, ServletException {
		LOGGER.info("Entering commence()");
		
		String ssoUserName = null;
		String dummyPassword = "doesNotMatterForSSO1";
		String authCode = null; 
		String ssoFirstName = null; 
		String ssoLastName = null; 
		String kbList = null;
		
		String username = null;
		String password = null;
		Date expiration = null;
		
		try {
			
			
			
			authCode = request.getParameter(HEADER_CODE);
			LOGGER.debug("authCode from request parameter:::"+authCode);
			
			if (authCode == null) {
				LOGGER.error("Missing AuthCode unable to authenticate");
				return;
			}
			
			// we have authCode need to get the token
			LOGGER.debug("############################ Request Token ############################");
			

			String redirect_uri = createRedirectURI(request);
			KmPingOIDCOauthUtils oAuth = new KmPingOIDCOauthUtils();
			
			GenericData tokenRequest = new GenericData()
					.set("client_id", clientId)
					.set("client_secret",clientSecret)
					.set("grant_type", "authorization_code")
					.set("redirect_uri",redirect_uri)
					.set("code", authCode);
			
			UrlEncodedContent content = new UrlEncodedContent(tokenRequest);
			HttpRequestFactory requestFactory = httpTransport.createRequestFactory();
			
			final HttpRequest req = requestFactory.buildPostRequest(new GenericUrl(tokenURL), content)
					.setParser(new JsonObjectParser(JacksonFactory.getDefaultInstance()));
			
			HttpResponse res = null;
			
			String idToken;
			String accessToken = null;
			GenericData responseData = null;
			
			try {
				LOGGER.debug("Execute token request to :  " + tokenURL);
				res = req.execute();
				
				responseData = res.parseAs(GenericData.class);
				LOGGER.debug("responseData:  " + responseData);
			} catch (HttpResponseException hre) {
				LOGGER.error("HttpResponseException: " + hre.toString() + " - Message: " + hre.getMessage());
				throw new ServletException(hre);
			} finally {
				//close the token response stream
				if (res != null) {
					res.disconnect();
				}
			}
			
			idToken = (String) responseData.get("id_token");
			LOGGER.debug("ID token is:  " + idToken);
			
			accessToken = (String) responseData.get("access_token");							
			LOGGER.debug("Access token is:  " + accessToken);		
		
			JSONObject userObject = oAuth.getUserDetails(idToken, clientSecret, jsonWebKeysURL, kmGroups);
			
			if (userObject != null) {
				LOGGER.debug("JSON string is:  " + userObject.toString());					
				
				ssoUserName = (String) userObject.get(USERNAME); 
				ssoFirstName = (String) userObject.get(SSO_FIRST_NAME); 
				ssoLastName = (String) userObject.get(SSO_LAST_NAME); 
				kbList = (String) userObject.get(KB_NAMES);
				
				LOGGER.info("Username = '" + ssoUserName + "'");
				LOGGER.info("First Name = '" + ssoFirstName + "'");
				LOGGER.info("Last Name = '" + ssoLastName + "'");
				LOGGER.info("KB List = '" + kbList + "'");
			}
			
			if (ssoUserName.equals("pseifer1")) {
				ssoUserName = "pseiferfake";
				LOGGER.info("Overwrite user pseifer1 -> Username = '" + ssoUserName + "'");
			}
						
			//we are going to pass in all the info need through password field of the UsernamePasswordAuthenticationToken
			String credentials = dummyPassword + "|" + ssoFirstName + "|" + ssoLastName + "|" + kbList;
			
			UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
					ssoUserName, credentials);
			authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
			Authentication authResult = authenticationManager.authenticate(authRequest);
			if (authResult.isAuthenticated()) {
				LOGGER.debug("Authentication success: " + authResult);
				request.setAttribute("kmuser", ssoUserName);
				request.setAttribute("kmpassword", dummyPassword);
				// Build up the cookies
				try {
					String user = (String) authResult.getPrincipal();
					String pass = (String) authResult.getCredentials();
			        String authToken = setupToken(user,pass);		        
			        String agentInfo = ssoFirstName + " " + ssoLastName;
			        //LOGGER.debug("authToken: " + authToken);
			        //LOGGER.debug("agentInfo: " + agentInfo);
					response.setHeader(X_KM_AUTHORIZATION, authToken);
					response.setHeader(USERNAME, user);
					response.setHeader(FIRST_NAME, ssoFirstName);
					response.setHeader(LAST_NAME, ssoLastName);
					//response.setHeader(GROUPS, kbList);
					//LOGGER.debug("Adding AuthToken ['" + authToken + "'] and AgentInfo ['"+ agentInfo + "'] cookies: ");
					//response.setHeader("Set-Cookie", "AgentInfo=" + agentInfo + "; path=/");
					//response.setHeader("Set-Cookie", "AuthToken=" + authToken + "; path=/");
					LOGGER.debug("Adding " +  AGENT_INFO_COOKIE_NAME + " ['"+ agentInfo + "'] cookies");
					Cookie agentInfoCookie = new Cookie(AGENT_INFO_COOKIE_NAME, agentInfo);
					agentInfoCookie.setPath("/");
					response.addCookie(agentInfoCookie);
					LOGGER.debug("Adding " + AUTH_TOKEN_COOKIE_NAME + " ['" + authToken + "'] cookie");
					Cookie authTokenCookie = new Cookie(AUTH_TOKEN_COOKIE_NAME , authToken);
					authTokenCookie.setPath("/");
					response.addCookie(authTokenCookie);
					
				} catch (UnsupportedEncodingException eu) {
					LOGGER.error("UnsupportedEncodingException" + eu.getMessage());
				}
				
				SecurityContextHolder.getContext().setAuthentication(authResult);
				rememberMeServices.loginSuccess(request, response, authResult);
				onSuccessfulAuthentication(request, response, authResult);
				
			} else {
				LOGGER.error("Authentication failure: " + authResult);
			}

		} catch (BadCredentialsException e0) { 
			LOGGER.error("BadCredentialsException message: " + e0.toString());
			String relativePath = getRelativedPath(request);
			String errParam = "?errorMsg=" + URLEncoder.encode(e0.getMessage(), "utf-8");
			if(e0.getMessage().contains("LoginResult=14") || e0.getMessage().contains("No Knowledge base")) {				
				LOGGER.error("Redirecting to: " + relativePath + redirectURIUnAuthorized + errParam);
				response.sendRedirect(relativePath + redirectURIUnAuthorized + errParam);
			} else {
			LOGGER.error("Redirecting to: " + relativePath + redirectURIGeneralError + errParam);				
			response.sendRedirect(relativePath +redirectURIGeneralError + errParam);
			}
		} catch (AuthenticationException ae) {
			LOGGER.error("AuthenticationException message: " + ae.toString());
			String relativePath = getRelativedPath(request);
			String errParam = "?errorMsg=Exception during loginV2DAO() - " + URLEncoder.encode(ae.getMessage(), "utf-8");
			response.sendRedirect(relativePath +redirectURIGeneralError + errParam);
		} catch (Exception e1) {
			LOGGER.error("Error with authorization code", e1);
			throw new ServletException(e1);
		}

		LOGGER.info("Exiting commence()");
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
		
		try {
			authenticationSuccessHandler.onAuthenticationSuccess(request, response, authResult);
		} catch (ServletException e) {
			LOGGER.debug("Error with authenticationSuccessHandler.onAuthenticationSuccess code", e);
		}
		
	}
	
	public String setupToken(String username, String password) throws UnsupportedEncodingException {
		String creds = username + ":" +  password;
        byte[] bytesEncoded = Base64.encode(creds.getBytes());
        String encodedCredentials = new String(bytesEncoded, "US-ASCII");
        String authToken = " Basic " + encodedCredentials;
        LOGGER.debug("Set up AuthToken: " + authToken);
        return authToken;
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
	 * @param am
	 */
	public void setAuthenticationManager(AuthenticationManager am) {
		this.authenticationManager = am;
	}
	
	/**
	 * 
	 * @return
	 */
	protected AuthenticationSuccessHandler getAuthenticationSuccessHandler() {
		return authenticationSuccessHandler;
	}

	/**
	 * 
	 * @param am
	 */
	public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler ash) {
		this.authenticationSuccessHandler = ash;
	}
	
	/**
	 * 
	 * @return
	 */
	protected AuthenticationFailureHandler getAuthenticationFailureHandler() {
		return authenticationFailureHandler;
	}

	/**
	 * 
	 * @param am
	 */
	public void setAuthenticationFailureHandler(AuthenticationFailureHandler afh) {
		this.authenticationFailureHandler = afh;
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
	 * @param aep
	 */
	public void setAuthenticationEntryPoint(AuthenticationEntryPoint aep) {
		this.authenticationEntryPoint = aep;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getClientId() {
		return this.clientId;
	}
	
	/**
	 * 
	 * @param clientId
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getClientSecret() {
		return this.clientSecret;
	}
	
	/**
	 * 
	 * @param clientSecret
	 */
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getJsonWebKeysURL() {
		return this.jsonWebKeysURL;
	}
	
	/**
	 * 
	 * @param jsonWebKeysURL
	 */
	public void setJsonWebKeysURL(String jsonWebKeysURL) {
		this.jsonWebKeysURL = jsonWebKeysURL;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getKmGroups() {
		return this.kmGroups;
	}
	
	/**
	 * 
	 * @param kmGroups
	 */
	public void setKmGroups(String kmGroups) {
		this.kmGroups = kmGroups;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getTokenURL() {
		return this.tokenURL;
	}
	
	/**
	 * 
	 * @param tokenURL
	 */
	public void setTokenURL(String tokenURL) {
		this.tokenURL = tokenURL;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getRedirectURI() {
		return this.redirectURI;
	}
	
	/**
	 * 
	 * @param redirectURI
	 */
	public void setRedirectURI(String redirectURI) {
		this.redirectURI = redirectURI;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getRedirectURIUnAuthorized() {
		return this.redirectURIUnAuthorized;
	}
	
	/**
	 * 
	 * @param redirectURIUnAuthorized
	 */
	public void setRedirectURIUnAuthorized(String redirectURIUnAuthorized) {
		this.redirectURIUnAuthorized = redirectURIUnAuthorized;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getRedirectURIGeneralError() {
		return this.redirectURIGeneralError;
	}
	
	/**
	 * 
	 * @param redirectURIGeneralError
	 */
	public void setRedirectURIGeneralError(String redirectURIGeneralError) {
		this.redirectURIGeneralError = redirectURIGeneralError;
	}
	
	
	private String createRedirectURI(HttpServletRequest request) {
		
		LOGGER.info("Entering createRedirectURI()");	
		
		String strRedirectURI = "";
		String uRl = request.getRequestURL().toString();
		
		if(uRl.contains("verintkm")) {
			strRedirectURI = uRl.substring(0, uRl.indexOf("verintkm") + "verintkm".length()) + redirectURI;
		} else if(uRl.contains("contentservices")) {
			strRedirectURI = uRl.substring(0, uRl.indexOf("contentservices") + "contentservices".length()) + redirectURI;
		} else if(uRl.contains("searchservices")) {
			strRedirectURI = uRl.substring(0, uRl.indexOf("searchservices") + "searchservices".length()) + redirectURI;
		} else if(uRl.contains("filterservices")) {
			strRedirectURI = uRl.substring(0, uRl.indexOf("filterservices") + "filterservices".length()) + redirectURI;
		}
		
		if (!strRedirectURI.startsWith("https")) {
			//force it to be https  For some stupid reason the request.getRequestURL() always returns http not https
			LOGGER.debug("Forced over to https original host: " + strRedirectURI);
			strRedirectURI = strRedirectURI.replaceFirst("http", "https");
		}
		
		if (strRedirectURI.indexOf(":80/") != -1) {
			LOGGER.debug("Removing port 80 which should never be on https: " + strRedirectURI);
			strRedirectURI = strRedirectURI.replaceFirst(":80/", "/");
		}
		
		LOGGER.debug("Modified from request URL - savedUrl: " + strRedirectURI);
		LOGGER.info("Exiting createRedirectURI()");
		return strRedirectURI;
	}
	
private String getRelativedPath(HttpServletRequest request) {
		
		LOGGER.info("getRelativedPath");	
		
		String strRedirectURI = "";
		String uRl = request.getRequestURL().toString();
		
		if(uRl.contains("verintkm")) {
			strRedirectURI = "/verintkm";
		} else if(uRl.contains("contentservices")) {
			strRedirectURI = "/contentservices";
		} else if(uRl.contains("searchservices")) {
			strRedirectURI = "/searchservices";
		} else if(uRl.contains("filterservices")) {
			strRedirectURI = "/filterservices";
		}

		
		LOGGER.debug("Relatived Path: " + strRedirectURI);
		LOGGER.info("getRelativedPath()");
		return strRedirectURI;
	}
	
}
