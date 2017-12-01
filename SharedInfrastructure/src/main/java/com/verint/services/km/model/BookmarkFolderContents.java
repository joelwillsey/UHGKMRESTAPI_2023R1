package com.verint.services.km.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "BookmarkFolderContents")
@XmlAccessorType(XmlAccessType.NONE)
public class BookmarkFolderContents implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@XmlElement(nillable=true)
	private BookmarkFolderContent bookmarkFolderContent;

	@XmlElement(nillable=true)
	private BookmarkSubFolderContents[] subFolders;

	/**
	 * Constructor
	 */
	public BookmarkFolderContents() {
		super();
	}

	/**
	 * @return the folders
	 */
	public BookmarkFolderContent getbookmarkFolderContent() {
		return bookmarkFolderContent;
	}

	/**
	 * @param folders the folders to set
	 */
	public void setBookmarkFolderContent(BookmarkFolderContent bookmarkFolderContent) {
		this.bookmarkFolderContent = bookmarkFolderContent;
	}
	
	/**
	 * @return the subfolders
	 */
	public BookmarkSubFolderContents[] getSubFolders() {
		return subFolders;
	}

	/**
	 * @param subfolders the subfolders to set
	 */
	public void setSubFolders(BookmarkSubFolderContents[] subFolders) {
		this.subFolders = subFolders;
	}
	
	@Override
	public String toString() {
		return "BookmarkFolderContents [bookmarkFolderContent=" + bookmarkFolderContent.toString() + ", subFolders=" + subFolders.toString()  +"]";
	}
}
