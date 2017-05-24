package com.verint.services.km.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Hbendale
 *
 */
@XmlRootElement(name = "FeaturedRequest")
@XmlAccessorType(XmlAccessType.NONE)
public class FeaturedRequest  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@XmlElement(nillable=true)
    private String locale = "";
	
	@XmlElement(nillable=true)
	private BigInteger maxNumberOfFeatured = new BigInteger("0");
	
	@XmlElement(nillable=true)
	private String username = "";

	@XmlElement(nillable=true)
	private String password = "";
	
	@XmlElement(nillable=true)
    private String applicationID = "";
	
	@XmlElement(nillable=true)
    private String kbase_tags = "";
	
	@XmlElement(nillable=true)
    private BigInteger maxNumberOfUnitsPerGroup = new BigInteger("0");
	
	@XmlElement(nillable=true)
    private BigInteger paginationStartIndex = new BigInteger("0");

	@XmlElement(nillable=true)
    private BigInteger maxNumberOfGroupResults = new BigInteger("0");
	/**
	 * Constructor
	 */
	public FeaturedRequest() {
		super();
	}
	
	/**
	 * @return the maxNumberOfFeatured
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
	 * @return the maxNumberOfFeatured
	 */
	public BigInteger getMaxNumberOfFeatured() {
		return maxNumberOfFeatured;
	}

	/**
	 * @param maxNumberOfFeatured the maxNumberOfFeatured to set
	 */
	public void setMaxNumberOfFeatured(BigInteger maxNumberOfFeatured) {
		this.maxNumberOfFeatured = maxNumberOfFeatured;
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
	
	public BigInteger getMaxNumberOfUnitsPerGroup() {
		return maxNumberOfUnitsPerGroup;
	}

	public void setMaxNumberOfUnitsPerGroup(BigInteger maxNumberOfUnitsPerGroup) {
		this.maxNumberOfUnitsPerGroup = maxNumberOfUnitsPerGroup;
	}

	public BigInteger getPaginationStartIndex() {
		return paginationStartIndex;
	}

	public void setPaginationStartIndex(BigInteger paginationStartIndex) {
		this.paginationStartIndex = paginationStartIndex;
	}

	public BigInteger getMaxNumberOfGroupResults() {
		return maxNumberOfGroupResults;
	}

	public void setMaxNumberOfGroupResults(BigInteger maxNumberOfGroupResults) {
		this.maxNumberOfGroupResults = maxNumberOfGroupResults;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FeaturedRequest [locale=" + locale + ", maxNumberOfFeatured=" + maxNumberOfFeatured + ", password=" + password + ", username=" + username + ", applicationID=" + applicationID + ", kbase_tags=" + kbase_tags + "]";
	}
}