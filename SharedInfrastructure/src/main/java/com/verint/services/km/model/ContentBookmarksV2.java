package com.verint.services.km.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ContentBookmarksV2")
@XmlAccessorType(XmlAccessType.NONE)
public class ContentBookmarksV2 {

	private static final long serialVersionUID = 1L;
	
	@XmlElement(nillable=true)
	private ArrayList<BookmarkedContentV2> bookmarks = new ArrayList<BookmarkedContentV2>();

	@XmlElement(nillable=true)
	private ArrayList<BookmarkFolderContent> folders = new ArrayList<BookmarkFolderContent>();

	/**
	 * Constructor
	 */
	public ContentBookmarksV2() {
		super();
	}

	/**
	 * @return the bookmarks
	 */
	public ArrayList<BookmarkedContentV2> getBookmarks() {
		return bookmarks;
	}

	/**
	 * @param bookmarks the bookmarks to set
	 */
	public void setBookmarks(ArrayList<BookmarkedContentV2> bookmarks) {
		this.bookmarks = bookmarks;
	}
	
	/**
	 * @return the folders
	 */
	public ArrayList<BookmarkFolderContent> getFolders() {
		return folders;
	}

	/**
	 * @param folders the folders to set
	 */
	public void setFolders(ArrayList<BookmarkFolderContent> folders) {
		this.folders = folders;
	}
	
	@Override
	public String toString() {
		return "ContentBookmarksV2 [folders=" + folders.toString() + ", bookmarks=" + bookmarks.toString()  +"]";
	}
}
