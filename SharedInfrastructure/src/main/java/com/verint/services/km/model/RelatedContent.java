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
@XmlRootElement(name = "RelatedContent")
@XmlAccessorType(XmlAccessType.NONE)
public class RelatedContent implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@XmlElement(nillable=true)
	private Set<ExternalContent> externalContents = new HashSet<ExternalContent>();

	@XmlElement(nillable=true)
	private Set<ContentEntry> contentEntries = new HashSet<ContentEntry>();

	/**
	 * 
	 */
	public RelatedContent() {
		super();
	}

	/**
	 * @return the externalContents
	 */
	public Set<ExternalContent> getExternalContents() {
		return externalContents;
	}

	/**
	 * @param externalContents the externalContents to set
	 */
	public void setExternalContents(Set<ExternalContent> externalContents) {
		this.externalContents = externalContents;
	}

	/**
	 * @param externalContent the externalContent to add
	 */
	public void addExternalContent(ExternalContent externalContent) {
		externalContents.add(externalContent);
	}

	/**
	 * @return the contentEntries
	 */
	public Set<ContentEntry> getContentEntries() {
		return contentEntries;
	}

	/**
	 * @param contentEntries the contentEntries to set
	 */
	public void setContentEntries(Set<ContentEntry> contentEntries) {
		this.contentEntries = contentEntries;
	}

	/**
	 * @param contentEntries the contentEntries to set
	 */
	public void addContentEntries(ContentEntry contentEntry) {
		contentEntries.add(contentEntry);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RelatedContent [externalContents=" + externalContents + ", contentEntries=" + contentEntries + "]";
	}
}