package com.verint.services.km.security;

import java.util.List;

import org.opensaml.common.binding.security.SAMLProtocolMessageXMLSignatureSecurityPolicyRule;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.binding.decoding.HTTPRedirectDeflateDecoder;
import org.opensaml.saml2.binding.security.SAML2HTTPRedirectDeflateSignatureRule;
import org.opensaml.ws.message.decoder.MessageDecoder;
import org.opensaml.ws.message.encoder.MessageEncoder;
import org.opensaml.ws.security.SecurityPolicyRule;
import org.opensaml.ws.transport.InTransport;
import org.opensaml.ws.transport.OutTransport;
import org.opensaml.ws.transport.http.HTTPInTransport;
import org.opensaml.ws.transport.http.HTTPOutTransport;
import org.opensaml.ws.transport.http.HTTPTransport;
import org.opensaml.xml.parse.ParserPool;
import org.opensaml.xml.signature.SignatureTrustEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.saml.context.SAMLMessageContext;
import org.springframework.security.saml.processor.SAMLBindingImpl;

/**
 * Http redirect binding.
 *
 * @author John Miller
 */
public class KmSAMLHTTPRedirectDeflateBinding extends SAMLBindingImpl {
	private static final Logger LOGGER = LoggerFactory.getLogger(KmSAMLHTTPRedirectDeflateBinding.class);

	/**
	 * Creates binding with default encoder and decoder.
	 *
	 * @param parserPool
	 *            parser pool
	 */
	public KmSAMLHTTPRedirectDeflateBinding(ParserPool parserPool) {
		this(new HTTPRedirectDeflateDecoder(parserPool), new KmHTTPRedirectDeflateEncoder());
		LOGGER.info("Entering KmSAMLHTTPRedirectDeflateBinding()");
		LOGGER.info("Exiting KmSAMLHTTPRedirectDeflateBinding()");
	}

	/**
	 * Constructor with customized encoder and decoder
	 *
	 * @param decoder
	 *            decoder
	 * @param encoder
	 *            encoder
	 */
	public KmSAMLHTTPRedirectDeflateBinding(MessageDecoder decoder, MessageEncoder encoder) {
		super(decoder, encoder);
		LOGGER.info("Entering KmSAMLHTTPRedirectDeflateBinding()");
		LOGGER.info("Exiting KmSAMLHTTPRedirectDeflateBinding()");
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.saml.processor.SAMLBinding#supports(org.opensaml.ws.transport.InTransport)
	 */
	@Override
	public boolean supports(InTransport transport) {
		LOGGER.info("Entering supports()");
		if (transport instanceof HTTPInTransport) {
			final HTTPTransport t = (HTTPTransport) transport;
			LOGGER.info("Exiting supports()");
			return "GET".equalsIgnoreCase(t.getHTTPMethod())
					&& (t.getParameterValue("SAMLRequest") != null || t.getParameterValue("SAMLResponse") != null);
		} else {
			LOGGER.info("Exiting supports()");
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.saml.processor.SAMLBinding#supports(org.opensaml.ws.transport.OutTransport)
	 */
	@Override
	public boolean supports(OutTransport transport) {
		return transport instanceof HTTPOutTransport;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.saml.processor.SAMLBinding#getBindingURI()
	 */
	@Override
	public String getBindingURI() {
		return SAMLConstants.SAML2_REDIRECT_BINDING_URI;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.saml.processor.SAMLBindingImpl#getSecurityPolicy(java.util.List, org.springframework.security.saml.context.SAMLMessageContext)
	 */
	@Override
	public void getSecurityPolicy(List<SecurityPolicyRule> securityPolicy, SAMLMessageContext samlContext) {
		LOGGER.info("Entering getSecurityPolicy()");
		final SignatureTrustEngine engine = samlContext.getLocalTrustEngine();
		securityPolicy.add(new SAML2HTTPRedirectDeflateSignatureRule(engine));
		securityPolicy.add(new SAMLProtocolMessageXMLSignatureSecurityPolicyRule(engine));
		LOGGER.info("Exiting getSecurityPolicy()");
	}
}