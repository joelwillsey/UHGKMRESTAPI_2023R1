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
@XmlRootElement(name = "TagSetResponse")
@XmlAccessorType(XmlAccessType.NONE)
public class TagSetResponse implements Serializable {
	private static final long serialVersionUID = 1L;

    @XmlElement
	private Set<TagSet> tagSets = new HashSet<TagSet>();

	/**
	 * 
	 */
	public TagSetResponse() {
		super();
	}

	/**
	 * @return the tagSets
	 */
	public Set<TagSet> getTagSets() {
		return tagSets;
	}

	/**
	 * @param tagSets the tagSets to set
	 */
	public void setTagSets(Set<TagSet> tagSets) {
		this.tagSets = tagSets;
	}

	/**
	 * @param tagSets the tagSets to set
	 */
	public void addTagSets(TagSet tagSet) {
		this.tagSets.add(tagSet);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TagSetResponse [tagSets=" + tagSets + "]";
	}
}