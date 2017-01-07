package com.verint.services.km.model;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TeamKBaseTagsResponse")
@XmlAccessorType(XmlAccessType.NONE)
public class TeamKBaseTagsResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement
	private LinkedHashSet<Tag> tags = new LinkedHashSet<Tag>();

	/**
	 * Constructor 
	 */
	public TeamKBaseTagsResponse() {
		super();
	}

	/**
	 * @return the tag
	 */
	public Set<Tag> getTags() {
		return tags;
	}

	/**
	 * @param tag the tag to set
	 */
	public void setTags(LinkedHashSet<Tag> tags) {
		this.tags = tags;
	}

	/**
	 * 
	 * @param tag
	 */
	public void addTags(Tag tag) {
		this.tags.add(tag);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TeamKBaseTagsResponse [tags=" + tags + "]";
	}

}
