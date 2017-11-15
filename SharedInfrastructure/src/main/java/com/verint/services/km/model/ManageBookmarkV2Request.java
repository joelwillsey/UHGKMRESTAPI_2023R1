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
@XmlRootElement(name = "ManageBookmarkRequest")
@XmlAccessorType(XmlAccessType.NONE)
public class ManageBookmarkV2Request implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlElement
	private String contentId;
	
	@XmlElement(nillable=true)
	private String userAction = "";

	@XmlElement(nillable=true)
	private String username = "";

	@XmlElement(nillable=true)
	private String password = "";

	@XmlElement(nillable=true)
	private String folderId = "";
	/**
	 * Constructor
	 */
	public ManageBookmarkV2Request() {
		super();
	}

	/**
	 * @return the contentId
	 */
	public String getContentId() {
		return contentId;
	}
	//return the folder ID
	public String getFolderId() {
		return folderId;
		
	}
	
	public void setFolderId(String folderId){
		this.folderId = folderId;
	}

	/**
	 * @param contentId the contentId to set
	 */
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getUserAction() {
		return userAction;
	}

	/**
	 * 
	 * @param userAction
	 */
	public void setUserAction(String userAction) {
		this.userAction = userAction;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ManageBookmarkV2Request [contentId=" + contentId + ", userAction=" + userAction + ", username=" + username + ", password=" + password +", folderId=" + folderId
				+ "]";
	}
}