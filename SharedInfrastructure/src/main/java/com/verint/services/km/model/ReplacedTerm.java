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
@XmlRootElement(name = "KnowledgeUnit")
@XmlAccessorType(XmlAccessType.NONE)
public class ReplacedTerm implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlElement(nillable = true)
	private String replacement = "";

	@XmlElement(nillable = true)
	private String term = "";

	/**
	 * Constructor
	 */
	public ReplacedTerm() {
		super();
	}

	/**
	 * @return the replacement
	 */
	public String getReplacement() {
		return replacement;
	}

	/**
	 * @param replacement the replacement to set
	 */
	public void setReplacement(String replacement) {
		this.replacement = replacement;
	}

	/**
	 * @return the term
	 */
	public String getTerm() {
		return term;
	}

	/**
	 * @param term the term to set
	 */
	public void setTerm(String term) {
		this.term = term;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ReplacedTerm [replacement=" + replacement + ", term=" + term + "]";
	}
}