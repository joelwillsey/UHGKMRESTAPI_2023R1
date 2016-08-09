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
@XmlRootElement(name = "RequestAnswer")
@XmlAccessorType(XmlAccessType.NONE)
public class RequestAnswer implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlElement
    private String keyword;

	@XmlElement
    private String expectation;

    @XmlElement(nillable=true)
    private String selectedFilter;

    @XmlElement(nillable=true)
    private Date searchDate;

	/**
	 * Constructor
	 */
	public RequestAnswer() {
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RequestAnswer [keyword=" + keyword + ", expectation=" + expectation + ", selectedFilter="
				+ selectedFilter + ", searchDate=" + searchDate + "]";
	}
}