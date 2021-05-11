package com.verint.services.km.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "AuthTokenResponse")
@XmlAccessorType(XmlAccessType.NONE)
public class AuthTokenResponse implements Serializable{
private static final long serialVersionUID = 1L;
	
	@XmlElement(nillable=true)
	private String access_token;
	
	@XmlElement(nillable=true)
	private String id_token;
	
	@XmlElement(nillable=true)
	private String token_type;
	
	@XmlElement(nillable=true)
	private int expires_in;
	

	/**
	 * Constructor
	 */
	public AuthTokenResponse() {
		super();
	}
	
	/**
	 * @return the access_token
	 */
	public String getAccess_token() {
		return access_token;
	}

	/**
	 * @param access_token the access_token to set
	 */
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	
	/**
	 * @return the id_token
	 */
	public String getId_token() {
		return id_token;
	}

	/**
	 * @param id_token the id_token to set
	 */
	public void setId_token(String id_token) {
		this.id_token = id_token;
	}
	
	/**
	 * @return the token_type
	 */
	public String getToken_type() {
		return token_type;
	}

	/**
	 * @param token_type the token_type to set
	 */
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	
	/**
	 * @return the token_type
	 */
	public int getExpires_in() {
		return expires_in;
	}

	/**
	 * @param expires_in the expires_in to set
	 */
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AuthTokenResponse [access_token=" + access_token + ", id_token=" + id_token + ", token_type=" + token_type  + ", expires_in=" + expires_in + "]";
	}
}
