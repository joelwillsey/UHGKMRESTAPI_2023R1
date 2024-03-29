/**
 * 
 */
package com.verint.services.km.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.verint.services.km.errorhandling.AppErrorCodes;
import com.verint.services.km.errorhandling.AppErrorMessage;
import com.verint.services.km.errorhandling.ErrorMessage;

/**
 * @author jmiller
 *
 */
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {
	private static final Logger LOGGER = LoggerFactory.getLogger(UnauthorizedEntryPoint.class);
	private static final boolean DEBUG = true;

	/**
	 * 
	 */
	public UnauthorizedEntryPoint() {
		super();
		LOGGER.info("Entering UnauthorizedEntryPoint()");
		LOGGER.info("Exiting UnauthorizedEntryPoint()");
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.web.AuthenticationEntryPoint#commence(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		LOGGER.info("Entering commence()");
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setCode(AppErrorCodes.UNAUTHORIZED_ACCESS);
		errorMessage.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		errorMessage.setMessage(AppErrorMessage.UNAUTHORIZED_ACCESS);
		StringWriter errorStackTrace = new StringWriter();
		authException.printStackTrace(new PrintWriter(errorStackTrace));
		errorMessage.setDeveloperMessage(errorStackTrace.toString());
		LOGGER.debug("AuthenticationException: ", authException);
		String json = "{\"status\":" + errorMessage.getStatus() + ",\"code\":" + errorMessage.getCode() + 
				",\"message\":\"" + errorMessage.getMessage() + "\",\"link\":\"\"";
		String finish = "}";
		if (DEBUG) {
			finish = ",\"developerMessage\":\"" + errorMessage.getDeveloperMessage() + "\"}";
		}
		json += finish;

		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    LOGGER.info("Exiting commence()");
	    response.getWriter().write(json);
//		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: Authentication token was either missing or invalid.");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}
}
