/**
 * 
 */
package com.verint.services.km.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author jmiller
 *
 */
@XmlRootElement(name = "FeedbackRequest")
@XmlAccessorType(XmlAccessType.NONE)
public class Feedback implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlElement
	private String contentId;

	@XmlElement
    private String comment;

	@XmlElement
    private Boolean notification;

	@XmlElement(nillable=true)
    private String locale;

	@XmlElement
    private BigInteger feedbackCodeID;

	@XmlElement(nillable=true)
    private BigInteger rating;

	@XmlElement(nillable=true)
    private String viewID;

	@XmlElement(nillable=true)
    private String email;

	@XmlElement(nillable=true)
    private String name;

	/**
	 * Constructor
	 */
	public Feedback() {
		super();
	}

	/**
	 * @return the contentId
	 */
	public String getContentId() {
		return contentId;
	}

	/**
	 * @param contentId the contentId to set
	 */
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the notification
	 */
	public Boolean getNotification() {
		return notification;
	}

	/**
	 * @param notification the notification to set
	 */
	public void setNotification(Boolean notification) {
		this.notification = notification;
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
	 * @return the feedbackCodeID
	 */
	public BigInteger getFeedbackCodeID() {
		return feedbackCodeID;
	}

	/**
	 * @param feedbackCodeID the feedbackCodeID to set
	 */
	public void setFeedbackCodeID(BigInteger feedbackCodeID) {
		this.feedbackCodeID = feedbackCodeID;
	}

	/**
	 * @return the rating
	 */
	public BigInteger getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(BigInteger rating) {
		this.rating = rating;
	}

	/**
	 * @return the viewID
	 */
	public String getViewID() {
		return viewID;
	}

	/**
	 * @param viewID the viewID to set
	 */
	public void setViewID(String viewID) {
		this.viewID = viewID;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Feedback [contentId=" + contentId + ", comment=" + comment + ", notification=" + notification
				+ ", locale=" + locale + ", feedbackCodeID=" + feedbackCodeID + ", rating=" + rating + ", viewID="
				+ viewID + ", email=" + email + ", name=" + name + "]";
	}
}