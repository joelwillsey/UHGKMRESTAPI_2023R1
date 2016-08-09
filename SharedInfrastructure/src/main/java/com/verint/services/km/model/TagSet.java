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
@XmlRootElement(name = "TagSet")
@XmlAccessorType(XmlAccessType.NONE)
public class TagSet implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlElement(nillable=true)
	private String parentTagName = "";

	@XmlElement(nillable=true)
	private String systemTagName = "";

	@XmlElement(nillable=true)
	private String displayTagName = "";
	
	@XmlElement(nillable=true)
	private Set<Tag> tags = new HashSet<Tag>();
 	
	/**
	 * Constructor 
	 */
	public TagSet() {
		super();
	}

	/**
	 * @return the parentTagName
	 */
	public String getParentTagName() {
		return parentTagName;
	}

	/**
	 * @param parentTagName the parentTagName to set
	 */
	public void setParentTagName(String parentTagName) {
		this.parentTagName = parentTagName;
	}

	/**
	 * @return the systemTagName
	 */
	public String getSystemTagName() {
		return systemTagName;
	}

	/**
	 * @param systemTagName the systemTagName to set
	 */
	public void setSystemTagName(String systemTagName) {
		this.systemTagName = systemTagName;
	}

	/**
	 * @return the displayTagName
	 */
	public String getDisplayTagName() {
		return displayTagName;
	}

	/**
	 * @param displayTagName the displayTagName to set
	 */
	public void setDisplayTagName(String displayTagName) {
		this.displayTagName = displayTagName;
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

	/**
	 * @param tags the tags to set
	 */
	public void addTag(Tag tag) {
		this.tags.add(tag);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TagSet [parentTagName=" + parentTagName + ", systemTagName=" + systemTagName + ", displayTagName="
				+ displayTagName + ", tags=" + tags + "]";
	}
}