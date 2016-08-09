/**
 * 
 */
package com.verint.services.km.model;

import java.io.Serializable;
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
@XmlRootElement(name = "CrossTagResponse")
@XmlAccessorType(XmlAccessType.NONE)
public class CrossTagResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlElement
	private Set<CrossTag> crossTags = new HashSet<CrossTag>();

	/**
	 * Constructor 
	 */
	public CrossTagResponse() {
		super();
	}

	/**
	 * @return the crossTags
	 */
	public Set<CrossTag> getCrossTags() {
		return crossTags;
	}

	/**
	 * @param crossTags the crossTags to set
	 */
	public void setCrossTags(Set<CrossTag> crossTags) {
		this.crossTags = crossTags;
	}

	/**
	 * 
	 * @param crossTag
	 */
	public void addCrossTag(CrossTag crossTag) {
		this.crossTags.add(crossTag);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CrossTagResponse [crossTags=" + crossTags + "]";
	}
}