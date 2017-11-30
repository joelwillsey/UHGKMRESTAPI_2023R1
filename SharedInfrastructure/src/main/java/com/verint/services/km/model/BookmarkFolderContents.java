package com.verint.services.km.model;

import java.io.Serializable;
import java.util.ArrayList;

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
	private ArrayList<BookmarkFolderContent> subFolders = new ArrayList<BookmarkFolderContent>();

	/**
	 * Constructor
	 */
	public BookmarkFolderContents() {
		super();
	}

	
	
	
	
	@Override
	public String toString() {
		return "BookmarkFolderContents [bookmarkFolderContent=" + bookmarkFolderContent.toString() + ", subFolders=" + subFolders.toString()  +"]";
	}
}
