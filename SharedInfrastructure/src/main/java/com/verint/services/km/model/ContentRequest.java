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
@XmlRootElement(name = "ContentRequest")
@XmlAccessorType(XmlAccessType.NONE)
public class ContentRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlElement
	private String contentId;

	@XmlElement(nillable=true)
	private String username = "";

	@XmlElement(nillable=true)
	private String password = "";
	
	@XmlElement(nillable=true)
	private String workflowState = "PUBLISHED";

	@XmlElement(nillable=true)
	private String version = "";

	@XmlElement(nillable=true)
	private String contentType = "";

	@XmlElement(nillable=true)
	private String oidcToken = "";

	/**
	 * Constructor
	 */
	public ContentRequest() {
		super();
	}

	/**
	 * @return the contentId
	 */
	public String getContentId() {
		return contentId;
	}
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
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
	 * @return the workflowState
	 */
	public String getWorkflowState() {
		return workflowState;
	}

	/**
	 * @param workflowState the password to set
	 */
	public void setWorkflowState(String workflowState) {
		this.workflowState = workflowState;
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
		return "ContentRequest [contentId=" + contentId +
				", username=" + username + ", password=" + password +
				", workflowState=" + workflowState + ", contentType=" + contentType +
				", oidcToken=" + oidcToken + "]";
	}
}