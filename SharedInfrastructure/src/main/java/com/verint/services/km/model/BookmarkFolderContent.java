package com.verint.services.km.model;

import java.io.Serializable;
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
	private float sequenceNumber;
		
	@XmlElement(nillable=true)
	private String title;
		
	@XmlElement(nillable=true)
	private String parentFolderId;

	@XmlElement(nillable=true)
	private BookmarkedContentV2[] bookmarkContentList;

	/**
	 * Constructor
	 */
	public BookmarkFolderContent() {
		super();
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String ti){
		this.title = ti;
	}
	
	public String getParentFolderId(){
		return parentFolderId;
	}
	
	public void setParentFolderId(String par){
		this.parentFolderId = par;
	}
	
	public float getSequenceNumber(){		
		return sequenceNumber;
	}
	
	public void setSequenceNumber(float seq){
		this.sequenceNumber=seq;
	}
	
	public String getLocaleName(){
		return localeName;
	}
	
	public void setLocaleName(String loc){
		this.localeName = loc;
	}
	
	public String getFolderId(){
		return folderId;
	}
	
	public void setFolderId(String fol){
		this.folderId = fol;
	}
	
	public Date getCreatedDate(){
		return createdDate;
	}
	
	public void setCreatedDate(Date dat){
		this.createdDate = dat;
	}
	
	public BookmarkedContentV2[] getBookmarkContentList(){
		return bookmarkContentList;
	}
	
	public void setBookmarkContentList(BookmarkedContentV2[] bookmarkContentList){
		this.bookmarkContentList = bookmarkContentList;
	}
	
	
	@Override
	public String toString() {
		return "BookmarkFolderContent [folderId=" + folderId + ", title=" + title + ", localeName=" + localeName + ", createdDates=" + createdDate.toString()  
				+ ", sequenceNumber=" + sequenceNumber + ", parentFolderIdr=" + parentFolderId + ", bookmarkContentList=" + bookmarkContentList.toString() +"]";
	}
}
