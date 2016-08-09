package com.verint.services.km.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.opensaml.common.SAMLException;
import org.opensaml.saml2.metadata.AssertionConsumerService;
import org.opensaml.saml2.metadata.SPSSODescriptor;
import org.opensaml.saml2.metadata.provider.MetadataProviderException;
import org.opensaml.util.URLBuilder;
import org.opensaml.ws.message.encoder.MessageEncodingException;
import org.opensaml.ws.transport.http.HTTPInTransport;
import org.opensaml.ws.transport.http.HTTPOutTransport;
import org.opensaml.ws.transport.http.HttpServletRequestAdapter;
import org.opensaml.xml.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.saml.SAMLConstants;
import org.springframework.security.saml.SAMLDiscovery;
import org.springframework.security.saml.context.SAMLContextProvider;
import org.springframework.security.saml.context.SAMLMessageContext;
import org.springframework.security.saml.log.SAMLLogger;
import org.springframework.security.saml.metadata.MetadataManager;
import org.springframework.security.saml.util.SAMLUtil;
import org.springframework.security.saml.websso.WebSSOProfile;
import org.springframework.security.saml.websso.WebSSOProfileOptions;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.FilterInvocation;
import org.springframework.util.Assert;
import org.springframework.web.filter.GenericFilterBean;

import com.verint.services.km.errorhandling.AppErrorCodes;
import com.verint.services.km.errorhandling.AppErrorMessage;
import com.verint.services.km.errorhandling.ErrorMessage;

/**
 * 
 * @author John.Miller
 *
 */
public class KmSAMLEntryPoint extends GenericFilterBean implements AuthenticationEntryPoint {
	private final static Logger LOGGER = LoggerFactory.getLogger(KmSAMLEntryPoint.class);
	protected WebSSOProfileOptions defaultOptions;
	protected WebSSOProfile webSSOprofile;
	protected WebSSOProfile webSSOprofileECP;
	protected WebSSOProfile webSSOprofileHoK;
	protected MetadataManager metadata;
	protected SAMLLogger samlLogger;
	protected SAMLContextProvider contextProvider;
	protected SAMLDiscovery samlDiscovery;
	protected String idpUrl;
	private static final boolean DEBUG = false;

	/**
	 * Url this filter should get activated on.
	 */
	protected String filterProcessesUrl = FILTER_URL;

	/**
	 * Default name of path suffix which will invoke this filter.
	 */
	public static final String FILTER_URL = "/saml/login";

	/**
	 * Name of parameter of HttpRequest telling entry point that the login
	 * should use specified idp.
	 */
	public static final String IDP_PARAMETER = "idp";

	/**
	 * Parameter is used to indicate response from IDP discovery service. When
	 * present IDP discovery is not invoked again.
	 */
	public static final String DISCOVERY_RESPONSE_PARAMETER = "disco";

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		LOGGER.info("Entering doFilter()");
		FilterInvocation fi = new FilterInvocation(request, response, chain);

		if (!processFilter(fi.getRequest())) {
			chain.doFilter(request, response);
			return;
		}

