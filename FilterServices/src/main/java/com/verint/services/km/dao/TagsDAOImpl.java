/**
 * 
 */
package com.verint.services.km.dao;

import java.rmi.RemoteException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.kana.contactcentre.services.model.LoginV1Service_wsdl.LoginUserResponseBodyType;
import com.kana.contactcentre.services.model.TagV1Service_wsdl.GetAllTagSetRequestBodyType;
import com.kana.contactcentre.services.model.TagV1Service_wsdl.GetAllTagSetResponseBodyType;
import com.kana.contactcentre.services.model.TagV1Service_wsdl.GetTagSetRequestBodyType;
import com.kana.contactcentre.services.model.TagV1Service_wsdl.GetTagSetResponseBodyType;
import com.kana.contactcentre.services.model.TagV1Service_wsdl.TagDescriptor;
import com.verint.services.km.model.Tag;
import com.verint.services.km.model.TagSet;

/**
 * @author jmiller
 *
 */
@Repository
public class TagsDAOImpl extends BaseDAOImpl implements TagsDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(TagsDAOImpl.class);

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
		TagSet[] tagSets = tagsDAO.getAllTagSets("kmagent", "verint");
		for (int x = 0; (tagSets != null) && (x < tagSets.length); x++) {
			System.out.println("TagSet: " + tagSets[x]);
		}
		Tag[] tags = tagsDAO.getTagSet("kmagent", "verint", "product");
		for (int x = 0; (tags != null) && (x < tags.length); x++) {
			System.out.println("Tag: " + tags[x]);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.verint.services.km.dao.TagsDAO#getAllTagSets(java.lang.String, java.lang.String)
	 */
	@Override
	public TagSet[] getAllTagSets(String username, String password) {
		LOGGER.info("Entering getAllTagSet()");
		LOGGER.debug("username: " + username);
		TagSet[] tagSets = null;

		try {
			final GetAllTagSetRequestBodyType request = new GetAllTagSetRequestBodyType();
			request.setApplicationID(AppID);
			request.setLocale(Locale);
			request.setTagDisplayNameType("");
			request.setPassword(password);
			request.setUsername(username);
			
			Instant start = Instant.now();
			final GetAllTagSetResponseBodyType response = TagPortType.getAllTagSet(request);
			Instant end = Instant.now();
			LOGGER.debug("SOAP Request->Response - getAllTagSets() duration: " + Duration.between(start, end).toMillis() + "ms");

			LOGGER.debug("GetAllTagSetResponseBodyType: " + response);
			TagDescriptor[] tagDescriptors = response.getTagDescriptors();
			
			// Check for valid TagSets
			if ((tagDescriptors != null) && (tagDescriptors.length > 0)) {
				tagSets = new TagSet[tagDescriptors.length];
				// Loop through TagSets
				for (int x = 0; x < tagDescriptors.length; x++) {
					final TagSet tagSet = new TagSet();
					tagSet.setDisplayTagName(tagDescriptors[x].getDisplayTagName());
					tagSet.setParentTagName(tagDescriptors[x].getParentTagName());
					tagSet.setSystemTagName(tagDescriptors[x].getSystemTagName());
					tagSets[x] = tagSet;
				}
			}
		} catch (RemoteException re) {
			LOGGER.error("RemoteException", re);
		}
		LOGGER.debug("TagSet[]: " + tagSets);
		LOGGER.info("Exiting getAllTagSet()");
		return tagSets;
	}

	/*
	 * (non-Javadoc)
	 * @see com.verint.services.km.dao.TagsDAO#getTagSet(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Tag[] getTagSet(String username, String password, String tagset) {
		LOGGER.info("Entering getTagSet()");
		LOGGER.debug("username: " + username);
		LOGGER.debug("tagset: " + tagset);
		Tag[] tags = null;

		try {
			final GetTagSetRequestBodyType request = new GetTagSetRequestBodyType();
			request.setApplicationID(AppID);
			request.setLocale(Locale);
			request.setTagDisplayNameType("");
			request.setPassword(password);
			request.setUsername(username);
			request.setTagSetName(tagset);
			
			final GetTagSetResponseBodyType response = TagPortType.getTagSet(request);
			LOGGER.debug("GetTagSetResponseBodyType: " + response);
			TagDescriptor[] tagDescriptors = response.getTagDescriptors();

			// Check for valid TagSets
			if ((tagDescriptors != null) && (tagDescriptors.length > 0)) {
				tags = new Tag[tagDescriptors.length];
				// Loop through TagSets
				for (int x = 0; x < tagDescriptors.length; x++) {
					final Tag tag = new Tag();
					tag.setSystemTagDisplayName(tagDescriptors[x].getDisplayTagName());
					tag.setParentTagName(tagDescriptors[x].getParentTagName());
					tag.setSystemTagName(tagDescriptors[x].getSystemTagName());
					tags[x] = tag;
				}
			}
		} catch (RemoteException re) {
			LOGGER.error("RemoteException", re);
		}

		LOGGER.debug("Tags[]: " + tags);
		LOGGER.info("Exiting getTagSet()");
		return tags;
	}

	/*
	 * (non-Javadoc)
	 * @see com.verint.services.km.dao.TagsDAO#getTagSets(java.lang.String, java.lang.String[])
	 */
	@Override
	public Set<TagSet> getTagSets(String username, String password, String[] tagsets) {
		LOGGER.info("Entering getTagSets()");
		LOGGER.debug("username: " + username);
		LOGGER.debug("tagset: " + tagsets);
		List<TagSet> tagSets = new ArrayList<TagSet>();

		try {
			// Loop through the tagsets
			for (int i = 0; (tagsets != null) && (i < tagsets.length); i++) {
				final TagSet tagSet = new TagSet();
				tagSet.setSystemTagName(tagsets[i]);
				final GetTagSetRequestBodyType request = new GetTagSetRequestBodyType();
				request.setApplicationID(AppID);
				request.setLocale(Locale);
				request.setTagDisplayNameType("");
				request.setPassword(password);
				request.setUsername(username);
				request.setTagSetName(tagsets[i]);
				
				final GetTagSetResponseBodyType response = TagPortType.getTagSet(request);
				LOGGER.debug("GetTagSetResponseBodyType: " + response);
				TagDescriptor[] tagDescriptors = response.getTagDescriptors();
				List<Tag> tags = new ArrayList<Tag>();
				
				// Check for valid TagSets
				if ((tagDescriptors != null) && (tagDescriptors.length > 0)) {
					// Loop through TagSets
					for (int x = 0; x < tagDescriptors.length; x++) {
						final Tag tag = new Tag();
						tag.setSystemTagDisplayName(tagDescriptors[x].getDisplayTagName());
						tag.setParentTagName(tagDescriptors[x].getParentTagName());
						tag.setSystemTagName(tagDescriptors[x].getSystemTagName());
						tags.add(tag);
					}
				} else {
					// TODO
				}
				final Set<Tag> setTags = new LinkedHashSet<Tag>(tags);
				tagSet.setTags(setTags);
				tagSets.add(tagSet);
			}
		} catch (RemoteException re) {
			LOGGER.error("RemoteException", re);
		}

		LOGGER.debug("TagSets[]: " + tagSets);
		LOGGER.info("Exiting getTagSets()");
		return new LinkedHashSet<TagSet>(tagSets);
	}
}