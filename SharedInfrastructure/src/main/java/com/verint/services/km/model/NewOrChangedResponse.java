package com.verint.services.km.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "NewOrChangedResponse")
@XmlAccessorType(XmlAccessType.NONE)
public class NewOrChangedResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	 @XmlElement(nillable=true)
		private Set<KnowledgeGroupUnit> knowledgeGroupUnits = new HashSet<KnowledgeGroupUnit>();
	 
	 @XmlElement
		private BigInteger numberOfResults;

	 @XmlElement(nillable=true)
		private Set<SuggestedQuery> suggestedQueries = new HashSet<SuggestedQuery>();

	 @XmlElement
	 private BigInteger page;
	
 	 @XmlElement
	 private BigInteger size;
	
	 @XmlElement
	 private Integer totalPages;

	/**
	 * 
	 */
	public NewOrChangedResponse() {
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
	
	public BigInteger getPage() {
		return page;
	}

	public void setPage(BigInteger page) {
		this.page = page;
	}

	public BigInteger getSize() {
		return size;
	}

	public void setSize(BigInteger size) {
		this.size = size;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SearchResponse [knowledgeGroupUnits=" + knowledgeGroupUnits + ", suggestedQueries="
				+ suggestedQueries + "]";
	}
}
