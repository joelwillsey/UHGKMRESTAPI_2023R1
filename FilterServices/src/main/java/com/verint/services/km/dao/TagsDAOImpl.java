/**
 * 
 */
package com.verint.services.km.dao;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

import com.verint.services.km.model.rest.RestTag;
import com.verint.services.km.util.ConfigInfo;
import com.verint.services.km.util.RestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;

import com.verint.services.km.model.Tag;
import com.verint.services.km.model.TagSet;

/**
 * @author jmiller
 *
 */
@Repository
public class TagsDAOImpl extends BaseDAOImpl implements TagsDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(TagsDAOImpl.class);
	private static String REST_TAGS_URL;

	static {
		try {
			REST_TAGS_URL = (new ConfigInfo()).getRestKmTagService();
		} catch (Throwable t) {
			LOGGER.error("Throwable Exception", t);
			System.exit(1);
		}
	}

	/**
	 * 
	 */
	public TagsDAOImpl() {
		super();
		LOGGER.info("Entering TagsDAOImpl()");
		LOGGER.info("Exiting TagsDAOImpl()");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final TagsDAO tagsDAO = new TagsDAOImpl();
		TagSet[] tagSets = tagsDAO.getAllTagSets("kmagent", "verint", "oidc");
		for (int x = 0; (tagSets != null) && (x < tagSets.length); x++) {
			System.out.println("TagSet: " + tagSets[x]);
		}
		Tag[] tags = tagsDAO.getTagSet("kmagent", "verint", "oidc", "product");
		for (int x = 0; (tags != null) && (x < tags.length); x++) {
			System.out.println("Tag: " + tags[x]);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.verint.services.km.dao.TagsDAO#getAllTagSets(java.lang.String, java.lang.String)
	 */
	@Override
	public TagSet[] getAllTagSets(String username, String password, String oidcToken) {
		LOGGER.info("Entering getAllTagSet()");
		LOGGER.debug("username: " + username);
		TagSet[] tagSets = null;

		try {
			Instant start = Instant.now();
			RestTag tagJsonResponse = RestUtil.getAndDeserialize(REST_TAGS_URL + "/default/tag",null,
					HttpMethod.GET, RestTag.class, oidcToken, null, false);
			Instant end = Instant.now();
			LOGGER.debug("SERVICE_CALL_PERFORMANCE("+username+") - getAllTagSets() duration: " + Duration.between(start, end).toMillis() + "ms");
			if (tagJsonResponse != null && tagJsonResponse.getMember() != null) {
				tagSets = new TagSet[tagJsonResponse.getMember().size()];
				for (int i = 0; i < tagJsonResponse.getMember().size(); i++) {
					RestTag curTag = tagJsonResponse.getMember().get(i);
					TagSet tagSet = new TagSet();
					tagSet.setDisplayTagName(curTag.getName());
					tagSet.setSystemTagName(curTag.getIdentifier());
					tagSets[i] = tagSet;
				}
			}
		} catch (IOException re) {
			LOGGER.error("Exception", re);
		}
		//LOGGER.trace("TagSet[]: " + tagSets);
		LOGGER.debug("TagSet[]: " + tagSets.length + " TagSets");
		LOGGER.info("Exiting getAllTagSet()");
		return tagSets;
	}

	/*
	 * (non-Javadoc)
	 * @see com.verint.services.km.dao.TagsDAO#getTagSet(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Tag[] getTagSet(String username, String password, String oidcToken, String tagset) {
		LOGGER.info("Entering getTagSet()");
		LOGGER.debug("username: " + username);
		LOGGER.debug("tagset: " + tagset);
		Tag[] tags = null;

		Instant start = Instant.now();
		List<Tag> restTags = getRestTags(REST_TAGS_URL + "/default/tag/" + tagset + "?flatten=true&size=150",oidcToken, username, 0);
		Instant end = Instant.now();
		LOGGER.debug("SERVICE_CALL_PERFORMANCE("+username+") - getTagSet(" + tagset + ") duration: " + Duration.between(start, end).toMillis() + "ms");
		if (!restTags.isEmpty()) {
			tags = restTags.toArray(new Tag[restTags.size()]);
		}
		LOGGER.info("Exiting getTagSet()");
		return tags;
	}

	/*
	 * (non-Javadoc)
	 * @see com.verint.services.km.dao.TagsDAO#getTagSets(java.lang.String, java.lang.String[])
	 */
	@Override
	public Set<TagSet> getTagSets(String username, String password, String oidcToken, String[] tagsets) {
		LOGGER.info("Entering getTagSets()");
		LOGGER.debug("username: " + username);
		LOGGER.debug("tagset: " + tagsets);
		List<TagSet> tagSets = new ArrayList<TagSet>();

		// Loop through the tagsets
		for (int i = 0; (tagsets != null) && (i < tagsets.length); i++) {
			final TagSet tagSet = new TagSet();
			tagSet.setSystemTagName(tagsets[i]);
			Tag[] tags = this.getTagSet(username, password, oidcToken, tagsets[i]);
			if (tags == null) {
				//create empty tag array
				tags = new Tag[]{};
			}
			final Set<Tag> setTags = new LinkedHashSet<>(Arrays.asList(tags));
			tagSet.setTags(setTags);
			tagSets.add(tagSet);
		}
		LOGGER.info("Exiting getTagSets()");
		return new LinkedHashSet<>(tagSets);
	}

	private List<Tag> getRestTags(String url, String oidcToken, String username, int startIndex) {
		List<Tag> tags = new ArrayList<>();
		try {
			Instant start = Instant.now();
			RestTag tagJsonResponse = RestUtil.getAndDeserialize(url,null,
						HttpMethod.GET, RestTag.class, oidcToken, null, false);
				Instant end = Instant.now();
			LOGGER.debug("SERVICE_CALL_PERFORMANCE("+username+") - getRestTags(" + startIndex +") duration: " + Duration.between(start, end).toMillis() + "ms");
			if (startIndex == 0) {
				final Tag tag = new Tag();
				tag.setSystemTagDisplayName(tagJsonResponse.getName());
				tag.setSystemTagName(tagJsonResponse.getIdentifier());
				//Start
				if(tagJsonResponse.getUp() != null) {
					//There is a parent tag to the tag we called
					String rootParentId = tagJsonResponse.getUp().getId();
					String rootParentName = rootParentId.substring(rootParentId.lastIndexOf("/")+1);
					if (!rootParentName.equals(tag.getSystemTagName())) {
						tag.setParentTagName(rootParentName);
					}
				}
				//END
				tags.add(tag);
			}
			if (tagJsonResponse != null && tagJsonResponse.getMember() != null) {
				for (int i = 0; i < tagJsonResponse.getMember().size(); i++) {
					final Tag tag = new Tag();
					RestTag curTag = tagJsonResponse.getMember().get(i);
					tag.setSystemTagDisplayName(curTag.getName());
					if (curTag.getUp().getId() != null) {
						String parentId = curTag.getUp().getId();
						tag.setParentTagName(parentId.substring(parentId.lastIndexOf("/")+1));
					}
					tag.setSystemTagName(curTag.getIdentifier());
					tags.add(tag);
				}
			}
			if (tagJsonResponse.getView() != null && tagJsonResponse.getView().getNext() != null) {
				List<Tag> nextTags = getRestTags(tagJsonResponse.getView().getNext(), oidcToken, username, startIndex+150);
				tags.addAll(nextTags);
			}
		} catch (Exception e) {
			LOGGER.error("Error during getRestTags()", e);
		}
		return tags;
	}
}
