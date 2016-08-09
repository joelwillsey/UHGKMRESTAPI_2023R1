package com.verint.services.km.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.opensaml.saml2.metadata.provider.MetadataProviderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.saml.context.SAMLContextProviderImpl;
import org.springframework.security.saml.context.SAMLMessageContext;

/**
 * 
 * @author John.Miller
 *
 */
public class KmSAMLContextProviderImpl extends SAMLContextProviderImpl {
	private static final Logger LOGGER = LoggerFactory.getLogger(KmSAMLProcessorImpl.class);
	protected String redirectUrl;
	
	/**
	 * 
	 */
	public KmSAMLContextProviderImpl() {
		super();
    	LOGGER.info("Entering KmSAMLContextProviderImpl()");
    	LOGGER.info("Exiting KmSAMLContextProviderImpl()");
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.saml.context.SAMLContextProviderImpl#getLocalEntity(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
    public SAMLMessageContext getLocalEntity(HttpServletRequest request, HttpServletResponse response) throws MetadataProviderException {
		LOGGER.info("Entering getLocalEntity()");
		final KmSAMLMessageContext context = new KmSAMLMessageContext();
        populateGenericContext(request, response, context);
        populateLocalEntityId(context, request.getRequestURI());
        populateLocalContext(context);
        LOGGER.debug("KmSAMLMessageContext: " + context);
        LOGGER.info("Exiting getLocalEntity()");
        return context;
    }

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.saml.context.SAMLContextProviderImpl#getLocalAndPeerEntity(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
    public SAMLMessageContext getLocalAndPeerEntity(HttpServletRequest request, HttpServletResponse response) throws MetadataProviderException {
		LOGGER.info("Entering getLocalAndPeerEntity()");
        final KmSAMLMessageContext context = new KmSAMLMessageContext();
        populateGenericContext(request, response, context);
        populateLocalEntityId(context, request.getRequestURI());
        populateLocalContext(context);
        populatePeerEntityId(context);
        populatePeerContext(context);
        LOGGER.debug("KmSAMLMessageContext: " + context);
        LOGGER.info("Exiting getLocalAndPeerEntity()");
        return context;
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
}