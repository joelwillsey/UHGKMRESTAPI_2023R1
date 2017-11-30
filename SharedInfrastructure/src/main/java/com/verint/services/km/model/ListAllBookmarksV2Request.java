package com.verint.services.km.model;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ListAllBookmarksV2Request")
@XmlAccessorType(XmlAccessType.NONE)
public class ListAllBookmarksV2Request {

	private static final long serialVersionUID = 1L;
	
	@XmlElement(nillable=true)
    private String locale = "";		
	
	@XmlElement(nillable=true)
	private String username = "";

	@XmlElement(nillable=true)
	private String password = "";
	
	@XmlElement(nillable=true)
    private String applicationID = "";
	
	@XmlElement(nillable=true)
    private String sortColumnName = "";
	
	@XmlElement(nillable=true)
    private String sortOrder = "";
	
	
	/**
	 * Constructor
	 */
	public ListAllBookmarksV2Request() {
		super();
	}
	
	/**
	 * @return the maxNumberOfNewOrChanged
	 */
	public String getLocale() {
		return locale;
	}

	/**
	 * @param locale the locale to set
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}
	
	/**
	 * @return the sortColumnName
	 */
	public String getSortColumnName() {
		return sortColumnName;
	}

	/**
	 * @param  sortColumnName the sortColumnName to set
	 */
	public void setSortColumnName(String sortColumnName) {
		this.sortColumnName = sortColumnName;
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
	 * @return the applicationID
	 */
	public String getApplicationID() {
		return applicationID;
	}

	/**
	 * @param applicationID the applicationID to set
	 */
	public void setApplicationID(String applicationID) {
		this.applicationID = applicationID;
	}
	
	/**
	 * @return the kbase_tags
	 */
	public String getSortOrder() {
		return sortOrder;
	}

	/**
	 * @param sortOrder the sortOrder to set
	 */
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ListAllBookmarksV2Request [locale=" + locale + ", password=" + password + ", username=" + username + ", applicationID=" + applicationID + ", sortOrder=" + sortOrder +  "sortColumnName=" + sortColumnName + "]";
	}
}
