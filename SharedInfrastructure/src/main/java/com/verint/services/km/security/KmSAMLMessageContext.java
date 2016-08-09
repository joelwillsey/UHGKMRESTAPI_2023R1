package com.verint.services.km.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.saml.context.SAMLMessageContext;

public class KmSAMLMessageContext extends SAMLMessageContext {
	private static final Logger LOGGER = LoggerFactory.getLogger(KmSAMLMessageContext.class);
	protected String redirectUrl;
	protected String encodedSAMLResponse;
	
	/**
	 * 
	 */
	public KmSAMLMessageContext() {
		super();
    	LOGGER.info("Entering KmSAMLMessageContext()");
    	LOGGER.info("Exiting KmSAMLMessageContext()");
	}

	/**
	 * @return the redirectUrl
	 */
	public String getRedirectUrl() {
		return redirectUrl;
	}

	/**
	 * @param redirectUrl the redirectUrl to set
	 */
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	/**
	 * @return the encodedSAMLResponse
	 */
	public String getEncodedSAMLResponse() {
		return encodedSAMLResponse;
	}

	/**
	 * @param encodedSAMLResponse the encodedSAMLResponse to set
	 */
	public void setEncodedSAMLResponse(String encodedSAMLResponse) {
		this.encodedSAMLResponse = encodedSAMLResponse;
	}
}