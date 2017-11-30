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

	
	
	
	
	@Override
	public String toString() {
		return "ContentBookmarksV2 [folders=" + folders.toString() + ", bookmarks=" + bookmarks.toString()  +"]";
	}
}
