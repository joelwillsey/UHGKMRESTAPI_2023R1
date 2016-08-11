package com.verint.services.km.service;

import java.io.StringReader;
import java.util.Base64;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.verint.services.km.model.CrossTagResponse;

/**
 * @author jmiller
 *
 */
public class CrossTagsClient extends BaseClient {
	private static final Logger LOGGER = LoggerFactory.getLogger(CrossTagsClient.class);

	/**
	 * 
	 */
	public CrossTagsClient() {
		super();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			getCrossTags();
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
	 * @return
	 * @throws Exception
	 */
	private static void getCrossTags() throws Exception {
		jc = JAXBContext.newInstance(CrossTagResponse.class);
		unmarshaller = jc.createUnmarshaller();
		unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
		unmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, false);
        String bAuth = "kmagent" + ":" + "admin12345";
        String encodedCredentials = Base64.getEncoder().encodeToString(bAuth.getBytes("utf-8"));
		String basicAuth = " Basic " + encodedCredentials;
		System.out.println("BasicAuth: " + basicAuth);
		String token = "1343433343";

		jsonResponse = target.path("/filterservices/km/crosstags").queryParam("sourcetag", "langset_english").queryParam("targettagset", "topic").request(MediaType.APPLICATION_JSON).cookie("BasicAuth", basicAuth).cookie("CSRF-TOKEN", token).header("x-km-authorization", basicAuth).header("X-CSRF-TOKEN", token).header("Content-Type", MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();
		if (jsonResponse.getStatus() == 200) {
			LOGGER.debug("jsonResponse: " + jsonResponse);
			String retValue = jsonResponse.readEntity(String.class);
			LOGGER.debug("retValue: " + retValue);
			StreamSource json = new StreamSource(new StringReader(retValue));
			LOGGER.debug("StreamSource: " + json);
			JAXBElement<CrossTagResponse> jaxbElement = unmarshaller.unmarshal(json, CrossTagResponse.class);
			CrossTagResponse xtagResponse = null;
			xtagResponse = jaxbElement.getValue();
			if (xtagResponse != null && xtagResponse.getCrossTags() != null) {
				LOGGER.info("CrossTagResponse: " + xtagResponse);
			} else {
				LOGGER.error("No response data avaialble");
			}
			jsonResponse.close();
		} else if (jsonResponse.getStatus() == 404) {
//			jsonResponse.close();
			LOGGER.error(jsonResponse.toString());
		}
	}
}