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
@XmlRootElement(name = "KnowledgeUnit")
@XmlAccessorType(XmlAccessType.NONE)
public class KnowledgeUnit implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlElement(nillable = true)
	private String contentOwner = "";

	@XmlElement(nillable = true)
	private String lastPublishedDate = "";

	@XmlElement(nillable = true)
	private String synopsis = "";

	@XmlElement(nillable = true)
	private String workflowState = "";

	@XmlElement(nillable = true)
	private Double relevanceScore;

	@XmlElement(nillable = true)
	private String lastModifiedDate = "";

	@XmlElement(nillable = true)
	private String associatedContentURL = "";

	@XmlElement(nillable = true)
	private String contentUnitID = "";

	@XmlElement(nillable = true)
	private String title = "";

	@XmlElement(nillable = true)
	private Set<Tag> contentCategoryTags = new HashSet<Tag>();

	@XmlElement(nillable = true)
	private String pageIdentifier = "";
	
	@XmlElement(nillable = true)
    private String publishedID = "";

	@XmlElement(nillable = true)
	private Set<Tag> tags = new HashSet<Tag>();

	@XmlElement(nillable = true)
	private String contentVersion = "";

	/**
	 * Constructor
	 */
	public KnowledgeUnit() {
		super();
	}

	/**
	 * @return the contentOwner
	 */
	public String getContentOwner() {
		return contentOwner;
	}

	/**
	 * @param contentOwner
	 *            the contentOwner to set
	 */
	public void setContentOwner(String contentOwner) {
		if (contentOwner != null)
			this.contentOwner = contentOwner;
	}

	/**
	 * @return the lastPublishedDate
	 */
	public String getLastPublishedDate() {
		return lastPublishedDate;
	}

	/**
	 * @param lastPublishedDate
	 *            the lastPublishedDate to set
	 */
	public void setLastPublishedDate(String lastPublishedDate) {
		if (lastPublishedDate != null)
			this.lastPublishedDate = lastPublishedDate;
	}

	/**
	 * @return the synopsis
	 */
	public String getSynopsis() {
		return synopsis;
	}

	/**
	 * @param synopsis
	 *            the synopsis to set
	 */
	public void setSynopsis(String synopsis) {
		if (synopsis != null)
			this.synopsis = synopsis;
	}

	/**
	 * @return the workflowState
	 */
	public String getWorkflowState() {
		return workflowState;
	}

	/**
	 * @param workflowState
	 *            the workflowState to set
	 */
	public void setWorkflowState(String workflowState) {
		if (workflowState != null)
			this.workflowState = workflowState;
	}

	/**
	 * @return the relevanceScore
	 */
	public Double getRelevanceScore() {
		return relevanceScore;
	}

	/**
	 * @param relevanceScore
	 *            the relevanceScore to set
	 */
	public void setRelevanceScore(Double relevanceScore) {
		if (relevanceScore != null)
			this.relevanceScore = relevanceScore;
	}

	/**
	 * @return the lastModifiedDate
	 */
	public String getLastModifiedDate() {
		return lastModifiedDate;
	}

	/**
	 * @param lastModifiedDate
	 *            the lastModifiedDate to set
	 */
	public void setLastModifiedDate(String lastModifiedDate) {
		if (lastModifiedDate != null)
			this.lastModifiedDate = lastModifiedDate;
	}

	/**
	 * @return the associatedContentURL
	 */
	public String getAssociatedContentURL() {
		return associatedContentURL;
	}

	/**
	 * @param associatedContentURL
	 *            the associatedContentURL to set
	 */
	public void setAssociatedContentURL(String associatedContentURL) {
		if (associatedContentURL != null)
			this.associatedContentURL = associatedContentURL;
	}

	/**
	 * @return the contentUnitID
	 */
	public String getContentUnitID() {
		return contentUnitID;
	}

	/**
	 * @param contentUnitID
	 *            the contentUnitID to set
	 */
	public void setContentUnitID(String contentUnitID) {
		if (contentUnitID != null)
			this.contentUnitID = contentUnitID;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		if (title != null)
			this.title = title;
	}

	/**
	 * @return the contentCategoryTags
	 */
	public Set<Tag> getContentCategoryTags() {
		return contentCategoryTags;
	}

	/**
	 * @param contentCategoryTags
	 *            the contentCategoryTags to set
	 */
	public void setContentCategoryTags(Set<Tag> contentCategoryTags) {
		if (contentCategoryTags != null)
			this.contentCategoryTags = contentCategoryTags;
	}

	/**
	 * @return the pageIdentifier
	 */
	public String getPageIdentifier() {
		return pageIdentifier;
	}

	/**
	 * @param pageIdentifier
	 *            the pageIdentifier to set
	 */
	public void setPageIdentifier(String pageIdentifier) {
		if (pageIdentifier != null)
			this.pageIdentifier = pageIdentifier;
	}
	
	/**
	 * @return the publishedID
	 */
	public String getPublishedID() {
		return publishedID;
	}

	/**
	 * @param publishedID
	 *            the publishedID to set
	 */
	public void setPublishedID(String publishedID) {
		if (publishedID != null)
			this.publishedID = publishedID;
	}
	
	/**
	 * @return the tags
	 */
	public Set<Tag> getTags() {
		return tags;
	}

	/**
	 * @param tags
	 *            the tags to set
	 */
	public void setTags(Set<Tag> tags) {
		if (tags != null)
			this.tags = tags;
	}

	/**
	 * @return the contentVersion
	 */
	public String getContentVersion() {
		return contentVersion;
	}

	/**
	 * @param contentVersion
	 *            the contentVersion to set
	 */
	public void setContentVersion(String contentVersion) {
		if (contentVersion != null)
			this.contentVersion = contentVersion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\nKnowledgeUnit [\n   contentOwner=" + contentOwner + "\n   lastPublishedDate=" + lastPublishedDate
				+ "\n   synopsis=" + synopsis + "\n   workflowState=" + workflowState + "\n   relevanceScore="
				+ relevanceScore + "\n   lastModifiedDate=" + lastModifiedDate + "\n   associatedContentURL="
				+ associatedContentURL + "\n   contentUnitID=" + contentUnitID + "\n   title=" + title
				+ "\n   contentCategoryTags=" + contentCategoryTags + "\n   pageIdentifier=" + pageIdentifier
				+ "\n   publishedID=" + publishedID + "\n   tags=" + tags + "\n   contentVersion=" + contentVersion + "]";
	}
}