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
@XmlRootElement(name = "TagResponse")
@XmlAccessorType(XmlAccessType.NONE)
public class TagResponse implements Serializable {
	private static final long serialVersionUID = 1L;

    @XmlElement
	private Set<Tag> tags = new HashSet<Tag>();

	/**
	 * 
	 */
	public TagResponse() {
		super();
	}

	/**
	 * @return the tagSets
	 */
	public Set<Tag> getTags() {
		return tags;
	}

	/**
	 * @param tagSets the tagSets to set
	 */
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	/**
	 * @param tagSets the tagSets to set
	 */
	public void addTag(Tag tag) {
		this.tags.add(tag);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TagResponse [tags=" + tags + "]";
	}
}