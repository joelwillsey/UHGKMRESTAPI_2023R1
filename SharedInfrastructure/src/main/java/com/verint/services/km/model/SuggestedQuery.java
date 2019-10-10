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
@XmlRootElement(name = "KnowledgeUnit")
@XmlAccessorType(XmlAccessType.NONE)
public class SuggestedQuery implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlElement(nillable = true)
	private BigInteger numberOfResults;

	@XmlElement(nillable = true)
	private String queryText = "";
	
	@XmlElement(nillable = true)
	private String type = "";
	
	@XmlElement(nillable = true)
	private Float searchPrecision;
	
	@XmlElement(nillable=true)
	private Set<ReplacedTerm> replacedTerms = new HashSet<ReplacedTerm>();
	
	@XmlElement(nillable=true)
	private Set<KnowledgeGroupUnit> knowledgeGroupUnits = new HashSet<KnowledgeGroupUnit>();
	
	/**
	 * Constructor
	 */
	public SuggestedQuery() {
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
		this.numberOfResults = numberOfResults;
	}

	/**
	 * @return the queryText
	 */
	public String getQueryText() {
		return queryText;
	}

	/**
	 * @param queryText the queryText to set
	 */
	public void setQueryText(String queryText) {
		this.queryText = queryText;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * @return the searchPrecision
	 */     
    public float getSearchPrecision() {
        return searchPrecision;
    }
	
	/**
	 * @param type the type to set
	 */
    public void setSearchPrecision(float searchPrecision) {
        this.searchPrecision = searchPrecision;
    }

	/**
	 * @return the replacedTerms
	 */
	public Set<ReplacedTerm> getReplacedTerms() {
		return replacedTerms;
	}

	/**
	 * @param replacedTerms the replacedTerms to set
	 */
	public void setReplacedTerms(Set<ReplacedTerm> replacedTerms) {
		this.replacedTerms = replacedTerms;
	}

	/**
	 * @param replacedTerms the replacedTerms to set
	 */
	public void addReplacedTerm(ReplacedTerm replacedTerm) {
		this.replacedTerms.add(replacedTerm);
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
		this.knowledgeGroupUnits = knowledgeGroupUnits;
	}

	/**
	 * @param knowledgeGroupUnits the knowledgeGroupUnits to set
	 */
	public void addKnowledgeGroupUnit(KnowledgeGroupUnit knowledgeGroupUnit) {
		this.knowledgeGroupUnits.add(knowledgeGroupUnit);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SuggestedQuery [numberOfResults=" + numberOfResults + ", queryText=" + queryText + ", type=" + type
				+ ", searchPrecision=" + searchPrecision + ", replacedTerms=" + replacedTerms + ", knowledgeGroupUnits=" + knowledgeGroupUnits + "]";
	}
}