package com.verint.services.km.service;

import java.io.StringReader;
import java.util.Iterator;
import java.util.Set;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.verint.services.km.model.TagResponse;
import com.verint.services.km.model.TagSet;
import com.verint.services.km.model.TagSetResponse;

/**
 * @author jmiller
 *
 */
public class TagsClient extends BaseClient {
	private static final Logger LOGGER = LoggerFactory.getLogger(TagsClient.class);

	/**
	 * 
	 */
	public TagsClient() {
		super();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			getAllTags();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.verint.services.km.service.BaseClient#doNothing()
	 */
	@Override
	public void doNothing() {
		
	}

	/**
	 * 
	 * @throws Exception
	 */
	private static void getAllTags() throws Exception {
		TagSetResponse tagSetResponse =  getTagSets();
		if (tagSetResponse != null) {
			Set<TagSet> tagSets = tagSetResponse.getTagSets();
			if (tagSets != null) {
				Iterator<TagSet> itr = tagSets.iterator();
				while (itr.hasNext()) {
					TagSet tagSet = itr.next();
					getTags(tagSet.getSystemTagName());
				}
			}
		}
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	private static TagSetResponse getTagSets() throws Exception {
		TagSetResponse tagSetResponse = null;
		jc = JAXBContext.newInstance(TagSetResponse.class);
		unmarshaller = jc.createUnmarshaller();
		unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
		unmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, false);
		jsonResponse = target.path("km").path("tags/gettagsets").request(MediaType.APPLICATION_JSON).header("Content-Type", MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();
		if (jsonResponse.getStatus() == 200) {
			LOGGER.debug("jsonResponse: " + jsonResponse);
/*			String retValue = jsonResponse.readEntity(String.class);
			LOGGER.debug("retValue: " + retValue);
			StreamSource json = new StreamSource(new StringReader(retValue));
			LOGGER.debug("StreamSource: " + json);
			JAXBElement<TagSetResponse> jaxbElement = unmarshaller.unmarshal(json, TagSetResponse.class);
			tagSetResponse = jaxbElement.getValue();
			if (tagSetResponse != null && tagSetResponse.getTagSets() != null) {
				LOGGER.info("TagSetResponse: " + tagSetResponse);
			} else {
				LOGGER.error("No response data avaialble");
			}
			jsonResponse.close();
*/
		} else if (jsonResponse.getStatus() == 404) {
//			jsonResponse.close();
			LOGGER.error(jsonResponse.toString());
		}

		return tagSetResponse;
	}

	/**
	 * 
	 * @param tagSet
	 * @return
	 * @throws Exception
	 */
	private static TagResponse getTags(String tagSet) throws Exception {
		TagResponse tagResponse = null;
		jc = JAXBContext.newInstance(TagResponse.class);
		unmarshaller = jc.createUnmarshaller();
		unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
		unmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, false);
		jsonResponse = target.path("km").path("tags/gettags/" + tagSet).request(MediaType.APPLICATION_JSON).header("Content-Type", MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();
		if (jsonResponse.getStatus() == 200) {
			LOGGER.debug("jsonResponse: " + jsonResponse);
/*			String retValue = jsonResponse.readEntity(String.class);
			LOGGER.debug("retValue: " + retValue);
			StreamSource json = new StreamSource(new StringReader(retValue));
			LOGGER.debug("StreamSource: " + json);
			JAXBElement<TagResponse> jaxbElement = unmarshaller.unmarshal(json, TagResponse.class);
			tagResponse = jaxbElement.getValue();
			if (tagResponse != null && tagResponse.getTags() != null) {
				LOGGER.info("TagResponse: " +tagResponse);
			} else {
				LOGGER.error("No response data avaialble");
			}
			jsonResponse.close();
*/
		} else if (jsonResponse.getStatus() == 404) {
//			jsonResponse.close();
			LOGGER.error(jsonResponse.toString());
		}


		return tagResponse;
	}
}