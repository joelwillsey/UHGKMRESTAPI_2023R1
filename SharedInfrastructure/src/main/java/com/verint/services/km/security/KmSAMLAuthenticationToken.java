package com.verint.services.km.security;

import java.util.Collection;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

/**
 * 
 * @author John.Miller
 *
 */
public class KmSAMLAuthenticationToken extends AbstractAuthenticationToken {
	private static final Logger LOGGER = LoggerFactory.getLogger(KmSAMLAuthenticationToken.class);
	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
	private final Object principal;
	private Date expiration;

	// ~ Constructors
	// ===================================================================================================
	/**
	 * This constructor can be safely used by any code that wishes to create a
	 * <code>UsernamePasswordAuthenticationToken</code>, as the
	 * {@link #isAuthenticated()} will return <code>false</code>.
	 *
	 */
	public KmSAMLAuthenticationToken(Object principal) {
		super(null);
		LOGGER.info("Entering KmSAMLAuthenticationToken()");
		this.principal = principal;
		setAuthenticated(false);
		LOGGER.info("Exiting KmSAMLAuthenticationToken()");
	}

	/**
	 * This constructor should only be used by
	 * <code>AuthenticationManager</code> or <code>AuthenticationProvider</code>
	 * implementations that are satisfied with producing a trusted (i.e.
	 * {@link #isAuthenticated()} = <code>true</code>) authentication token.
	 *
	 * @param principal
	 * @param credentials
	 * @param authorities
	 */
	public KmSAMLAuthenticationToken(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		LOGGER.info("Entering KmSAMLAuthenticationToken()");
		this.principal = principal;
		super.setAuthenticated(true); // must use super, as we override
		LOGGER.info("Exiting KmSAMLAuthenticationToken()");
	}

	// ~ Methods
	// ========================================================================================================

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.Authentication#getCredentials()
	 */
	@Override
	public Object getCredentials() {
		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.Authentication#getPrincipal()
	 */
	@Override
	public Object getPrincipal() {
		return this.principal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.authentication.AbstractAuthenticationToken#
	 * setAuthenticated(boolean)
	 */
	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		if (isAuthenticated) {
			throw new IllegalArgumentException(
					"Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
		}
		super.setAuthenticated(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.authentication.AbstractAuthenticationToken#
	 * eraseCredentials()
	 */
	@Override
	public void eraseCredentials() {
		super.eraseCredentials();
	}

	/**
	 * @return the expiration
	 */
	public Date getExpiration() {
		return expiration;
	}

	/**
	 * @param expiration
	 *            the expiration to set
	 */
	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}
}
