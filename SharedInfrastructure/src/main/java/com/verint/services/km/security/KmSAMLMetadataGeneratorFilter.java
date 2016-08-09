/**
 * 
 */
package com.verint.services.km.security;

import javax.servlet.http.HttpServletRequest;

import org.opensaml.util.SimpleURLCanonicalizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.saml.metadata.MetadataGenerator;
import org.springframework.security.saml.metadata.MetadataGeneratorFilter;

/**
 * @author jmiller
 *
 */
public class KmSAMLMetadataGeneratorFilter extends MetadataGeneratorFilter {
	private static final Logger LOGGER = LoggerFactory.getLogger(KmSAMLMetadataGeneratorFilter.class);

	/**
	 * 
	 */
	public KmSAMLMetadataGeneratorFilter(MetadataGenerator generator) {
		super(generator);
		LOGGER.info("Entering KmSamlMetadataGeneratorFilter()");
		LOGGER.info("Exiting KmSamlMetadataGeneratorFilter()");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.saml.metadata.MetadataGeneratorFilter#getDefaultBaseURL(javax.servlet.http.HttpServletRequest)
	 */
	@Override
    protected String getDefaultBaseURL(HttpServletRequest request) {
		LOGGER.info("Entering getDefaultBaseURL()");
        final StringBuilder sb = new StringBuilder();
        sb.append(request.getScheme()).append("://").append(request.getServerName()).append(":").append(request.getServerPort());
        sb.append(request.getContextPath()).append("/km");
        LOGGER.debug("URL: " + sb.toString());
        if (isNormalizeBaseUrl()) {
        	LOGGER.info("Exiting getDefaultBaseURL()");
            return SimpleURLCanonicalizer.canonicalize(sb.toString());
        } else {
        	LOGGER.info("Exiting getDefaultBaseURL()");
            return sb.toString();
        }
    }
}