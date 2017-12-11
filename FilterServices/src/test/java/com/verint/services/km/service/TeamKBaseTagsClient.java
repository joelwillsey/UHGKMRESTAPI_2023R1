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

import com.verint.services.km.model.TeamKBaseTagsResponse;

public class TeamKBaseTagsClient extends BaseClient {
	private static final Logger LOGGER = LoggerFactory.getLogger(TeamKBaseTagsClient.class);

	public TeamKBaseTagsClient() {
		super();
	}
	
	public static void main(String[] args) {
		try {
			getKBaseTags();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void getKBaseTags() throws Exception {
		jc = JAXBContext.newInstance(TeamKBaseTagsResponse.class);
		unmarshaller = jc.createUnmarshaller();
		unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
		unmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, false);
        String bAuth = "kmagent" + ":" + "admin12345";
        String encodedCredentials = Base64.getEncoder().encodeToString(bAuth.getBytes("utf-8"));
		String basicAuth = " Basic " + encodedCredentials;
		System.out.println("BasicAuth: " + basicAuth);
		String token = "1343433343";

		jsonResponse = target.path("/filterservices/km/kbasetags").request(MediaType.APPLICATION_JSON).cookie("BasicAuth", basicAuth).cookie("CSRF-TOKEN", token).header("x-km-authorization", basicAuth).header("X-CSRF-TOKEN", token).header("Content-Type", MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();
		if (jsonResponse.getStatus() == 200) {
			LOGGER.debug("jsonResponse: " + jsonResponse);
			String retValue = jsonResponse.readEntity(String.class);
			LOGGER.debug("retValue: " + retValue);
			StreamSource json = new StreamSource(new StringReader(retValue));
			LOGGER.debug("StreamSource: " + json);
			JAXBElement<TeamKBaseTagsResponse> jaxbElement = unmarshaller.unmarshal(json, TeamKBaseTagsResponse.class);
			TeamKBaseTagsResponse kbaseTagResponse = null;
			kbaseTagResponse = jaxbElement.getValue();
			if (kbaseTagResponse != null && kbaseTagResponse.getTags() != null) {
				LOGGER.info("kbaseTagResponse: " + kbaseTagResponse);
			} else {
				LOGGER.error("No response data avaialble");
			}
			jsonResponse.close();
		} else if (jsonResponse.getStatus() == 404) {
//			jsonResponse.close();
			LOGGER.error(jsonResponse.toString());
		}
	}
	
	@Override
	public void doNothing() {
		// TODO Auto-generated method stub

	}

}
