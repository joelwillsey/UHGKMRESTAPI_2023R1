/**
 * 
 */
package com.verint.services.km.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.kana.contactcentre.services.model.ContentV1Service_wsdl.ErrorMessage;

import com.verint.services.km.util.TagSetComp;

/**
 * @author jmiller
 *
 */
@XmlRootElement(name = "ContentResponse")
@XmlAccessorType(XmlAccessType.NONE)
public class ContentResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlElement
	private String id = "";
	
	@XmlElement(nillable=true)
	private Set<Translated> translatedTo = new HashSet<Translated>();
	
	@XmlElement(nillable=true)
    private String language = "";
    
	@XmlElement(nillable=true)
    private String lastModifiedDate = "";
    
	@XmlElement(nillable=true)
    private Double averageRating = 0.0;

	@XmlElement(nillable=true)
	private TreeSet<TagSet> tagSets = new TreeSet<TagSet>(new TagSetComp());

	@XmlElement(nillable=true)
	private String publicBody = "";
	
	@XmlElement(nillable=true)
	private String publicAnswer = "";

	@XmlElement(nillable=true)
	private Set<ContentEntry> publicSectionContent = new HashSet<ContentEntry>();

	@XmlElement(nillable=true)
	private Set<ContentEntry> publicSegmentContent = new HashSet<ContentEntry>();
	
	@XmlElement(nillable=true)
	private String privateBody = "";
	
	@XmlElement(nillable=true)
	private String privateAnswer = "";

	@XmlElement(nillable=true)
	private Set<ContentEntry> privateSectionContent = new HashSet<ContentEntry>();

	@XmlElement(nillable=true)
	private Set<ContentEntry> privateSegmentContent = new HashSet<ContentEntry>();

	@XmlElement(nillable=true)
	private RelatedContent relatedContent  = new RelatedContent();

	@XmlElement(nillable=true)
	private Set<CustomField> customFields  = new HashSet<CustomField>();

	@XmlElement(nillable=true)
    private String publishedId = "";
    
	@XmlElement(nillable=true)
    private String contentCategory = "";

	@XmlElement(nillable=true)
    private Boolean isFeatured = false;

	@XmlElement(nillable=true)
    private String version = "";
    
	@XmlElement(nillable=true)
    private String title = "";
    
	@XmlElement(nillable=true)
    private String locale = "";
    
	@XmlElement(nillable=true)
    private BigInteger numberOfRatings = new BigInteger("0");
    
	@XmlElement(nillable=true)
    private String contentType = "";
    
	@XmlElement(nillable=true)
    private BigInteger viewContent = new BigInteger("0");
    
	@XmlElement(nillable=true)
    private String description = "";

	@XmlElement(nillable=true)
	private Set<Attachment> attachments = new HashSet<Attachment>();
	
	@XmlElement(nillable=true)
	private Set<ScriptSrc> externalSrcFiles = new HashSet<ScriptSrc>();

	@XmlElement(nillable=true)
	private String publishedDate = "";
	
	@XmlElement(nillable=true)
	private String expiredDate = "";
	
	@XmlElement(nillable=true)
	private String lastModifiedBy = "";
	
	@XmlElement(nillable=true)
	private String owner = "";
	
	@XmlElement(nillable=true)
	private String weighting = "";
	
	@XmlElement(nillable=true)
	private String status = "";
	
	@XmlElement(nillable=true)
	private Boolean bookmarked = new Boolean(false);

	@XmlElement(nillable=true)
	private String rawBody = "";
	
	@XmlElement(nillable=true)
	private String viewUUID = "";
	
	@XmlElement(nillable=true)
	private com.kana.contactcentre.services.model.ContentV1Service_wsdl.ErrorMessage[] errorList;
 

	/**
	 * 
	 */
	public ContentResponse() {
		super();
	}

	   public void setErrorList(com.kana.contactcentre.services.model.ContentV1Service_wsdl.ErrorMessage[] errorList) {
	        this.errorList = errorList;
	    }
	   
	   public com.kana.contactcentre.services.model.ContentV1Service_wsdl.ErrorMessage[] getErrorList() {
	        return this.errorList;
	    }
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * @return the translatedTo
	 */
	public Set<Translated> getTranslatedTo() {
		return translatedTo;
	}


	/**
	 * @param translatedTo the translatedTo to set
	 */
	public void setTranslatedTo(Set<Translated> translatedTo) {
		this.translatedTo = translatedTo;
	}

	/**
	 * @param translatedTo the translatedTo to add
	 */
	public void addTranslatedTo(Translated translatedTo) {
		this.translatedTo.add(translatedTo);
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}


	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}


	/**
	 * @return the lastModifiedDate
	 */
	public String getLastModifiedDate() {
		return lastModifiedDate;
	}


	/**
	 * @param lastModifiedDate the lastModifiedDate to set
	 */
	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}


	/**
	 * @return the averageRating
	 */
	public Double getAverageRating() {
		return averageRating;
	}


	/**
	 * @param averageRating the averageRating to set
	 */
	public void setAverageRating(Double averageRating) {
		this.averageRating = averageRating;
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
	public void setTagSets(TreeSet<TagSet> tagSets) {
		this.tagSets = tagSets;
	}

	/**
	 * @param tagSets the tagSets to set
	 */
	public void addTagSet(TagSet tagSet) {
		this.tagSets.add(tagSet);
	}

	/**
	 * @return the customFields
	 */
	public Set<CustomField> getCustomFields() {
		return customFields;
	}

	/**
	 * @param customFields the customFields to set
	 */
	public void setCustomFields(Set<CustomField> customFields) {
		this.customFields = customFields;
	}

	/**
	 * @param customFields the customFields to set
	 */
	public void addCustomField(CustomField customField) {
		this.customFields.add(customField);
	}

	/**
	 * @return the publicSectionContent
	 */
	public Set<ContentEntry> getPublicSectionContent() {
		return publicSectionContent;
	}


	/**
	 * @param publicSectionContent the publicSectionContent to set
	 */
	public void setPublicSectionContent(Set<ContentEntry> publicSectionContent) {
		this.publicSectionContent = publicSectionContent;
	}

	/**
	 * @param publicSectionContent the publicSectionContent to set
	 */
	public void addPublicSectionContent(ContentEntry contentEntry) {
		this.publicSectionContent.add(contentEntry);
	}

	/**
	 * @return the publicSectionContent
	 */
	public Set<ContentEntry> getPublicSegmentContent() {
		return publicSectionContent;
	}

	/**
	 * @param publicSectionContent the publicSectionContent to set
	 */
	public void setPublicSegmentContent(Set<ContentEntry> publicSegmentContent) {
		this.publicSegmentContent = publicSegmentContent;
	}

	/**
	 * @param publicSectionContent the publicSectionContent to set
	 */
	public void addPublicSegmentContent(ContentEntry contentEntry) {
		this.publicSegmentContent.add(contentEntry);
	}
	
	/**
	 * @return the relatedContent
	 */
	public RelatedContent getRelatedContent() {
		return relatedContent;
	}


	/**
	 * @param relatedContent the relatedContent to set
	 */
	public void setRelatedContent(RelatedContent relatedContent) {
		this.relatedContent = relatedContent;
	}

	/**
	 * @return the publishedId
	 */
	public String getPublishedId() {
		return publishedId;
	}


	/**
	 * @param publishedId the publishedId to set
	 */
	public void setPublishedId(String publishedId) {
		this.publishedId = publishedId;
	}


	/**
	 * @return the contentCategory
	 */
	public String getContentCategory() {
		return contentCategory;
	}


	/**
	 * @param contentCategory the contentCategory to set
	 */
	public void setContentCategory(String contentCategory) {
		this.contentCategory = contentCategory;
	}


	/**
	 * @return the isFeatured
	 */
	public Boolean getIsFeatured() {
		return isFeatured;
	}


	/**
	 * @param isFeatured the isFeatured to set
	 */
	public void setIsFeatured(Boolean isFeatured) {
		this.isFeatured = isFeatured;
	}


	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}


	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}


	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}


	/**
	 * @return the locale
	 */
	public String getLocale() {
		return locale;
	}


	/**
	 * @param locale the locale to set
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}


	/**
	 * @return the numberOfRatings
	 */
	public BigInteger getNumberOfRatings() {
		return numberOfRatings;
	}


	/**
	 * @param numberOfRatings the numberOfRatings to set
	 */
	public void setNumberOfRatings(BigInteger numberOfRatings) {
		this.numberOfRatings = numberOfRatings;
	}


	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}


	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		if (contentType != null)
			this.contentType = contentType;
	}


	/**
	 * @return the viewContent
	 */
	public BigInteger getViewContent() {
		return viewContent;
	}


	/**
	 * @param viewContent the viewContent to set
	 */
	public void setViewContent(BigInteger viewContent) {
		if (viewContent != null)
			this.viewContent = viewContent;
	}


	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		if (description != null)
			this.description = description;
	}


	/**
	 * @return the publicBody
	 */
	public String getPublicBody() {
		return publicBody;
	}


	/**
	 * @param publicBody the publicBody to set
	 */
	public void setPublicBody(String publicBody) {
		this.publicBody = publicBody;
	}


	/**
	 * @return the publishedDate
	 */
	public String getPublishedDate() {
		return publishedDate;
	}


	/**
	 * @param publishedDate the publishedDate to set
	 */
	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}


	/**
	 * @return the expiredDate
	 */
	public String getExpiredDate() {
		return expiredDate;
	}


	/**
	 * @param expiredDate the expiredDate to set
	 */
	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
	}


	/**
	 * @return the lastModifiedBy
	 */
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}


	/**
	 * @param lastModifiedBy the lastModifiedBy to set
	 */
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}


	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}


	/**
	 * @param owner the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}


	/**
	 * @return the weighting
	 */
	public String getWeighting() {
		return weighting;
	}


	/**
	 * @param weighting the weighting to set
	 */
	public void setWeighting(String weighting) {
		this.weighting = weighting;
	}


	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the attachments
	 */
	public Set<Attachment> getAttachments() {
		return attachments;
	}

	/**
	 * @param attachments the attachments to set
	 */
	public void setAttachments(Set<Attachment> attachments) {
		this.attachments = attachments;
	}

	/**
	 * @param attachment the attachment to set
	 */
	public void addAttachment(Attachment attachment) {
		this.attachments.add(attachment);
	}

	/**
	 * @return the scriptSrc
	 */
	public Set<ScriptSrc> getExternalSrcFiles() {
		return externalSrcFiles;
	}
	
	/**
	 * @param scriptSrc the scriptSrc to set
	 */
	public void setExternalSrcFiles(Set<ScriptSrc> externalSrcFiles) {
		this.externalSrcFiles =externalSrcFiles;
	}

	/**
	 * @param scriptSrc the scriptSrc to add
	 */
	public void addExternalSrcFiles(ScriptSrc scriptSrc) {
		this.externalSrcFiles.add(scriptSrc);
	}
	
	/**
	 * @return the privateBody
	 */
	public String getPrivateBody() {
		return privateBody;
	}

	/**
	 * @param privateBody the privateBody to set
	 */
	public void setPrivateBody(String privateBody) {
		this.privateBody = privateBody;
	}

	/**
	 * @return the privateSectionContent
	 */
	public Set<ContentEntry> getPrivateSectionContent() {
		return privateSectionContent;
	}

	/**
	 * @param privateSectionContent the privateSectionContent to set
	 */
	public void setPrivateSectionContent(Set<ContentEntry> privateSectionContent) {
		this.privateSectionContent = privateSectionContent;
	}

	/**
	 * @param privateSectionContent the privateSectionContent to set
	 */
	public void addPrivateSectionContent(ContentEntry contentEntry) {
		this.privateSectionContent.add(contentEntry);
	}

	/**
	 * @return the privateSegmentContent
	 */
	public Set<ContentEntry> getPrivateSegmentContent() {
		return privateSegmentContent;
	}

	/**
	 * @param privateSegmentContent the privateSegmentContent to set
	 */
	public void setPrivateSegmentContent(Set<ContentEntry> privateSegmentContent) {
		this.privateSegmentContent = privateSegmentContent;
	}

	/**
	 * @param privateSegmentContent the privateSegmentContent to set
	 */
	public void addPrivateSegmentContent(ContentEntry contentEntry) {
		this.privateSegmentContent.add(contentEntry);
	}

	/**
	 * @return the bookmarked
	 */
	public Boolean getBookmarked() {
		return bookmarked;
	}


	/**
	 * @param bookmarked the bookmarked to set
	 */
	public void setBookmarked(Boolean bookmarked) {
		this.bookmarked = bookmarked;
	}
	
	/**
	 * @return the publicAnswer
	 */
	public String getPublicAnswer() {
		return publicAnswer;
	}


	/**
	 * @param publicAnswer the publicAnswer to set
	 */
	public void setPublicAnswer(String publicAnswer) {
		this.publicAnswer = publicAnswer;
	}


	/**
	 * @return the privateAnswer
	 */
	public String getPrivateAnswer() {
		return privateAnswer;
	}


	/**
	 * @param privateAnswer the privateAnswer to set
	 */
	public void setPrivateAnswer(String privateAnswer) {
		this.privateAnswer = privateAnswer;
	}


	/**
	 * @return the rawBody
	 */
	public String getRawBody() {
		return rawBody;
	}


	/**
	 * @param rawBody the rawBody to set
	 */
	public void setRawBody(String rawBody) {
		this.rawBody = rawBody;
	}


	/**
	 * @return the viewUUID
	 */
	public String getViewUUID() {
		return viewUUID;
	}

	/**
	 * @param viewUUID the viewUUID to set
	 */
	public void setViewUUID(String viewUUID) {
		this.viewUUID = viewUUID;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ContentResponse [id=" + id + ", translatedTo=" + translatedTo + ", language=" + language
				+ ", lastModifiedDate=" + lastModifiedDate + ", averageRating=" + averageRating + ", tagSets=" + tagSets
				+ ", publicBody=" + publicBody + ", publicAnswer=" + publicAnswer + ", publicSectionContent="
				+ publicSectionContent + ", publicSegmentContent=" + publicSegmentContent + ", privateBody="
				+ privateBody + ", privateAnswer=" + privateAnswer + ", privateSectionContent=" + privateSectionContent
				+ ", privateSegmentContent=" + privateSegmentContent + ", relatedContent=" + relatedContent
				+ ", customFields=" + customFields + ", publishedId=" + publishedId + ", contentCategory="
				+ contentCategory + ", isFeatured=" + isFeatured + ", version=" + version + ", title=" + title
				+ ", locale=" + locale + ", numberOfRatings=" + numberOfRatings + ", contentType=" + contentType
				+ ", viewContent=" + viewContent + ", description=" + description + ", attachments=" + attachments
				+ ", publishedDate=" + publishedDate + ", expiredDate=" + expiredDate + ", lastModifiedBy="
				+ lastModifiedBy + ", owner=" + owner + ", weighting=" + weighting + ", status=" + status
				+ ", bookmarked=" + bookmarked + ", rawBody=" + rawBody + ", viewUUID=" + viewUUID + ", externalSrcFiles=" + externalSrcFiles + " ]";
	}
}