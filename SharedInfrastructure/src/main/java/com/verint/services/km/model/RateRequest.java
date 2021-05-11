/**
 * 
 */
package com.verint.services.km.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author jmiller
 *
 */
@XmlRootElement(name = "RateRequest")
@XmlAccessorType(XmlAccessType.NONE)
public class RateRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlElement
	private String contentId;

	@XmlElement(nillable=true)
	private String username = "";

	@XmlElement(nillable=true)
	private String password = "";

	@XmlElement
	private Double rating;
	
	@XmlElement(nillable=true)
	private String siteName = "";

	@XmlElement(nillable=true)
	private String contentType = "";

	@XmlElement(nillable=true)
	private String oidcToken = "";

	/**
	 * Constructor
	 */
	public RateRequest() {
		super();
	}

	/**
	 * @return the contentId
	 */
	public String getContentId() {
		return contentId;
	}

	/**
	 * @param contentId the contentId to set
	 */
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the rating
	 */
	public Double getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(Double rating) {
		this.rating = rating;
	}
	
	/**
	 * @return the siteName
	 */
	public String getSiteName() {
		return siteName;
	}

	/**
	 * @param siteName the siteName to set
	 */
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getOidcToken() {
		return oidcToken;
	}

	public void setOidcToken(String oidcToken) {
		this.oidcToken = oidcToken;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RateRequest [contentId=" + contentId +
				", username=" + username +
				", password=" + password +
				", siteName=" + siteName +
				", rating=" + rating +
				", contentType=" + contentType +
				", oidcToken=" + oidcToken +
				"]";
	}
}