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
@XmlRootElement(name = "CrossTagRequest")
@XmlAccessorType(XmlAccessType.NONE)
public class CrossTagRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@XmlElement
	private String sourceTag;
	
	@XmlElement
	private String targetTagSet;

	/**
	 * Constructor 
	 */
	public CrossTagRequest() {
		super();
	}

	/**
	 * @return the sourceTag
	 */
	public String getSourceTag() {
		return sourceTag;
	}

	/**
	 * @param sourceTag the sourceTag to set
	 */
	public void setSourceTag(String sourceTag) {
		this.sourceTag = sourceTag;
	}

	/**
	 * @return the targetTagSet
	 */
	public String getTargetTagSet() {
		return targetTagSet;
	}

	/**
	 * @param targetTagSet the targetTagSet to set
	 */
	public void setTargetTagSet(String targetTagSet) {
		this.targetTagSet = targetTagSet;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CrossTagRequest [sourceTag=" + sourceTag + ", targetTagSet=" + targetTagSet + "]";
	}
}