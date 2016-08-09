/**
 * 
 */
package com.verint.services.km.security;

import java.util.Collection;

import javax.xml.namespace.QName;

import org.opensaml.common.SAMLException;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.metadata.IDPSSODescriptor;
import org.opensaml.saml2.metadata.provider.MetadataProviderException;
import org.opensaml.ws.message.decoder.MessageDecoder;
import org.opensaml.ws.message.decoder.MessageDecodingException;
import org.opensaml.ws.message.encoder.MessageEncoder;
import org.opensaml.ws.message.encoder.MessageEncodingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.saml.context.SAMLMessageContext;
import org.springframework.security.saml.metadata.MetadataManager;
import org.springframework.security.saml.processor.SAMLBinding;
import org.springframework.security.saml.processor.SAMLProcessorImpl;
import org.springframework.util.Assert;

/**
 * @author jmiller
 *
 */
public class KmSAMLProcessorImpl extends SAMLProcessorImpl {
	private static final Logger LOGGER = LoggerFactory.getLogger(KmSAMLProcessorImpl.class);

	/**
	 * 
	 * @param binding
	 */
    public KmSAMLProcessorImpl(SAMLBinding binding) {
    	super(binding);
    	LOGGER.info("Entering KmSAMLProcessorImpl()");
    	LOGGER.info("Exiting KmSAMLProcessorImpl()");
    }

    /**
     * Creates a processor supporting multiple bindings.
     *
     * @param bindings bindings
     */
    public KmSAMLProcessorImpl(Collection<SAMLBinding> bindings) {
    	super(bindings);
    	LOGGER.info("Entering KmSAMLProcessorImpl()");
    	LOGGER.info("Exiting KmSAMLProcessorImpl()");
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.security.saml.processor.SAMLProcessorImpl#sendMessage(org.springframework.security.saml.context.SAMLMessageContext, boolean, org.springframework.security.saml.processor.SAMLBinding)
     */
    @Override
    protected SAMLMessageContext sendMessage(SAMLMessageContext samlContext, boolean sign, SAMLBinding binding) throws SAMLException, MetadataProviderException, MessageEncodingException {
    	LOGGER.info("Entering sendMessage()");
        verifyContext(samlContext);

        if (sign) {
            Assert.notNull(samlContext.getLocalSigningCredential(), "Cannot sign outgoing message as no signing credential is set in the context");
            samlContext.setOutboundSAMLMessageSigningCredential(samlContext.getLocalSigningCredential());
        }
        final MessageEncoder encoder = binding.getMessageEncoder();
        LOGGER.info("Encoder: " + encoder.getClass().getName());
        encoder.encode(samlContext);

        LOGGER.info("Exiting sendMessage()");
        return samlContext;
    }
    
    /*
     * (non-Javadoc)
     * @see org.springframework.security.saml.processor.SAMLProcessorImpl#retrieveMessage(org.springframework.security.saml.context.SAMLMessageContext, org.springframework.security.saml.processor.SAMLBinding)
     */
    @Override
    public SAMLMessageContext retrieveMessage(SAMLMessageContext samlContext, SAMLBinding binding) throws SAMLException, MetadataProviderException, MessageDecodingException, org.opensaml.xml.security.SecurityException {
    	LOGGER.info("Entering retrieveMessage()");
    	LOGGER.debug("SAMLMessageContext {}", samlContext);
        LOGGER.debug("Retrieving message using binding {}", binding.getBindingURI());

        // Verify the context
        verifyContext(samlContext);
        populateSecurityPolicy(samlContext, binding);

        QName peerEntityRole = samlContext.getPeerEntityRole();
        if (peerEntityRole == null) {
            peerEntityRole = IDPSSODescriptor.DEFAULT_ELEMENT_NAME;
        }
        samlContext.setPeerEntityRole(peerEntityRole);
        samlContext.setInboundSAMLProtocol(SAMLConstants.SAML20P_NS);
        samlContext.setInboundSAMLBinding(binding.getBindingURI());

        // Decode the message
        final MessageDecoder decoder = binding.getMessageDecoder();
        decoder.decode(samlContext);

        if (samlContext.getPeerEntityMetadata() == null) {
            throw new MetadataProviderException("Metadata for issuer " + samlContext.getInboundMessageIssuer() + " wasn't found");
        }
        samlContext.setPeerEntityId(samlContext.getPeerEntityMetadata().getEntityID());
        samlContext.setPeerExtendedMetadata(((MetadataManager) samlContext.getMetadataProvider()).getExtendedMetadata(samlContext.getPeerEntityId()));

        LOGGER.info("Exiting retrieveMessage()");
        return samlContext;
    }
}