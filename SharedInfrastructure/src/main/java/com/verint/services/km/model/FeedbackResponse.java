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
@XmlRootElement(name = "FeedbackResponse")
@XmlAccessorType(XmlAccessType.NONE)
public class FeedbackResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlElement(nillable=true)
    private String optionalComments;

	@XmlElement(nillable=true)
    private BigInteger feedbackCode;

    @XmlElement(nillable=true)
    private String notificationName;

    @XmlElement
    private Boolean notification;

    @XmlElement
    private BigInteger outcomeCode;

    @XmlElement(nillable=true)
    private String notificationEmail;	

	/**
	 * 
	 */
	public FeedbackResponse() {
		super();
	}

	/**
	 * @return the optionalComments
	 */
	public String getOptionalComments() {
		return optionalComments;
	}

	/**
	 * @param optionalComments the optionalComments to set
	 */
	public void setOptionalComments(String optionalComments) {
		this.optionalComments = optionalComments;
	}

	/**
	 * @return the feedbackCode
	 */
	public BigInteger getFeedbackCode() {
		return feedbackCode;
	}

	/**
	 * @param feedbackCode the feedbackCode to set
	 */
	public void setFeedbackCode(BigInteger feedbackCode) {
		this.feedbackCode = feedbackCode;
	}

	/**
	 * @return the notificationName
	 */
	public String getNotificationName() {
		return notificationName;
	}

	/**
	 * @param notificationName the notificationName to set
	 */
	public void setNotificationName(String notificationName) {
		this.notificationName = notificationName;
	}

	/**
	 * @return the notification
	 */
	public Boolean isNotification() {
		return notification;
	}

	/**
	 * @param notification the notification to set
	 */
	public void setNotification(Boolean notification) {
		this.notification = notification;
	}

	/**
	 * @return the outcomeCode
	 */
	public BigInteger getOutcomeCode() {
		return outcomeCode;
	}

	/**
	 * @param outcomeCode the outcomeCode to set
	 */
	public void setOutcomeCode(BigInteger outcomeCode) {
		this.outcomeCode = outcomeCode;
	}

	/**
	 * @return the notificationEmail
	 */
	public String getNotificationEmail() {
		return notificationEmail;
	}

	/**
	 * @param notificationEmail the notificationEmail to set
	 */
	public void setNotificationEmail(String notificationEmail) {
		this.notificationEmail = notificationEmail;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FeedbackResponse [optionalComments=" + optionalComments + ", feedbackCode=" + feedbackCode
				+ ", notificationName=" + notificationName + ", notification=" + notification + ", outcomeCode="
				+ outcomeCode + ", notificationEmail=" + notificationEmail + "]";
	}
}