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
@XmlRootElement(name = "CrossTag")
@XmlAccessorType(XmlAccessType.NONE)
public class CrossTag implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@XmlElement
	private BigInteger id = new BigInteger("0");
	
	@XmlElement
	private String sourceTag = "";
 
	@XmlElement
	private String targetTagSet = "";

	@XmlElement
	private Boolean preselected = new Boolean(false);

	@XmlElement
	private Boolean selectChildren = new Boolean(true);

	@XmlElement
	private Set<Tag> tags = new HashSet<Tag>();
	
	/**
	 * Constructor 
	 */
	public CrossTag() {
		super();
	}

	/**
	 * @return the id
	 */
	public BigInteger getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(BigInteger id) {
		this.id = id;
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
	 * @return the targetTag
	 */
	public String getTargetTagSet() {
		return targetTagSet;
	}

	/**
	 * @param targetTag the targetTag to set
	 */
	public void setTargetTagSet(String targetTagSet) {
		this.targetTagSet = targetTagSet;
	}

	/**
	 * @return the preselected
	 */
	public Boolean getPreselected() {
		return preselected;
	}

	/**
	 * @param preselected the preselected to set
	 */
	public void setPreselected(Boolean preselected) {
		this.preselected = preselected;
	}

	/**
	 * @return the selectChildren
	 */
	public Boolean getSelectChildren() {
		return selectChildren;
	}

	/**
	 * @param selectChildren the selectChildren to set
	 */
	public void setSelectChildren(Boolean selectChildren) {
		this.selectChildren = selectChildren;
	}

	/**
	 * @return the tags
	 */
	public Set<Tag> getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CrossTag [id=" + id + ", sourceTag=" + sourceTag + ", targetTagSet=" + targetTagSet + ", preselected="
				+ preselected + ", selectChildren=" + selectChildren + ", tags=" + tags + "]";
	}
}