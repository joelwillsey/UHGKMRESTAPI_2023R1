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
@XmlRootElement(name = "SearchRequest")
@XmlAccessorType(XmlAccessType.NONE)
public class SearchRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@XmlElement(nillable=true)
	private String query = "*";
	
	@XmlElement(nillable=true)
	private BigInteger page = new BigInteger("0");

	@XmlElement(nillable=true)
	private BigInteger size = new BigInteger("10");

	@XmlElement(nillable=true)
	private String categories = "";

	@XmlElement(nillable=true)
	private String tags = "";

	@XmlElement(nillable=true)
	private String username = "";

	@XmlElement(nillable=true)
	private String password = "";
	
	@XmlElement(nillable=true)
	private String sort = "";	

	@XmlElement(nillable=true)
	private String publishedId = "";

	/**
	 * 
	 */
	public SearchRequest() {
		super();
	}

	/**
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * @param query the query to set
	 */
	public void setQuery(String query) {
		this.query = query;
	}

	/**
	 * @return the page
	 */
	public BigInteger getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(BigInteger page) {
		if (page != null)
			this.page = page;
	}

	/**
	 * @return the size
	 */
	public BigInteger getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(BigInteger size) {
		if (size != null)
			this.size = size;
	}

	/**
	 * @return the categories
	 */
	public String getCategories() {
		return categories;
	}

	/**
	 * @param categories the categories to set
	 */
	public void setCategories(String categories) {
		this.categories = categories;
	}

	/**
	 * @return the tags
	 */
	public String getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(String tags) {
		this.tags = tags;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the sort
	 */
	public String getSort() {
		return sort;
	}

	/**
	 * @param sort the sort to set
	 */
	public void setSort(String sort) {
		this.sort = sort;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SearchRequest [query=" + query + ", page=" + page + ", size=" + size + ", categories=" + categories
				+ ", tags=" + tags + ", username=" + username + ", password=" + password + ", sort=" + sort
				+ ", publishedId=" + publishedId + "]";
	}
}