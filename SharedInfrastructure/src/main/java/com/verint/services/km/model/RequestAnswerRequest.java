/**
 * 
 */
package com.verint.services.km.model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author jmiller
 *
 */
@XmlRootElement(name = "RequestAnswerRequest")
@XmlAccessorType(XmlAccessType.NONE)
public class RequestAnswerRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlElement
    private String keyword;

	@XmlElement
    private String expectation;
	
    @XmlElement(nillable=true)
    private String selectedFilter;

    @XmlElement(nillable=true)
    private Date searchDate;

    @XmlElement
    private String username;
    
    @XmlElement
    private String searchFeedbackURL;

    @XmlElement(nillable=true)
    private String password;

    @XmlElement
    private String locale = "en-US";
    
	/**
	 * Constructor
	 */
	public RequestAnswerRequest() {
		super();
	}

	/**
	 * @return the keyword
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * @param keyword the keyword to set
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * @return the expectation
	 */
	public String getExpectation() {
		return expectation;
	}

	/**
	 * @param expectation the expectation to set
	 */
	public void setExpectation(String expectation) {
		this.expectation = expectation;
	}

	/**
	 * @return the selectedFilter
	 */
	public String getSelectedFilter() {
		return selectedFilter;
	}

	/**
	 * @param selectedFilter the selectedFilter to set
	 */
	public void setSelectedFilter(String selectedFilter) {
		this.selectedFilter = selectedFilter;
	}

	/**
	 * @return the searchDate
	 */
	public Date getSearchDate() {
		return searchDate;
	}

	/**
	 * @param searchDate the searchDate to set
	 */
	public void setSearchDate(Date searchDate) {
		this.searchDate = searchDate;
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
	 * @return the searchFeedbackURL
	 */
	public String getSearchFeedbackURL() {
		return searchFeedbackURL;
	}

	/**
	 * @param searchFeedbackURL the searchFeedbackURL to set
	 */
	public void setSearchFeedbackURL(String searchFeedbackURL) {
		this.searchFeedbackURL = searchFeedbackURL;
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
	 * @return the locale
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RequestAnswerRequest [keyword=" + keyword + ", expectation=" + expectation + ", selectedFilter="
				+ selectedFilter + ", searchDate=" + searchDate + ", username=" + username + ", searchFeedbackURL=" + searchFeedbackURL + ", password=" + password
				+ ", locale=" + locale + "]";
	}
}