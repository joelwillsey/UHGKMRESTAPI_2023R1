/**
 * 
 */
package com.verint.services.km.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author jmiller
 *
 */
@XmlRootElement(name = "KnowledgeGroupUnit")
@XmlAccessorType(XmlAccessType.NONE)
public class KnowledgeGroupUnit implements Serializable {
	private static final long serialVersionUID = 1L;

    @XmlElement(nillable=true)
    private String contentID = "";

    @XmlElement(nillable=true)
    private Boolean isFeatured;

    @XmlElement(nillable=true)
    private String title = "";
    
    @XmlElement(nillable=true)
    private Double averageRating;
    
    @XmlElement(nillable=true)
    private BigInteger numberOfRatings;
    
    @XmlElement(nillable=true)
    private String locale = "";

    @XmlElement(nillable=true)
    private String contentType = "";
    
    @XmlElement(nillable=true)
    private BigInteger viewCount;

    @XmlElement(nillable=true)
    private String contentSource = "";

    @XmlElement(nillable=true)
    private Set<KnowledgeUnit> knowledgeUnits;

	/**
	 * Constructor
	 */
	public KnowledgeGroupUnit() {
		super();
	}

	/**
	 * @return the contentID
	 */
	public String getContentID() {
		return contentID;
	}

	/**
	 * @param contentID the contentID to set
	 */
	public void setContentID(String contentID) {
		if (contentID != null)
			this.contentID = contentID;
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
		if (isFeatured != null)
			this.isFeatured = isFeatured;
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
		if (title != null)
			this.title = title;
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
		if (averageRating != null)
			this.averageRating = averageRating;
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
		if (numberOfRatings != null)
			this.numberOfRatings = numberOfRatings;
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
		if (locale != null)
			this.locale = locale;
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
	 * @return the viewCount
	 */
	public BigInteger getViewCount() {
		return viewCount;
	}

	/**
	 * @param viewCount the viewCount to set
	 */
	public void setViewCount(BigInteger viewCount) {
		if (viewCount != null)
			this.viewCount = viewCount;
	}

	/**
	 * @return the contentSource
	 */
	public String getContentSource() {
		return contentSource;
	}

	/**
	 * @param contentSource the contentSource to set
	 */
	public void setContentSource(String contentSource) {
		if (contentSource != null)
			this.contentSource = contentSource;
	}

	/**
	 * @return the knowledgeUnits
	 */
	public Set<KnowledgeUnit> getKnowledgeUnits() {
		return knowledgeUnits;
	}

	/**
	 * @param knowledgeUnits the knowledgeUnits to set
	 */
	public void setKnowledgeUnits(Set<KnowledgeUnit> knowledgeUnits) {
		if (knowledgeUnits != null)
			this.knowledgeUnits = knowledgeUnits;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\nKnowledgeGroupUnit [\n contentID=" + contentID + "\n isFeatured=" + isFeatured + "\n title=" + title
				+ "\n averageRating=" + averageRating + "\n numberOfRatings=" + numberOfRatings + "\n locale=" + locale
				+ "\n contentType=" + contentType + "\n viewCount=" + viewCount + "\n contentSource=" + contentSource
				+ "\n knowledgeUnits=" + knowledgeUnits + "]";
	}
}