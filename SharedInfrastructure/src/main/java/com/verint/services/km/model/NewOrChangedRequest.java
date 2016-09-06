package com.verint.services.km.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author ERaygorodetskiy
 *
 */
@XmlRootElement(name = "NewOrChangedRequest")
@XmlAccessorType(XmlAccessType.NONE)
public class NewOrChangedRequest  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@XmlElement(nillable=true)
    private String locale = "";
	
	@XmlElement(nillable=true)
	private BigInteger maxNumberOfNewOrChanged = new BigInteger("0");
	
	@XmlElement(nillable=true)
	private String username = "";

	@XmlElement(nillable=true)
	private String password = "";
	
	@XmlElement(nillable=true)
    private String applicationID = "";
	
	@XmlElement(nillable=true)
    private String kbase_tags = "";
	
	/**
	 * Constructor
	 */
	public NewOrChangedRequest() {
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
	 * @return the maxNumberOfNewOrChanged
	 */
	public BigInteger getMaxNumberOfNewOrChanged() {
		return maxNumberOfNewOrChanged;
	}

	/**
	 * @param maxNumberOfNewOrChanged the maxNumberOfNewOrChanged to set
	 */
	public void setMaxNumberOfNewOrChanged(BigInteger maxNumberOfNewOrChanged) {
		this.maxNumberOfNewOrChanged = maxNumberOfNewOrChanged;
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
	public String getKBaseTags() {
		return kbase_tags;
	}

	/**
	 * @param kbase_tags the kbase_tags to set
	 */
	public void setKBaseTags(String kbase_tags) {
		this.kbase_tags = kbase_tags;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NewOrChangedRequest [locale=" + locale + ", maxNumberOfNewOrChanged=" + maxNumberOfNewOrChanged + ", password=" + password + ", username=" + username + ", applicationID=" + applicationID + ", kbase_tags=" + kbase_tags + "]";
	}
}