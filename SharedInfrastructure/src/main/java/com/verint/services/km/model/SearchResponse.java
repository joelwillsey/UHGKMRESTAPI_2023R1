/**
 * 
 */
package com.verint.services.km.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author jmiller
 *
 */
@XmlRootElement(name = "SearchResponse")
@XmlAccessorType(XmlAccessType.NONE)
public class SearchResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlElement
	private BigInteger numberOfResults;

	@XmlElement
	private BigInteger page;
	
	@XmlElement
	private BigInteger size;
	
	@XmlElement
	private Integer totalPages;

    @XmlElement(nillable=true)
	private Set<KnowledgeGroupUnit> knowledgeGroupUnits = new HashSet<KnowledgeGroupUnit>();

    @XmlElement(nillable=true)
	private Set<SuggestedQuery> suggestedQueries = new HashSet<SuggestedQuery>();
    
	/**
	 * 
	 */
	public SearchResponse() {
		super();
	}

	/**
	 * @return the numberOfResults
	 */
	public BigInteger getNumberOfResults() {
		return numberOfResults;
	}

	/**
	 * @param numberOfResults the numberOfResults to set
	 */
	public void setNumberOfResults(BigInteger numberOfResults) {
		if (numberOfResults != null)
			this.numberOfResults = numberOfResults;
	}

	/**
	 * @return the knowledgeGroupUnits
	 */
	public Set<KnowledgeGroupUnit> getKnowledgeGroupUnits() {
		return knowledgeGroupUnits;
	}

	/**
	 * @param knowledgeGroupUnits the knowledgeGroupUnits to set
	 */
	public void setKnowledgeGroupUnits(Set<KnowledgeGroupUnit> knowledgeGroupUnits) {
		if (knowledgeGroupUnits != null)
			this.knowledgeGroupUnits = knowledgeGroupUnits;
	}

	/**
	 * @return the page
	 */
	public BigInteger getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(BigInteger page) {
		if (page != null)
			this.page = page;
	}

	/**
	 * @return the size
	 */
	public BigInteger getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(BigInteger size) {
		if (size != null)
			this.size = size;
	}

	/**
	 * @return the totalPages
	 */
	public Integer getTotalPages() {
		return totalPages;
	}

	/**
	 * @param totalPages the totalPages to set
	 */
	public void setTotalPages(Integer totalPages) {
		if (totalPages != null)
			this.totalPages = totalPages;
	}

	/**
	 * @return the suggestedQueries
	 */
	public Set<SuggestedQuery> getSuggestedQueries() {
		return suggestedQueries;
	}

	/**
	 * @param suggestedQueries the suggestedQueries to set
	 */
	public void setSuggestedQueries(Set<SuggestedQuery> suggestedQueries) {
		this.suggestedQueries = suggestedQueries;
	}

	/**
	 * @param suggestedQueries the suggestedQueries to set
	 */
	public void addSuggestedQuery(SuggestedQuery suggestedQuery) {
		this.suggestedQueries.add(suggestedQuery);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SearchResponse [numberOfResults=" + numberOfResults + ", page=" + page + ", size=" + size
				+ ", totalPages=" + totalPages + ", knowledgeGroupUnits=" + knowledgeGroupUnits + ", suggestedQueries="
				+ suggestedQueries + "]";
	}
}