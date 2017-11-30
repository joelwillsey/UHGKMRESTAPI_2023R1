package com.verint.services.km.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "BookmarkFolderContent")
@XmlAccessorType(XmlAccessType.NONE)
public class BookmarkFolderContent implements Serializable{

	private static final long serialVersionUID = 1L;

	@XmlElement(nillable = true)
	private String folderId;

	@XmlElement(nillable = true)
	private String localeName = "";
	
	@XmlElement(nillable = true)
	private Date createdDate;
	
	@XmlElement(nillable=true)
	private int sequenceNumber;
		
	@XmlElement(nillable=true)
	private String title;
		
	@XmlElement(nillable=true)
	private String parentFolderId;

	@XmlElement(nillable=true)
	private ArrayList<BookmarkedContentV2> bookmarkContentList = new ArrayList<BookmarkedContentV2>();

	/**
	 * Constructor
	 */
	public BookmarkFolderContent() {
		super();
	}

	
	
	
	
	@Override
	public String toString() {
		return "BookmarkFolderContent [folderId=" + folderId + ", title=" + title + ", localeName=" + localeName + ", createdDates=" + createdDate.toString()  
				+ ", sequenceNumber=" + sequenceNumber + ", parentFolderIdr=" + parentFolderId + ", bookmarkContentList=" + bookmarkContentList.toString() +"]";
	}
}
