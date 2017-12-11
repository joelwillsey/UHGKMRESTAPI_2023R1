/**
 * 
 */
package com.verint.services.km.dao;

import java.util.Set;

import com.verint.services.km.model.Tag;
import com.verint.services.km.model.TagSet;

/**
 * @author jmiller
 *
 */
public interface TagsDAO {

	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public TagSet[] getAllTagSets(String username, String password);

	/**
	 * 
	 * @param username
	 * @param password
	 * @param tagset
	 * @return
	 */
	public Tag[] getTagSet(String username, String password, String tagset);

	/**
	 * 
	 * @param username
	 * @param password
	 * @param tagsets
	 * @return
	 */
	public Set<TagSet> getTagSets(String username, String password, String[] tagsets);	
}