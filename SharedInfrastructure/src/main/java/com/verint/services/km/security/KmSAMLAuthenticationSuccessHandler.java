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
public class KmSAMLAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(KmSAMLAuthenticationSuccessHandler.class);

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
		clearAuthenticationAttributes(request);

		// Use the DefaultSavedRequest URL
		LOGGER.debug("Sending contents of URL: " + savedUrl);
		getRedirectStrategy().sendRedirect(request, response, savedUrl);
		LOGGER.info("Exiting onAuthenticationSuccess()");
	}
}