		commence(fi.getRequest(), fi.getResponse(), null);
		LOGGER.info("Exiting doFilter()");
	}

	/**
	 * The filter will be used in case the URL of the request contains the
	 * DEFAULT_FILTER_URL.
	 *
	 * @param request
	 *            request used to determine whether to enable this filter
	 * @return true if this filter should be used
	 */
	protected boolean processFilter(HttpServletRequest request) {
		LOGGER.info("Entering processFilter()");
		LOGGER.debug("FilterProcessesUrl: " + filterProcessesUrl);
		LOGGER.info("Exiting processFilter()");
		return SAMLUtil.processFilter(filterProcessesUrl, request);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.web.AuthenticationEntryPoint#commence(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
			throws IOException, ServletException {
		LOGGER.info("Entering commence()");
		try {
			final KmSAMLMessageContext context = (KmSAMLMessageContext)contextProvider.getLocalAndPeerEntity(request, response);
			LOGGER.debug("SAMLMessageContext: " + context);
			request.setAttribute(org.springframework.security.saml.SAMLConstants.PEER_ENTITY_ID, getIdpUrl());
			initializeSSO(context, e);

			// Setup the error message
			final ErrorMessage errorMessage = new ErrorMessage();
			errorMessage.setCode(AppErrorCodes.UNAUTHORIZED_ACCESS);
			errorMessage.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			errorMessage.setMessage(AppErrorMessage.UNAUTHORIZED_ACCESS);
			
			// Setup the print stack trace
			final StringWriter errorStackTrace = new StringWriter();
			e.printStackTrace(new PrintWriter(errorStackTrace));
			errorMessage.setDeveloperMessage(errorStackTrace.toString());
			LOGGER.debug("AuthenticationException: ", e);
			String json = "{\"status\":" + errorMessage.getStatus() + ",\"code\":" + errorMessage.getCode()
					+ ",\"message\":\"" + errorMessage.getMessage() + "\",\"link\":\"" + context.getRedirectUrl() + "\"";
			String finish = "}";
			if (DEBUG) {
				finish = ",\"developerMessage\":\"" + errorMessage.getDeveloperMessage() + "\"}";
			}
			json += finish;
			LOGGER.debug("Auth JSON: " + json);
			
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
			LOGGER.info("Writing JSON out");
		} catch (SAMLException e1) {
			LOGGER.debug("Error initializing entry point", e1);
			throw new ServletException(e1);
		} catch (MetadataProviderException e1) {
			LOGGER.debug("Error initializing entry point", e1);
			throw new ServletException(e1);
		} catch (MessageEncodingException e1) {
			LOGGER.debug("Error initializing entry point", e1);
			throw new ServletException(e1);
		}
		LOGGER.info("Exiting commence()");
	}

	/**
	 * Initializes ECP profile.
	 * <p>
	 * Subclasses can alter the initialization behaviour.
	 *
	 * @param context
	 *            saml context, also containing wrapped request and response
	 *            objects
	 * @param e
	 *            exception causing the entry point to be invoked (if any)
	 * @throws MetadataProviderException
	 *             in case metadata can't be queried
	 * @throws SAMLException
	 *             in case message sending fails
	 * @throws MessageEncodingException
	 *             in case SAML message encoding fails
	 */
	protected void initializeECP(SAMLMessageContext context, AuthenticationException e)
			throws MetadataProviderException, SAMLException, MessageEncodingException {
		LOGGER.info("Entering initializeECP()");
		WebSSOProfileOptions options = getProfileOptions(context, e);
		LOGGER.debug("Processing SSO using ECP profile");
		webSSOprofileECP.sendAuthenticationRequest(context, options);
		samlLogger.log(SAMLConstants.AUTH_N_REQUEST, SAMLConstants.SUCCESS, context);
		LOGGER.info("Exiting initializeECP()");
	}

	/**
	 * WebSSO profile or WebSSO Holder-of-Key profile. Selection is made based
	 * on the settings of the Service Provider. In case Enhanced Client/Proxy is
	 * enabled and the request claims to support this profile it is used.
	 * Otherwise it is verified what is the binding and profile specified for
	 * the assertionConsumerIndex in the WebSSOProfileOptions. In case it is HoK
	 * the WebSSO Holder-of-Key profile is used, otherwise the ordinary WebSSO.
	 * <p>
	 * Subclasses can alter the initialization behaviour.
	 *
	 * @param context
	 *            saml context, also containing wrapped request and response
	 *            objects
	 * @param e
	 *            exception causing the entry point to be invoked (if any)
	 * @throws MetadataProviderException
	 *             in case metadata can't be queried
	 * @throws SAMLException
	 *             in case message sending fails
	 * @throws MessageEncodingException
	 *             in case SAML message encoding fails
	 */
	protected void initializeSSO(SAMLMessageContext context, AuthenticationException e)
			throws MetadataProviderException, SAMLException, MessageEncodingException {
		LOGGER.info("Entering initializeSSO()");
		// Generate options for the current SSO request
		WebSSOProfileOptions options = getProfileOptions(context, e);

		// Determine the assertionConsumerService to be used
		AssertionConsumerService consumerService = SAMLUtil.getConsumerService(
				(SPSSODescriptor) context.getLocalEntityRoleMetadata(), options.getAssertionConsumerIndex());

		// HoK WebSSO
		if (SAMLConstants.SAML2_HOK_WEBSSO_PROFILE_URI.equals(consumerService.getBinding())) {
			if (webSSOprofileHoK == null) {
				LOGGER.warn(
						"WebSSO HoK profile was specified to be used, but profile is not configured in the EntryPoint, HoK will be skipped");
			} else {
				LOGGER.debug("Processing SSO using WebSSO HolderOfKey profile");
				webSSOprofileHoK.sendAuthenticationRequest(context, options);
				samlLogger.log(SAMLConstants.AUTH_N_REQUEST, SAMLConstants.SUCCESS, context);
				return;
			}
		}

		// Ordinary WebSSO
		LOGGER.debug("Processing SSO using WebSSO profile");
		LOGGER.info("webSSOprofile className: " + webSSOprofile.getClass().getName());
		webSSOprofile.sendAuthenticationRequest(context, options);
		samlLogger.log(SAMLConstants.AUTH_N_REQUEST, SAMLConstants.SUCCESS, context);
		LOGGER.info("Exiting initializeSSO()");
	}

	/**
	 * Method initializes IDP Discovery Profile as defined in
	 * http://docs.oasis-open.org/security/saml/Post2.0/sstc-saml-idp-discovery.
	 * pdf It is presumed that metadata of the local Service Provider contains
	 * discovery return address.
	 *
	 * @param context
	 *            saml context also containing request and response objects
	 * @throws ServletException
	 *             error
	 * @throws IOException
	 *             io error
	 * @throws MetadataProviderException
	 *             in case metadata of the local entity can't be populated
	 */
	protected void initializeDiscovery(SAMLMessageContext context)
			throws ServletException, IOException, MetadataProviderException {
		LOGGER.info("Entering initializeDiscovery()");
		String discoveryURL = context.getLocalExtendedMetadata().getIdpDiscoveryURL();
		LOGGER.debug("discoveryURL: " + discoveryURL);

		if (discoveryURL != null) {
			URLBuilder urlBuilder = new URLBuilder(discoveryURL);
			List<Pair<String, String>> queryParams = urlBuilder.getQueryParams();
			queryParams.add(new Pair<String, String>(SAMLDiscovery.ENTITY_ID_PARAM, context.getLocalEntityId()));
			queryParams.add(new Pair<String, String>(SAMLDiscovery.RETURN_ID_PARAM, IDP_PARAMETER));
			discoveryURL = urlBuilder.buildURL();
			LOGGER.debug("discoveryURL with params: " + discoveryURL);
			LOGGER.debug("Using discovery URL from extended metadata");
		} else {
			String discoveryUrl = SAMLDiscovery.FILTER_URL;
			if (samlDiscovery != null) {
				discoveryUrl = samlDiscovery.getFilterProcessesUrl();
			}
			LOGGER.debug("discoveryURL before: " + discoveryURL);
			String contextPath = (String) context.getInboundMessageTransport()
					.getAttribute(SAMLConstants.LOCAL_CONTEXT_PATH);
			discoveryURL = contextPath + discoveryUrl + "?" + SAMLDiscovery.RETURN_ID_PARAM + "=" + IDP_PARAMETER + "&"
					+ SAMLDiscovery.ENTITY_ID_PARAM + "=" + context.getLocalEntityId();
			LOGGER.debug("discoveryURL after: " + discoveryURL);
			LOGGER.debug("Using local discovery URL");
		}
		LOGGER.debug("Redirecting to discovery URL {}", discoveryURL);
		HTTPOutTransport response = (HTTPOutTransport) context.getOutboundMessageTransport();
		LOGGER.info("Exiting initializeDiscovery()");
		response.sendRedirect(discoveryURL);
	}

	/**
	 * Method is supposed to populate preferences used to construct the SAML
	 * message. Method can be overridden to provide logic appropriate for given
	 * application. In case defaultOptions object was set it will be used as
	 * basis for construction and request specific values will be update (idp
	 * field).
	 *
	 * @param context
	 *            containing local entity
	 * @param exception
	 *            exception causing invocation of this entry point (can be null)
	 * @return populated webSSOprofile
	 * @throws MetadataProviderException
	 *             in case metadata loading fails
	 */
	protected WebSSOProfileOptions getProfileOptions(SAMLMessageContext context, AuthenticationException exception)
			throws MetadataProviderException {

		WebSSOProfileOptions ssoProfileOptions;
		if (defaultOptions != null) {
			ssoProfileOptions = defaultOptions.clone();
		} else {
			ssoProfileOptions = new WebSSOProfileOptions();
		}

		return ssoProfileOptions;

	}

	/**
	 * Sets object which determines default values to be used as basis for
	 * construction during getProfileOptions call.
	 *
	 * @param defaultOptions
	 *            default object to use for options construction
	 */
	public void setDefaultProfileOptions(WebSSOProfileOptions defaultOptions) {
		if (defaultOptions != null) {
			this.defaultOptions = defaultOptions.clone();
		} else {
			this.defaultOptions = null;
		}
	}

	/**
	 * Determines whether IDP Discovery should be initialized. By default no
	 * user-selected IDP must be present in the context, IDP Discovery must be
	 * enabled and the request mustn't be a response from IDP Discovery in order
	 * for the method to return true.
	 *
	 * @param context
	 *            context
	 * @return true if IDP Discovery should get initialized
	 */
	protected boolean isDiscovery(SAMLMessageContext context) {
		return !context.isPeerUserSelected() && context.getLocalExtendedMetadata().isIdpDiscoveryEnabled()
				&& !isDiscoResponse(context);
	}

	/**
	 * Determines whether ECP profile should get initialized. By default ECP is
	 * used when request declares supports for ECP and ECP is allowed for the
	 * current service provider. In case ECP is enabled but webSSOprofileECP
	 * wasn't set a warning is logged and ECP is not used.
	 *
	 * @param context
	 *            context
	 * @return true if ECP profile should get initialized
	 */
	protected boolean isECP(SAMLMessageContext context) {
		HttpServletRequest request = ((HttpServletRequestAdapter) context.getInboundMessageTransport())
				.getWrappedRequest();
		boolean ecp = context.getLocalExtendedMetadata().isEcpEnabled() && SAMLUtil.isECPRequest(request);
		if (ecp) {
			if (webSSOprofileECP == null) {
				logger.warn(
						"ECP profile was specified to be used, but profile is not configured in the EntryPoint, ECP will be skipped");
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	/**
	 * True value indicates that request is a response from the discovery
	 * profile. We use the value to prevent repeated invocation of the discovery
	 * service upon failure.
	 *
	 * @param context
	 *            context with request and response included
	 * @return true if this HttpRequest is a response from IDP discovery
	 *         profile.
	 */
	private boolean isDiscoResponse(SAMLMessageContext context) {
		HTTPInTransport request = (HTTPInTransport) context.getInboundMessageTransport();
		String disco = request.getParameterValue(DISCOVERY_RESPONSE_PARAMETER);
		return (disco != null && disco.toLowerCase().trim().equals("true"));
	}

	/**
	 * Profile for consumption of processed messages, cannot be null, must be
	 * set.
	 *
	 * @param webSSOprofile
	 *            profile
	 */
	@Autowired
	@Qualifier("webSSOprofile")
	public void setWebSSOprofile(WebSSOProfile webSSOprofile) {
		Assert.notNull(webSSOprofile, "WebSSOPRofile can't be null");
		this.webSSOprofile = webSSOprofile;
	}

	@Autowired(required = false)
	@Qualifier("ecpprofile")
	public void setWebSSOprofileECP(WebSSOProfile webSSOprofileECP) {
		this.webSSOprofileECP = webSSOprofileECP;
	}

	@Autowired(required = false)
	@Qualifier("hokWebSSOProfile")
	public void setWebSSOprofileHoK(WebSSOProfile webSSOprofileHoK) {
		this.webSSOprofileHoK = webSSOprofileHoK;
	}

	/**
	 * Logger for SAML events, cannot be null, must be set.
	 *
	 * @param samlLogger
	 *            logger
	 */
	@Autowired
	public void setSamlLogger(SAMLLogger samlLogger) {
		Assert.notNull(samlLogger, "SAML Logger can't be null");
		this.samlLogger = samlLogger;
	}

	/**
	 * Dependency for loading of discovery URL
	 * 
	 * @param samlDiscovery
	 *            saml discovery endpoint
	 */
	@Autowired(required = false)
	public void setSamlDiscovery(SAMLDiscovery samlDiscovery) {
		this.samlDiscovery = samlDiscovery;
	}

	/**
	 * Sets entity responsible for populating local entity context data.
	 *
	 * @param contextProvider
	 *            provider implementation
	 */
	@Autowired
	public void setContextProvider(SAMLContextProvider contextProvider) {
		Assert.notNull(contextProvider, "Context provider can't be null");
		this.contextProvider = contextProvider;
	}

	/**
	 * Metadata manager, cannot be null, must be set.
	 *
	 * @param metadata
	 *            manager
	 */
	@Autowired
	public void setMetadata(MetadataManager metadata) {
		Assert.notNull(metadata, "MetadataManager can't be null");
		this.metadata = metadata;
	}

	/**
	 * @return filter URL
	 */
	public String getFilterProcessesUrl() {
		return filterProcessesUrl;
	}

	/**
	 * Custom filter URL which overrides the default. Filter url determines URL
	 * where filter starts processing.
	 *
	 * @param filterProcessesUrl
	 *            filter URL
	 */
	public void setFilterProcessesUrl(String filterProcessesUrl) {
		this.filterProcessesUrl = filterProcessesUrl;
	}

	/**
	 * @return the idpUrl
	 */
	public String getIdpUrl() {
		return idpUrl;
	}

	/**
	 * @param idpUrl the idpUrl to set
	 */
	public void setIdpUrl(String idpUrl) {
		this.idpUrl = idpUrl;
	}

	/**
	 * Verifies that required entities were autowired or set.
	 */
	@Override
	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet();
		Assert.notNull(webSSOprofile, "WebSSO profile must be set");
		Assert.notNull(metadata, "Metadata must be set");
		Assert.notNull(samlLogger, "Logger must be set");
		Assert.notNull(contextProvider, "Context provider must be set");
	}

}