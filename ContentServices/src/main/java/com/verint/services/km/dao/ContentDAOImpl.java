/**F
 * 
 */
package com.verint.services.km.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.kana.contactcentre.services.model.ContentV1Service_wsdl.ContentDetails;
import com.kana.contactcentre.services.model.ContentV1Service_wsdl.GetContentDetailsRequestBodyType;
import com.kana.contactcentre.services.model.ContentV1Service_wsdl.GetContentDetailsResponseBodyType;
import com.kana.contactcentre.services.model.ContentV1Service_wsdl.StringItem;
import com.verint.services.km.dao.parser.ElementParser;
import com.verint.services.km.errorhandling.AppErrorCodes;
import com.verint.services.km.errorhandling.AppErrorMessage;
import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.Attachment;
import com.verint.services.km.model.ContentRequest;
import com.verint.services.km.model.ContentResponse;
import com.verint.services.km.model.CustomField;
import com.verint.services.km.model.Translated;

/**
 * @author jmiller
 *
 */
@Repository
public class ContentDAOImpl extends BaseDAOImpl implements ContentDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(ContentDAOImpl.class);
	private static String ExternalUrl;
	
	static {
		try {
			final String OSName = System.getProperty("os.name");
			LOGGER.debug("OSName: " + OSName);
			String fileLocation = "/opt/kmservices/";
			if (OSName != null && OSName.length() > 0) {
				if (OSName.startsWith("Windows")) {
					fileLocation = "C:\\opt\\kmservices\\";
				}
			}
			// Get the properties
			final Properties prop = new Properties();
			LOGGER.debug("FileLocation: " + fileLocation + "externalFiles.properties");
	        final InputStream in = new FileInputStream(fileLocation + "externalFiles.properties");
	        prop.load(in);
	        in.close();
	        ExternalUrl = prop.getProperty("serverurl");	        
		} catch (FileNotFoundException fnfe) {
			LOGGER.error("FileNotFoundException", fnfe);
			System.exit(1);
		} catch (IOException ioe) {
			LOGGER.error("IOException", ioe);
			System.exit(1);
		} catch (Throwable t) {
			LOGGER.error("Throwable Exception", t);
			System.exit(1);
		}
	}
	
	/**
	 * 
	 */
	public ContentDAOImpl() {
		super();
		LOGGER.info("Entering ContentDAOImpl()");
		LOGGER.info("Exiting ContentDAOImpl()");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final ContentDAO contentDAO = new ContentDAOImpl();
		final ContentRequest contentRequest = new ContentRequest();
//		String body = "<img v:shapes=\"Picture_x0020_1\" height=\"449\" width=\"624\" style=\"font-size:10.0pt;line-height:115%;font-family:Verdana,sans-serif;\" src=\"/GTConnect/UnifiedAcceptor/FrameworkDesktop.Main?gtxResource=/asset/images/clip_image004347501196479703654_ServiceDesk_en-US.jpg&gtxResourceFileName=clip_image004347501196479703654_ServiceDesk_en-US.jpg&mode=download\"/><p>xyz</p><img v:shapes=\"Picture_x0020_1\" height=\"449\" width=\"624\" style=\"font-size:10.0pt;line-height:115%;font-family:Verdana,sans-serif;\" src=\"/GTConnect/UnifiedAcceptor/FrameworkDesktop.Main?gtxResource=/asset/images/clip_image004347501196479703654_ServiceDesk_en-US.jpg&gtxResourceFileName=clip_image004347501196479703654_ServiceDesk_en-US.jpg&mode=download\"/><div><p>oweek</p></div><img v:shapes=\"Picture_x0020_1\" height=\"449\" width=\"624\" style=\"font-size:10.0pt;line-height:115%;font-family:Verdana,sans-serif;\" src=\"/GTConnect/UnifiedAcceptor/FrameworkDesktop.Main?gtxResource=/asset/images/clip_image004347501196479703654_ServiceDesk_en-US.jpg&gtxResourceFileName=clip_image004347501196479703654_ServiceDesk_en-US.jpg&mode=download\"/>";
		//String body = "<body><title>New Process for Simple Address Changes</title><featured>true</featured><publicBody><p><br data-mce-bogus=\"1\"></p></publicBody><relatedContent></relatedContent><publicSectionContent></publicSectionContent><publicSegmentContent></publicSegmentContent><attachments></attachments><publishedDate>2016-04-22T23:45:46.227+00:00</publishedDate><expiredDate></expiredDate><lastModifiedBy>kmmanager</lastModifiedBy><owner>kmmanager</owner><weighting></weighting><status>PUBLISHED</status><tagsets><tagset> <systemTagSetName>system</systemTagSetName><displayTagSetName>Process</displayTagSetName><tag><systemTagName>kipviewworkitemsocial</systemTagName><displayTagName>View Work Item - Social</displayTagName></tag></tagset></tagsets></body>]]></body>";
//		String body = "<p><img src=\"/images/knowledgeImages/EditCustomer.png\" alt=\"\" data-mce-src=\"/images/knowledgeImages/EditCustomer.png\"></p>";
		String body = "<img src=\"{{{{GTSYSTEM_RESOURCE_PATH_PLACEHOLDER}}}}?gtxResource=/asset/images/sfrx_img6581411467656126519-898975550771652792_ServiceDesk_en-US.png&gtxResourceFileName=sfrx_img6581411467656126519-898975550771652792_ServiceDesk_en-US.png&mode=download\"/>";
		body = ((ContentDAOImpl)contentDAO).updateImg(body);
		System.out.println("Body: " + body);

		contentRequest.setContentId("OfZNOcPQLP0Hd3Kle7DUh5");
		contentRequest.setUsername("wjkm2");
		contentRequest.setPassword("wjkm2");
		try {
//			final ContentResponse contentResponse = contentDAO.getContent(contentRequest);
//			LOGGER.debug("ContentResponse: " + contentResponse);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.verint.services.km.dao.ContentDAO#getContent(com.verint.services.km.model.ContentRequest)
	 */
	@Override
	public ContentResponse getContent(ContentRequest contentRequest) throws RemoteException, IOException, SQLException, AppException {
		LOGGER.info("Entering getContent()");
		LOGGER.debug("ContentRequest: " + contentRequest);
		ContentResponse contentResponse = new ContentResponse();

		// Setup the request object to the SOAP call
		final GetContentDetailsRequestBodyType request = new GetContentDetailsRequestBodyType();
		request.setApplicationID(AppID);
		request.setContentID(contentRequest.getContentId());
		request.setLocale(Locale);
		request.setUsername(contentRequest.getUsername());
		request.setVersion("");
		request.setWorkflowState("");
		request.setPassword(contentRequest.getPassword());

		// Get the content details
		final GetContentDetailsResponseBodyType response = ContentPortType.getContentDetails(request);
		LOGGER.debug("response: " + response);

		// Get the response information
		if (response != null && response.getResponse() != null) {
			final ContentDetails contentDetails = response.getResponse();
			contentResponse.setAverageRating(new Double(contentDetails.getAverageRating()));
			contentResponse.setContentCategory(contentDetails.getContentCategory());
			contentResponse.setContentType(contentDetails.getContentType());
			contentResponse.setId(contentDetails.getId());
			contentResponse.setIsFeatured(contentDetails.isIsFeatured());
			contentResponse.setLanguage(contentDetails.getLanguage());
			contentResponse.setLastModifiedDate(contentDetails.getLastModifiedDate());
			contentResponse.setLocale(contentDetails.getLocale());
			contentResponse.setNumberOfRatings(contentDetails.getNumberOfRatings());
			contentResponse.setPublishedId(contentDetails.getPublishedId());
			contentResponse.setTitle(contentDetails.getTitle());

			// Translated text
			final StringItem[] items = contentDetails.getTranslatedTo();
			for (int x = 0; (items != null) && (x < items.length); x++) {
				final Translated translated = new Translated();
				translated.setLabel(items[x].getValue());
				translated.setText(items[x].getValue());
				contentResponse.addTranslatedTo(translated);
			}
			contentResponse.setVersion(contentDetails.getVersion());
			contentResponse.setViewContent(contentDetails.getViewCount());
			contentResponse.setDescription(contentDetails.getDescription());
			contentResponse.setRawBody(contentDetails.getBody());

			// Parse the XML from the Body
			if ("KnowledgeUploadED".equals(contentDetails.getContentType())) {
	    		final CustomField customField = new CustomField();
	    		customField.setName("File Information");
	    		String body = contentDetails.getBody();
	    		int index1 = body.indexOf("<fileDescription>");
	    		int index2 = body.indexOf("</fileDescription>");
	    		if (index1 != -1 && index2 != -1) {
	    			String data = body.substring(index1 + "<fileDescription>".length(), index2);
		    		index1 = body.indexOf("<file>");
		    		index2 = body.indexOf("</file>");
		    		if (index1 != -1 && index2 != -1) {
		    			String tempData = body.substring(index1 + "<file>".length(), index2);
		    			int index3 = tempData.indexOf("?gtxResource=");
		    			if (index3 != -1) {
		    				tempData = tempData.substring(index3 + "?gtxResource=".length());
		    				LOGGER.debug("tempData: " + tempData);
		    				tempData = tempData.replaceFirst("&gtxResourceFileName=", "?gtxResourceFileName=");
		    				LOGGER.debug("tempData: " + tempData);
		    				data = "<p>" + data + "</p>" + "<p><iframe src=\"" + ExternalUrl + tempData + "\" width=\"100%\" height=\"400px\"/></p>";
		    				LOGGER.debug("data: " + data);
		    	    		customField.setData(data);
		    	    		contentResponse.addCustomField(customField);
		    			}
		    		}
	    		}
			} else {
				// Parse the body
				parseBody(contentResponse, contentDetails.getBody());
			}

			// Setup Public Body
			contentResponse.setPublicBody(parseBodyData(contentResponse.getPublicBody()));
			// Setup Public Answer
			contentResponse.setPublicAnswer(parseBodyData(contentResponse.getPublicAnswer()));
			// Setup Private Body
			contentResponse.setPrivateBody(parseBodyData(contentResponse.getPrivateBody()));
			// Get Private Answer
			contentResponse.setPrivateAnswer(parseBodyData(contentResponse.getPrivateAnswer()));
			// Fix Dates
			contentResponse = setupPublishedDate(contentResponse);

			// Get URL for attachments
			final Set<Attachment> attachments = contentResponse.getAttachments();
			final Iterator<Attachment> itr = attachments.iterator();
			while (itr != null && itr.hasNext()) {
				final Attachment attachment = itr.next();
				String url = attachment.getUrl();
    			int index3 = url.indexOf("?gtxResource=");
    			if (index3 != -1) {
    				url = url.substring(index3 + "?gtxResource=".length());
    				LOGGER.debug("url: " + url);
    				url = url.replaceFirst("&gtxResourceFileName=", "?gtxResourceFileName=");
    				url = url.replaceFirst("\\\\", "/");
    				LOGGER.debug("url: " + url);
    				url = ExternalUrl + url;
    				attachment.setUrl(url);
    			}
			}

			// Reformat custom fields
			final Set<CustomField> customFields = contentResponse.getCustomFields();
			if (customFields != null && !customFields.isEmpty()) {
				final Iterator<CustomField> itrCustomField = customFields.iterator();
				while (itrCustomField != null && itrCustomField.hasNext()) {
					final CustomField cf = itrCustomField.next();
					final String data = cf.getData();
					if (data != null && data.length() > 0) {
						cf.setData(data.replace("></br>", "/>"));
						cf.setData((parseBodyData(cf.getData())));
					}
				}
			}
		} else {
			// We have a problem with the service
			throw new AppException(500, AppErrorCodes.CONTENT_RETRIEVAL_ERROR,
				AppErrorMessage.CONTENT_RETRIEVAL_ERROR);
		}
		LOGGER.debug("ContentResponse: " + contentResponse);
		LOGGER.info("Exiting getContent()");
		return contentResponse;
	}

	/**
	 * 
	 * @param privateData
	 * @return
	 */
	private String parseBodyData(String bodyData) {
		LOGGER.info("Entering parseBodyData()");
		LOGGER.debug("Incoming bodyData: " + bodyData);

		// Check for an empty body
		if (bodyData != null && bodyData.length() > 0) {
			bodyData = bodyData.replace("></br>", "/>");
			bodyData = updateAHref(bodyData);
			bodyData = updateImg(bodyData);
			final ElementParser ep = new ElementParser();
			bodyData = ep.parseInlineContent(bodyData);
		}
		LOGGER.debug("Outgoing privateData: " + bodyData);
		LOGGER.info("Exiting parseBodyData()");
		return bodyData;
	}

	/**
	 * 
	 * @param contentResponse
	 * @return
	 */
	private ContentResponse setupPublishedDate(ContentResponse contentResponse) {
		String pubDate = contentResponse.getPublishedDate();
		if (pubDate != null && pubDate.length() > 0) {
			int index = pubDate.indexOf("T");
			if (index != -1) {
				pubDate = pubDate.substring(0, index);
				contentResponse.setPublishedDate(pubDate);
			}
		} else {
			String modDate = contentResponse.getLastModifiedDate();
			if (modDate != null && modDate.length() > 0) {
				int index = modDate.indexOf("T");
				if (index != -1) {
					modDate = modDate.substring(0, index);
					contentResponse.setPublishedDate(modDate);
					contentResponse.setLastModifiedDate(modDate);
				}
			}
		}
		return contentResponse;
	}


	/**
	 * 
	 * @param body
	 * @return
	 */
	private String updateAHref(String body) {
		LOGGER.info("Entering updateAHref()");
		String finalBody = "";
		int ahref = body.indexOf(" href=\"");
		String bodyAfter = body;
		String bodyBefore = "";
		int gtxResource = 0;
		while (ahref != -1) {
			body = bodyAfter;
			gtxResource = body.indexOf("?gtxResource=");
			if (gtxResource != -1) {
				bodyBefore =  body.substring(0, ahref + " href=\"".length());
				bodyAfter = ExternalUrl + body.substring(gtxResource + "?gtxResource=".length());
				bodyAfter = bodyAfter.replaceFirst("&gtxResourceFileName=", "?gtxResourceFileName=");
				ahref = bodyAfter.indexOf(" href=\"");
				finalBody += bodyBefore;
			} else {
				// go on to the next one could be regular content from customer
				ahref = -1;
			}
		}
		finalBody += bodyAfter;
		LOGGER.info("finalBody: " + finalBody);
		LOGGER.info("existing updateAHref()");
		return finalBody;
	}
	
	/**
	 * 
	 * @param body
	 * @return
	 */
	public String updateImg(String body) {
		LOGGER.info("Entering updateImg()");
		// Example: <img src="/GTConnect/UnifiedAcceptor/FrameworkDesktop.Main?gtxResource=/asset/images/clip_image004347501196479703654_ServiceDesk_en-US.jpg&gtxResourceFileName=clip_image004347501196479703654_ServiceDesk_en-US.jpg&mode=download"/>
		String finalBody = body;
		int img = body.indexOf("<img");
		String bodyAfter = body;
		String bodyBefore = "";
		boolean foundImg = false;
		while (img != -1) {
			// We found an image lets parse it; find the gtxResource=
			String tempBody = bodyAfter.substring(img + "<img".length());
			bodyBefore = bodyBefore + bodyAfter.substring(0, img + "<img".length());
			int srcIndex = tempBody.indexOf("src=\"");
			int resourceIndex = tempBody.indexOf("gtxResource=");
			if (srcIndex != -1 && resourceIndex != -1) {
				bodyBefore = bodyBefore + tempBody.substring(0, srcIndex + "src=\"".length());
				bodyAfter = ExternalUrl + tempBody.substring(resourceIndex + "gtxResource=".length());
				finalBody = bodyBefore + bodyAfter;
				img = bodyAfter.indexOf("<img");
				foundImg = true;
			} else if (srcIndex != -1) {
				// This means we just have an image; we need to check if an image from our repository
				String tempCheck = tempBody.substring(srcIndex + "src=\"".length());
				if (tempCheck != null) {
					String firstChar = tempCheck.substring(0, 1);
					if (firstChar != null && firstChar.equals("/")) {
						// We found a match now let's put on the external url link in front of it
						bodyBefore = bodyBefore + tempBody.substring(0, srcIndex + "src=\"".length());
						bodyAfter = ExternalUrl + tempCheck;
						finalBody = bodyBefore + bodyAfter;
						img = bodyAfter.indexOf("<img");
						foundImg = true;						
					} else { 
						bodyAfter = tempBody;
						img = bodyAfter.indexOf("<img");
					}
				}
			} else {
				// Get out of this something is wrong
				break;
			}
		}
		// Found an image?
		if (foundImg) {
			finalBody = finalBody.replaceAll("&gtxResourceFileName", "?gtxResourceFileName");
		}

		LOGGER.debug("finalBody: " + finalBody);
		LOGGER.info("Exiting updateImg()");
		return finalBody;
	}

	/**
	 * 
	 * @param contentResponse
	 * @param bodyContent
	 */
	private void parseBody(ContentResponse contentResponse, String bodyContent) {
		LOGGER.info("Entering parseBody()");
		final ElementParser elementParser = new ElementParser();
		elementParser.parseBody(bodyContent, contentResponse);
		LOGGER.info("Entering parseBody()");
	}
}