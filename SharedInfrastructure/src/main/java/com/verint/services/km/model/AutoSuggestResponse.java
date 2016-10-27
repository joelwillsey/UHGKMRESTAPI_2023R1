package com.verint.services.km.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * @author ERaygorodetskiy
 *
 */
public class AutoSuggestResponse implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@XmlElement(nillable=true)
	private int numFound = 0;
	
	@XmlElement(nillable=true)
	private int startOffset = 0;
	
	@XmlElement(nillable=true)
	private int endOffset = 0;
	
	@XmlElement(nillable=true)
	private Set<String> suggestion = new HashSet<String>();
	
	/**
	 * 
	 */
	public AutoSuggestResponse() {
		super();
	}
	
	/**
	 * @return
	 */
	public int getNumFound(){
		return numFound;
	}
	
	/**
	 * @param numFound the numFound to set
	 */
	public void setNumFound(int numFound) {
		this.numFound = numFound;
	}
	
	/**
	 * @return
	 */
	public int getStartOffset(){
		return startOffset;
	}
	
	/**
	 * @param startOffset the startOffset to set
	 */
	public void setStartOffset(int startOffset){
		this.startOffset = startOffset;
	}
	
	/**
	 * @return
	 */
	public int getEndOffset(){
		return endOffset;
	}
	
	/**
	 * @param endOffset the endOffset to set
	 */
	public void setEndOffset(int endOffset){
		this.endOffset = endOffset;
	}
	
	/**
	 * @return
	 */
	public Set<String> getSuggestion(){
		return suggestion;
	}
	
	/**
	 * @param suggestion the suggestion to set
	 */
	public void setSuggestion(Set<String> suggestion){
		this.suggestion = suggestion;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return "Autosuggest [numFound=" + numFound + ", startOffset=" + startOffset + ", endOffset=" + endOffset + ", suggestion=" + suggestion + "]";
	}
}
