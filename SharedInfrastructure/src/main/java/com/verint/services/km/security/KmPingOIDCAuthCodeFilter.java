package com.verint.services.km.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.net.ssl.SSLHandshakeException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.AxisFault;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.jose4j.json.internal.json_simple.parser.JSONParser;
import org.jose4j.json.internal.json_simple.parser.ParseException;
import org.jose4j.lang.JoseException;
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
	private String oidcTokenServiceURL;
	private String oidcTokenServiceGrantType;
	private String oidcTokenServiceRedirectUri;
	private String oidcTokenServiceResponseType;
	private String oidcTokenServiceClientId;
	private String oidcTokenServiceScope;
	private int authTokenExpirationDuration;
	private static final String USERNAME = "USERNAME";
	private static final String	FIRST_NAME = "FIRST_NAME"; 
	private static final String	LAST_NAME = "LAST_NAME";
	private static final String	SSO_FIRST_NAME = "SSO_FIRST_NAME";
	private static final String	SSO_LAST_NAME = "SSO_LAST_NAME";
	private static final String KB_NAMES = "KB_NAMES";
	private static final String X_KM_AUTHORIZATION = "x-km-authorization";
	private static final String AUTH_TOKEN_COOKIE_NAME = "AuthToken";
	private static final String AGENT_INFO_COOKIE_NAME = "AgentInfo";
	public static final String ACCESS_TOKEN = "access_token";
	public static final String TOKEN_TYPE = "token_type";
	public static final String EXPIRES_IN = "expires_in";
	public static final String ID_TOKEN = "id_token";
	private static final String VERINT_AUTH_TOKEN_COOKIE_NAME = "verintAuthToken";
	
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
				LOGGER.error("Token Request - HttpResponseException: " + hre.toString() + " - Message: " + hre.getMessage());
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
					LOGGER.debug("Adding " +  AGENT_INFO_COOKIE_NAME + " ['"+ agentInfo + "'] cookies");
					Cookie agentInfoCookie = new Cookie(AGENT_INFO_COOKIE_NAME, agentInfo);
					agentInfoCookie.setPath("/");
					response.addCookie(agentInfoCookie);
					LOGGER.debug("Adding " + AUTH_TOKEN_COOKIE_NAME + " ['" + authToken + "'] cookie");
					Cookie authTokenCookie = new Cookie(AUTH_TOKEN_COOKIE_NAME , authToken);
					authTokenCookie.setPath("/");
					//Remove expiration to make it a session cookie
					//authTokenCookie.setMaxAge(authTokenExpirationDuration);
					response.addCookie(authTokenCookie);
					
				} catch (UnsupportedEncodingException eu) {
					LOGGER.error("UnsupportedEncodingException" + eu.getMessage());
					throw eu;
				}
				
				//get token from oidc-token-service
				JSONObject verintOidcToken = getVerintOIDCToken(idToken);
				
				// Create verint oidc token cookie
				if (verintOidcToken.containsKey(ID_TOKEN)) {
					String verintIdToken = (String) verintOidcToken.get(ID_TOKEN);
					Long verintIdTokenExpiresl = (Long) verintOidcToken.get(EXPIRES_IN);
					int verintIdTokenExpires = verintIdTokenExpiresl.intValue();
					int expiryTimeInSeconds = getExpriyTimeInSecondsFromNow(verintIdTokenExpires);					
					LOGGER.debug("verintOidcToken: " + verintOidcToken);
					LOGGER.debug("Adding " + VERINT_AUTH_TOKEN_COOKIE_NAME + " ['" + verintIdToken + "'] cookie");
					//LOGGER.debug("Adding " + VERINT_AUTH_TOKEN_COOKIE_NAME + " ['" + verintIdToken + "'] cookie, expires in "+ expiryTimeInSeconds);
					Cookie verintAuthTokenCookie = new Cookie(VERINT_AUTH_TOKEN_COOKIE_NAME , verintIdToken);
					//Remove expiration to make it a session cookie
					//verintAuthTokenCookie.setMaxAge(expiryTimeInSeconds);
					verintAuthTokenCookie.setPath("/");
					response.addCookie(verintAuthTokenCookie);
				} else {
					LOGGER.error("Unable to read verintOidcToken json: " + verintOidcToken.toString());
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
			String errString = "Exception during loginV2DAO() - " + ae.getMessage();
			String errParam = "?errorMsg=" + URLEncoder.encode(errString, "utf-8");
			response.sendRedirect(relativePath +redirectURIGeneralError + errParam);
		} catch (JoseException je) {
			String relativePath = getRelativedPath(request);
			String errString = "Exception during SSO - " + je.getMessage();
			String errParam = "?errorMsg=" + URLEncoder.encode(errString, "utf-8");
			LOGGER.error(errString);
			response.sendRedirect(relativePath +redirectURIUnAuthorized + errParam);
		} catch (RemoteException re) {
			String relativePath = getRelativedPath(request);
			String errString = "";
			if(re instanceof AxisFault) {
				errString = "AxisFault - " + ((AxisFault)re).getFaultString();
			} else {
			LOGGER.error("RemoteException: " + re.toString());
				errString = "RemoteException - " + re.getMessage();
			}
			String errParam = "?errorMsg=" + URLEncoder.encode(errString, "utf-8");
			response.sendRedirect(relativePath +redirectURIGeneralError + errParam);
		}catch (HttpResponseException hre1) {
			String relativePath = getRelativedPath(request);
			String errString = "Error with authorization code, HttpResponseException: " + hre1;
			LOGGER.error(errString);
			String errParam = "?errorMsg=" + URLEncoder.encode(errString, "utf-8");
			response.sendRedirect(relativePath +redirectURIUnAuthorized + errParam);
        }catch (Exception e1) {
			LOGGER.error("Error with authorization code: " + e1);
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
	

	  public JSONObject getVerintOIDCToken(String pingIdToken) {
		  JSONObject verintOIDCToken = null;
		  
		  LOGGER.debug("Entering getVerintOIDCToken()");
		  
		  CloseableHttpClient httpClient = HttpClients.createDefault();
		  HttpPost httpPost = new HttpPost(oidcTokenServiceURL);
		 
		  try {
			// Request parameters and other properties.
			  List<NameValuePair> form = new ArrayList<NameValuePair>();
			  form.add(new BasicNameValuePair("grant_type", oidcTokenServiceGrantType));
			  form.add(new BasicNameValuePair("redirect_uri", oidcTokenServiceRedirectUri));
			  //form.add(new BasicNameValuePair("response_type", oidcTokenServiceResponseType));
			  form.add(new BasicNameValuePair("client_id", oidcTokenServiceClientId));
			  form.add(new BasicNameValuePair("scope", oidcTokenServiceScope));
			  UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form,  "UTF-8");
			  httpPost.setEntity(entity);
			  httpPost.setHeader("Ping-token", pingIdToken);
	
			// Create a custom response handler
	          ResponseHandler < String > responseHandler = response -> {
	              int status = response.getStatusLine().getStatusCode();
	              if (status >= 200 && status < 300) {
	                  HttpEntity responseEntity = response.getEntity();
	                  return responseEntity != null ? EntityUtils.toString(responseEntity) : null;
	              } else {
	                  throw new ClientProtocolException("Unexpected response status code: " + status);
	              }
	          };
	          
	          
	          
			  //Execute and get the response.
	          LOGGER.debug("oidc-token-service Request - " + httpPost.getURI().toString());
	          String response = httpClient.execute(httpPost, responseHandler);
	          LOGGER.debug("oidc-token-service Response - " + response);
	          
	          verintOIDCToken = (JSONObject) new JSONParser().parse(response);
	          	          
			} catch (ClientProtocolException e) {
				LOGGER.error("ClientProtocolException - " + e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				LOGGER.error("IOException - " + e.getMessage());
				e.printStackTrace();
			} catch (ParseException e) {
				LOGGER.error("ParseException - " + e.getMessage());
				e.printStackTrace();
			}
		 
		  LOGGER.debug("Exiting getVerintOIDCToken()");		  
		  return verintOIDCToken;
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
	public int getAuthTokenExpirationDuration() {
		return this.authTokenExpirationDuration;
	}
	
	/**
	 * 
	 * @param authTokenExpirationDuration
	 */
	public void setAuthTokenExpirationDuration(int authTokenExpirationDuration) {
		this.authTokenExpirationDuration = authTokenExpirationDuration;		
	}
	
	/**
	 * 
	 * @return
	 */
	public String getOidcTokenServiceURL() {
		return this.oidcTokenServiceURL;
	}
	
	/**
	 * 
	 * @param clientId
	 */
	public void setOidcTokenServiceURL(String oidcTokenServiceURL) {
		this.oidcTokenServiceURL = oidcTokenServiceURL;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getOidcTokenServiceGrantType() {
		return this.oidcTokenServiceGrantType;
	}
	
	/**
	 * 
	 * @param clientId
	 */
	public void setOidcTokenServiceGrantType(String oidcTokenServiceGrantType) {
		this.oidcTokenServiceGrantType = oidcTokenServiceGrantType;
	}

	/**
	 * 
	 * @return
	 */
	public String getOidcTokenServiceRedirectUriL() {
		return this.oidcTokenServiceRedirectUri;
	}
	
	/**
	 * 
	 * @param clientId
	 */
	public void setOidcTokenServiceRedirectUri(String oidcTokenServiceRedirectUri) {
		this.oidcTokenServiceRedirectUri = oidcTokenServiceRedirectUri;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getOidcTokenServiceResponseType() {
		return this.oidcTokenServiceResponseType;
	}
	
	/**
	 * 
	 * @param clientId
	 */
	public void setOidcTokenServiceResponseType(String oidcTokenServiceResponseType) {
		this.oidcTokenServiceResponseType = oidcTokenServiceResponseType;
	}

	/**
	 * 
	 * @return
	 */
	public String getOidcTokenServiceClientId() {
		return this.oidcTokenServiceClientId;
	}
	
	/**
	 * 
	 * @param clientId
	 */
	public void setOidcTokenServiceClientId(String oidcTokenServiceClientId) {
		this.oidcTokenServiceClientId = oidcTokenServiceClientId;
	}

	/**
	 * 
	 * @return
	 */
	public String getOidcTokenServiceScope() {
		return this.oidcTokenServiceScope;
	}
	
	/**
	 * 
	 * @param clientId
	 */
	public void setOidcTokenServiceScope(String oidcTokenServiceScope) {
		this.oidcTokenServiceScope = oidcTokenServiceScope;
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
	
	public static int getExpriyTimeInSecondsFromNow(int epochTime) {
	
		Long currentEpochTimeInMilliSeconds = System.currentTimeMillis()/1000;
		int currentEpochTimeInSeconds = currentEpochTimeInMilliSeconds.intValue();
		if (epochTime > currentEpochTimeInSeconds) {
			return epochTime - currentEpochTimeInSeconds;
		}
	
		return 0; 
	}
	
}
