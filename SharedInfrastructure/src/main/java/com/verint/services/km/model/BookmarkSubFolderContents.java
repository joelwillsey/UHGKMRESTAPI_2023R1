package com.verint.services.km.model;

import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "BookmarkSubFolderContents")
@XmlAccessorType(XmlAccessType.NONE)
public class BookmarkSubFolderContents implements Serializable{

	private static final long serialVersionUID = 1L;
		
	@XmlElement(nillable=true)
	private BookmarkFolderContent bookmarkFolderContent;

	@XmlElement(nillable=true)
	private BookmarkFolderContent[] subSubFolders;

	/**
	 * Constructor
	 */
	public BookmarkSubFolderContents() {
		super();
	}

	/**
	 * @return the folders
	 */
	public BookmarkFolderContent getBookmarkFolderContent() {
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
	public BookmarkFolderContent[] getsubSubFolders() {
		return subSubFolders;
	}

	/**
	 * @param subfolders the subfolders to set
	 */
	public void setSubSubFolders(BookmarkFolderContent[] subSubFolders) {
		this.subSubFolders = subSubFolders;
	}
	
	
	
	@Override
	public String toString() {
		return "BookmarkSubFolderContents [bookmarkFolderContent=" + bookmarkFolderContent.toString() + ", subSubFolders=" + subSubFolders.toString()  +"]";
	}
}
