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
	private BookmarkedContentV2[] bookmarks;

	@XmlElement(nillable=true)
	private BookmarkFolderContent[] folders;

	/**
	 * Constructor
	 */
	public ContentBookmarksV2() {
		super();
	}

	/**
	 * @return the bookmarks
	 */
	public BookmarkedContentV2[] getBookmarks() {
		return bookmarks;
	}

	/**
	 * @param bookmarks the bookmarks to set
	 */
	public void setBookmarks(BookmarkedContentV2[] bookmarks) {
		this.bookmarks = bookmarks;
	}
	
	/**
	 * @return the folders
	 */
	public BookmarkFolderContent[] getFolders() {
		return folders;
	}

	/**
	 * @param folders the folders to set
	 */
	public void setFolders(BookmarkFolderContent[] folders) {
		this.folders = folders;
	}
	
	@Override
	public String toString() {
		return "ContentBookmarksV2 [folders=" + folders.toString() + ", bookmarks=" + bookmarks.toString()  +"]";
	}
}
