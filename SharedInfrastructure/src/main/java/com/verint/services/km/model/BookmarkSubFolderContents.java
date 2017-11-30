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
	private ArrayList<BookmarkFolderContent> subSubFolders = new ArrayList<BookmarkFolderContent>();

	/**
	 * Constructor
	 */
	public BookmarkSubFolderContents() {
		super();
	}

	
	
	
	
	@Override
	public String toString() {
		return "BookmarkSubFolderContents [bookmarkFolderContent=" + bookmarkFolderContent.toString() + ", subSubFolders=" + subSubFolders.toString()  +"]";
	}
}
