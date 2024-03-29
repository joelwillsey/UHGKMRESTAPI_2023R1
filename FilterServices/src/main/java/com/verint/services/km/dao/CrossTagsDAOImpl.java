/**
 * 
 */
package com.verint.services.km.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kana.contactcentre.services.model.SearchV1Service_wsdl.SharedTextSearchResponseBodyType;
import com.verint.services.km.dbcp.ConnectionPool;
import com.verint.services.km.model.CrossTag;
import com.verint.services.km.model.CrossTagResponse;
import com.verint.services.km.model.Tag;

/**
 * @author jmiller
 *
 */
@Repository
public class CrossTagsDAOImpl extends BaseDAOImpl implements CrossTagsDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(CrossTagsDAOImpl.class);
	
	@Autowired
	private TagsDAO tagsDAO;

	/**
	 * 
	 */
	public CrossTagsDAOImpl() {
		super();
		LOGGER.info("Entering CrossTagsDAOImpl()");
		LOGGER.info("Exiting CrossTagsDAOImpl()");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.verint.services.km.dao.CrossTagsDAO#getTagSetConfigurations(java.lang.String, java.lang.String, java.lang.String[], java.lang.String)
	 */
	@Override
	public CrossTagResponse getTagSetConfigurations(String username, String password, String oidcToken, String[] sourcetag, String targettagset, String targettagset1, String targettagset2, String targettagset3) throws SQLException {
		LOGGER.info("Entering getTagSetConfiguration()");
		LOGGER.debug("sourcetag: " + Arrays.toString(sourcetag));
		LOGGER.debug("targettagset: " + targettagset);
		LOGGER.debug("targettagset1: " + targettagset1);
		LOGGER.debug("targettagset2: " + targettagset2);
		LOGGER.debug("targettagset3: " + targettagset3);
		final CrossTagResponse response = new CrossTagResponse();
		
		// Get the connection and statement
		final Connection connection = ConnectionPool.getConnection();
		PreparedStatement stmt = null;

		Instant startCross = Instant.now();
		//looping through all three target sets to get all crosstags in one source
		for(int c = 0; c < 4; c++){
			
			//setting the currentTagSet
			String currentTagSet = "";
			
			//switched between the target tag sets
			switch (c) {
			case 0:	currentTagSet = targettagset;
					break;
			case 1: currentTagSet = targettagset1;
					break;
			case 2: currentTagSet = targettagset2;
					break;
			case 3: currentTagSet = targettagset3;
					break;
			}
		
		//actually executes everything, just three times
		try {
			// Loop through all source tags
			for (int s = 0; (sourcetag != null && s < sourcetag.length && currentTagSet != null); s++) {
				LOGGER.debug("Looping Begin sourceTag[" + s + "]: " + sourcetag[s] + " targetTagSet" + c + ": " + currentTagSet);
				//final String query = "SELECT t.tagset, t.tag, t.preselected, t.select_children FROM X_CROSSTAG c JOIN X_CROSSTAG_SOURCE s ON s.CROSSTAG_ID = c.ID JOIN X_CROSSTAG_TARGET t ON t.CROSSTAG_ID = c.ID WHERE s.TAG = ? AND t.TAGSET = ?";
				final String query = "SELECT t.tagset, t.tag, t.preselected, t.select_children FROM AKTR_TAG_RELATIONSHIP c JOIN AKTR_TAG_REL_SOURCE s ON s.CROSSTAG_ID = c.ID JOIN AKTR_TAG_REL_TARGET t ON t.CROSSTAG_ID = c.ID WHERE s.TAG = ? AND t.TAGSET = ?";
				stmt = connection.prepareStatement(query);
				stmt.setString(1, sourcetag[s]);
				stmt.setString(2, currentTagSet);
				// Execute query
				Instant start = Instant.now();
				final ResultSet rs = stmt.executeQuery();
				Instant end = Instant.now();
				LOGGER.debug("SERVICE_CALL_PERFORMANCE("+username+") - getTagSetConfigurations(" + sourcetag[s] + "," + currentTagSet + ") duration: " + Duration.between(start, end).toMillis() + "ms");

				
				
				// loop through all records
				while (rs != null && rs.next()) {
					// Should only be one
					String tagset = rs.getString("tagset");
					String tag = rs.getString("tag");
					final String preselected = rs.getString("preselected");
					final String children = rs.getString("select_children");
					LOGGER.debug("tagset: " + tagset);
					LOGGER.debug("tag: " + tag);
					LOGGER.debug("preselected: " + preselected);
					LOGGER.debug("children: " + children);
						
					final CrossTag crossTag = new CrossTag();
					
					// Set the preselect indicator
					if ("Y".equals(preselected)) {
						crossTag.setPreselected(true);
					} else {
						crossTag.setPreselected(false);									
					}

					// Set the children indicator
					if ("Y".equals(children)) {
						crossTag.setSelectChildren(true);
					} else {
						crossTag.setSelectChildren(false);								
					}
					
					
					
					crossTag.setSourceTag(sourcetag[s]);
					crossTag.setTargetTagSet(targettagset);
					

					//final Tag[] tags = tagsDAO.getTagSet(username, password, oidcToken, tagset);
					final Tag[] tags = tagsDAO.getTagSet(username, password, oidcToken, tag);

					if (tags != null) {
						for (int v = 0; v < tags.length; v++) {
							LOGGER.debug("tags [" + v + "]=" + tags[v].toString());
						}
					}
					
					// convert the list
					if (children != null && children.equals("Y")) {
						boolean found = false;
						boolean skipFirst = false;
						boolean doneWithTree = false;

						final List<Tag> lTags = new ArrayList<Tag>();
								
						String parentTagName = "";
						
						for (int x = 0; (tags != null) && (x < tags.length); x++) {
							if (!parentTagName.equals("") && parentTagName.equals(tags[x].getParentTagName())) {
								// This means we are done with the tree
								doneWithTree = true;
							} else if (!doneWithTree) {
								
								if (tags[x].getSystemTagName().equals(tag)) {
									LOGGER.debug("Found system tag: " + tags[x].getSystemTagName());
									found = true;
									skipFirst = true;

									// Go through top down to get the parent info first
									getBackOrder(tags, tags[x].getParentTagName(), lTags);

									// Set the parent
									parentTagName = tags[x].getParentTagName();
									
									// Set the preselect indicator
									setPreSelected(preselected, tags[x]);

									// Add the tag
									LOGGER.debug("Adding tag: " + tags[x]);
									lTags.add(tags[x]);
									
								} else {
									skipFirst = false;
								}

								// Check to add a tag
								addTag(found, skipFirst, tags[x], lTags);
							}
						}
						final Set<Tag> nTags = new LinkedHashSet<Tag>(lTags);
						crossTag.setTags(nTags);
						response.addCrossTag(crossTag);
					} else if (tags != null && tags.length > 1) {
						boolean doneWithTree = false;
						
						//creation of the header tag
						
						boolean topOfTagset = false;
						int findUnderScore = tags[0].getSystemTagName().indexOf("_");
						if (findUnderScore != -1) {
							//We found the root tag, the root tags do not have the _ in them
							topOfTagset = true;
						} else {
							//We need to grab the parent tag of the target tag so we can climb up to the root tag
							
						}

						Tag headerTag = new Tag();
						headerTag.setParentTagName(tags[0].getSystemTagName());
						headerTag.setSystemTagName(tags[0].getSystemTagName());
						headerTag.setSystemTagDisplayName("Header");
						
						final List<Tag> lTags = new ArrayList<Tag>();
						
						//insertion of the header tag into the tag list
						lTags.add(headerTag);
						
						String parentTagName = "";
						for (int x = 0; (tags != null) && (x < tags.length); x++) {
							
							//this tells us if we've found the tags
							if (!parentTagName.equals("") && parentTagName.equals(tags[x].getParentTagName())) {
								// This means we are done with the tree
								doneWithTree = true;
							} else if (!doneWithTree) {
								if (tags[x].getSystemTagName().equals(tag)) {
									LOGGER.debug("Found system tag: " + tags[x].getSystemTagName());

									// Go through top down to get the parent info first
									getBackOrder(tags, tags[x].getParentTagName(), lTags);

									// Set the parent
									parentTagName = tags[x].getParentTagName();
									
									// Set the preselect indicator
									setPreSelected(preselected, tags[x]);

									// Add the tag
									LOGGER.debug("Adding tag: " + tags[x]);
									lTags.add(tags[x]);
									
									// Added this in, in case there are multiple instances, this way it increments through and returns only one crossTag
									if(rs.next()){
										parentTagName = "";
										tag = rs.getString("tag");
										x = 0;
									}
								}
							}				
						}
						final Set<Tag> nTags = new LinkedHashSet<Tag>(lTags);
						crossTag.setTags(nTags);
						response.addCrossTag(crossTag);
					}
				}
				rs.close();
			}
			

		} catch (SQLException sqle) {
			LOGGER.error("SQLException", sqle);
			throw sqle;
		} finally {
			if (stmt != null && c == 3)
				stmt.close();
			if (connection != null && c == 3)
				connection.close();
		}
		}
		Instant endCross = Instant.now();
		LOGGER.debug("SERVICE_CALL_PERFORMANCE("+username+") - getTagSetConfigurations(AllCalls) duration: " + Duration.between(startCross, endCross).toMillis() + "ms");
		LOGGER.debug("CrossTagResponse: "  + response);
		LOGGER.info("Exiting getTagSetConfiguration()");
		return response;
	}
	
	/**
	 * 
	 * @param tags
	 * @param pTag
	 * @param lTags
	 */
	private void getBackOrder(Tag[] tags, String pTag, List<Tag> lTags) {
		LOGGER.debug("Entering getBackOrder()");
		// Loop through all the tags to go back through order
		for (int a = 0; a < tags.length; a++) {
			if (pTag != null) {
				int retValue = determineTags(tags, pTag);
				pTag = tags[retValue].getParentTagName();
				if (pTag != null && pTag.length() > 0) {
					// Add it to the list
					// Add the tag
					LOGGER.debug("Adding tag: " + tags[retValue]);
					lTags.add(0, tags[retValue]);
				}
			}
		}
		LOGGER.debug("Exiting getBackOrder()");
	}

	/**
	 * 
	 * @param preselected
	 * @param tag
	 */
	private void setPreSelected(String preselected, Tag tag) {
		LOGGER.debug("Entering setPreSelected()");		
		// Set the preselect indicator
		if ("Y".equals(preselected)) {
			tag.setPreselected(true);
		} else {
			tag.setPreselected(false);									
		}
		LOGGER.debug("Exiting setPreSelected()");
	}

	/**
	 * 
	 * @param found
	 * @param skipFirst
	 * @param tag
	 * @param lTags
	 */
	private void addTag(boolean found, boolean skipFirst, Tag tag, List<Tag> lTags) {
		LOGGER.debug("Entering addTag()");
		// Add it in
		if (found && !skipFirst) {
			// Add the tag
			LOGGER.debug("Adding tag: " + tag);
			lTags.add(tag);
			skipFirst = false;
		}
		LOGGER.debug("Exiting addTag()");
	}

	/**
	 * 
	 * @param tags
	 * @param parentTag
	 * @return
	 */
	private int determineTags(Tag[] tags, String parentTag) {
		LOGGER.debug("Entering determineTags()");
		int pTag = 0;
		if (parentTag != null && parentTag != "") {
			LOGGER.debug("ParentTag: " + parentTag);
		}
		// Go through top down to get the parent info first
		for (int a = 0; a < tags.length; a++) {
			if (parentTag.equals(tags[a].getSystemTagName())) {
				pTag = a;
				LOGGER.debug("pTag: " + pTag);
				break;
			}
		}
		LOGGER.debug("Exiting determineTags()");
		return pTag;
	}
}
