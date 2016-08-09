/**
 * 
 */
package com.verint.services.km.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author jmiller
 *
 */
@XmlRootElement(name = "ContentEntry")
@XmlAccessorType(XmlAccessType.NONE)
public class ContentEntry implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@XmlElement(nillable=true)
	private String id = "";
	
	@XmlElement(nillable=true)
	private String locale = "";
	
	@XmlElement(nillable=true)
	private String type = "";
	
	@XmlElement(nillable=true)
	private String version = "";
	
	@XmlElement(nillable=true)
	private String title = "";
	
	@XmlElement(nillable=true)
	private String view = "";

	/**
	 * 
	 */
	public ContentEntry() {
		super();
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
	 * @return the view
	 */
	public String getView() {
		return view;
	}

	/**
	 * @param view the view to set
	 */
	public void setView(String view) {
		this.view = view;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ContentEntry [id=" + id + ", locale=" + locale + ", type=" + type + ", version=" + version + ", title="
				+ title + ", view=" + view + "]";
	}
}