/**
 * 
 */
package com.verint.services.km.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;


/**
 * @author John.Miller
 *
 */
public class KmOIDCSAMLAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(KmOIDCSAMLAuthenticationSuccessHandler.class);

	private String verintkm;
	private String contentservices;
	private String searchservices;
	private String filterservices;
	private String VERINT_KM = "/verintkm";
	private String CONTENT_SERVICES = "/contentservices";
	private String SEARCH_SERVICES = "/searchservicesss";
	private String FILTER_SERVICES = "/filterservices";
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		LOGGER.info("Entering onAuthenticationSuccess()");
		String savedUrl = "";
		// Go through the cookies to find original URL
		final Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (int x = 0; x < cookies.length; x++) {
				if ("savedurl".equals(cookies[x].getName())) {
					savedUrl = "/" + cookies[x].getValue();
					savedUrl = savedUrl.replaceAll("%3F", "?");
					savedUrl = savedUrl.replaceAll("%3D", "=");
				}
			}
		}
		//clearAuthenticationAttributes(request);		
		
		LOGGER.debug("Request - RequestURL: " + request.getRequestURL().toString());
	
		String newUrl = checkServiceContext(savedUrl);

		// Use the DefaultSavedRequest URL
		LOGGER.debug("Sending contents of URL: " + newUrl);
	
		if (response.isCommitted()) {
	        logger.debug("Response has already been committed. Unable to redirect to " + newUrl);
	        return;
	    }
		
		getRedirectStrategy().sendRedirect(request, response, newUrl);
		//response.sendRedirect(newUrl);
		LOGGER.info("Exiting onAuthenticationSuccess()");
	}
	
	public String checkServiceContext(String url) {
		String result = url;
		
		String[] verintkmAry = verintkm.split(",");
		String[] contentservicesAry = contentservices.split(",");
		String[] searchservicessAry = searchservices.split(",");
		String[] filterservicesAry = filterservices.split(",");
		
		if (compareString2Array(url,verintkmAry)) {
			result = VERINT_KM + url;
		} else if (compareString2Array(url,contentservicesAry)) {
			result = CONTENT_SERVICES + url;
		} else if (compareString2Array(url,searchservicessAry)) {
			result = SEARCH_SERVICES + url;
		} else if (compareString2Array(url,filterservicesAry)) {
			result = FILTER_SERVICES + url;
		}
			
		//This is tied to the line getRedirectStrategy().sendRedirect(request, response, newUrl);
		result = url;
				
		return result;
	}
	
	public boolean compareString2Array(String url, String[] serviceAry) {
		boolean result = false;
		
		if (url != null) 
		{
			for (int i = 0; i < serviceAry.length; i++) 
			{
				LOGGER.debug("serviceAry["+i+"]=" + serviceAry[i]);
				if (url.contains(serviceAry[i].trim())) 
				{
					LOGGER.debug("Match on serviceAry["+i+"]=" + serviceAry[i] + " to " + url);
					result = true;
					return result;
				}
			}
		}
		
		return result;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public String getVerintkm() {
		return this.verintkm;
	}
	
	/**
	 * 
	 * @param verintkm
	 */
	public void setVerintkm(String verintkm) {
		this.verintkm = verintkm;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getcontentservices() {
		return this.contentservices;
	}
	
	/**
	 * 
	 * @param contentservices
	 */
	public void setContentservices(String contentservices) {
		this.contentservices = contentservices;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getsearchservices() {
		return this.searchservices;
	}
	
	/**
	 * 
	 * @param searchservicess
	 */
	public void setsearchservices(String searchservices) {
		this.searchservices = searchservices;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getFilterservices() {
		return this.filterservices;
	}
	
	/**
	 * 
	 * @param filterservices
	 */
	public void setFilterservices(String filterservices) {
		this.filterservices = filterservices;
	}
}