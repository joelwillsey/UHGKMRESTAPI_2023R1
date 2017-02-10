/**
 * 
 */
package com.verint.services.km.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author jmiller
 *
 */
@XmlRootElement(name = "LoginResponse")
@XmlAccessorType(XmlAccessType.NONE)
public class LoginV2Response implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlElement(nillable=true)
	private String username;

	@XmlElement(nillable=true)
    private Date expires;

	@XmlElement(nillable=true)
    private String fullName;

	@XmlElement(nillable=true)
    private BigInteger grace;

	@XmlElement(nillable=true)
    private Boolean isDeleted;

	@XmlElement(nillable=true)
    private String displayName;

	@XmlElement(nillable=true)
    private String locale;

	@XmlElement(nillable=true)
    private String firstName;

	@XmlElement(nillable=true)
    private Boolean isDisabled;

	@XmlElement
    private BigInteger loginResult;

	@XmlElement(nillable=true)
    private String lastName;

	@XmlElement(nillable=true)
    private String message;

	/**
	 * 
	 */
	public LoginV2Response() {
		super();
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the expires
	 */
	public Date getExpires() {
		return expires;
	}

	/**
	 * @param expires the expires to set
	 */
	public void setExpires(Date expires) {
		this.expires = expires;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the grace
	 */
	public BigInteger getGrace() {
		return grace;
	}

	/**
	 * @param grace the grace to set
	 */
	public void setGrace(BigInteger grace) {
		this.grace = grace;
	}

	/**
	 * @return the isDeleted
	 */
	public Boolean getIsDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the isDisabled
	 */
	public Boolean getIsDisabled() {
		return isDisabled;
	}

	/**
	 * @param isDisabled the isDisabled to set
	 */
	public void setIsDisabled(Boolean isDisabled) {
		this.isDisabled = isDisabled;
	}

	/**
	 * @return the loginResult
	 */
	public BigInteger getLoginResult() {
		return loginResult;
	}

	/**
	 * @param loginResult the loginResult to set
	 */
	public void setLoginResult(BigInteger loginResult) {
		this.loginResult = loginResult;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LoginResponse [username=" + username + ", expires=" + expires + ", fullName="
				+ fullName + ", grace=" + grace + ", isDeleted=" + isDeleted + ", displayName=" + displayName
				+ ", locale=" + locale + ", firstName=" + firstName + ", isDisabled=" + isDisabled + ", loginResult="
				+ loginResult + ", lastName=" + lastName + ", message=" + message + "]";
	}
}