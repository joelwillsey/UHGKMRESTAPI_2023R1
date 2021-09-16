/**F
 * 
 */
package com.verint.services.km.dao;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.regex.Pattern;
import java.math.BigInteger;

import com.verint.services.km.dao.parser.RestParser;
import com.verint.services.km.model.rest.Annotation;
import com.verint.services.km.model.rest.AnnotationSelector;
import com.verint.services.km.model.rest.RestContentResponse;
import com.verint.services.km.model.rest.RestTag;
import com.verint.services.km.util.RestUtil;
import com.verint.services.km.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;

import com.kana.contactcentre.services.model.ContentV1Service_wsdl.GetContentDetailsRequestBodyType;
import com.kana.contactcentre.services.model.ContentV1Service_wsdl.GetContentVersionsRequestBodyType;
import com.kana.contactcentre.services.model.ContentV1Service_wsdl.GetContentVersionsResponseBodyType;
import com.verint.services.km.dao.parser.ElementParser;
import com.verint.services.km.errorhandling.AppErrorCodes;
import com.verint.services.km.errorhandling.AppErrorMessage;
import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.util.ConfigInfo;

/**
 * @author jmiller
 *
 */
@Repository
public class ContentDAOImpl extends BaseDAOImpl implements ContentDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(ContentDAOImpl.class);
	private static String ExternalUrl;
	private static String REST_CONTENT_URL;
	private static boolean ConvertTagUrlToHttp;

	static {
		try {
			// Get the properties
			ConfigInfo kmConfiguration = new ConfigInfo();
			ExternalUrl = kmConfiguration.getStaticcontentServerurl();
			REST_CONTENT_URL = kmConfiguration.getRestKmContentService();
			ConvertTagUrlToHttp = kmConfiguration.getConvertTagServiceToHTTP();
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
		final ContentVersionRequest contentVersionRequest = new ContentVersionRequest();
		// String body = "<img v:shapes=\"Picture_x0020_1\" height=\"449\"
		// width=\"624\"
		// style=\"font-size:10.0pt;line-height:115%;font-family:Verdana,sans-serif;\"
		// src=\"/GTConnect/UnifiedAcceptor/FrameworkDesktop.Main?gtxResource=/asset/images/clip_image004347501196479703654_ServiceDesk_en-US.jpg&gtxResourceFileName=clip_image004347501196479703654_ServiceDesk_en-US.jpg&mode=download\"/><p>xyz</p><img
		// v:shapes=\"Picture_x0020_1\" height=\"449\" width=\"624\"
		// style=\"font-size:10.0pt;line-height:115%;font-family:Verdana,sans-serif;\"
		// src=\"/GTConnect/UnifiedAcceptor/FrameworkDesktop.Main?gtxResource=/asset/images/clip_image004347501196479703654_ServiceDesk_en-US.jpg&gtxResourceFileName=clip_image004347501196479703654_ServiceDesk_en-US.jpg&mode=download\"/><div><p>oweek</p></div><img
		// v:shapes=\"Picture_x0020_1\" height=\"449\" width=\"624\"
		// style=\"font-size:10.0pt;line-height:115%;font-family:Verdana,sans-serif;\"
		// src=\"/GTConnect/UnifiedAcceptor/FrameworkDesktop.Main?gtxResource=/asset/images/clip_image004347501196479703654_ServiceDesk_en-US.jpg&gtxResourceFileName=clip_image004347501196479703654_ServiceDesk_en-US.jpg&mode=download\"/>";
		// String body = "<body><title>New Process for Simple Address
		// Changes</title><featured>true</featured><publicBody><p><br
		// data-mce-bogus=\"1\"></p></publicBody><relatedContent></relatedContent><publicSectionContent></publicSectionContent><publicSegmentContent></publicSegmentContent><attachments></attachments><publishedDate>2016-04-22T23:45:46.227+00:00</publishedDate><expiredDate></expiredDate><lastModifiedBy>kmmanager</lastModifiedBy><owner>kmmanager</owner><weighting></weighting><status>PUBLISHED</status><tagsets><tagset>
		// <systemTagSetName>system</systemTagSetName><displayTagSetName>Process</displayTagSetName><tag><systemTagName>kipviewworkitemsocial</systemTagName><displayTagName>View
		// Work Item -
		// Social</displayTagName></tag></tagset></tagsets></body>]]></body>";
		// String body = "<p><img
		// src=\"/images/knowledgeImages/EditCustomer.png\" alt=\"\"
		// data-mce-src=\"/images/knowledgeImages/EditCustomer.png\"></p>";
		String body = "<img src=\"{{{{GTSYSTEM_RESOURCE_PATH_PLACEHOLDER}}}}?gtxResource=/asset/images/sfrx_img6581411467656126519-898975550771652792_ServiceDesk_en-US.png&gtxResourceFileName=sfrx_img6581411467656126519-898975550771652792_ServiceDesk_en-US.png&mode=download\"/>";
		body = ((ContentDAOImpl) contentDAO).updateImg(body);
		System.out.println("Body: " + body);

		contentRequest.setContentId("OfZNOcPQLP0Hd3Kle7DUh5");
		contentRequest.setUsername("wjkm2");
		contentRequest.setPassword("wjkm2");
		try {
			// final ContentResponse contentResponse =
			// contentDAO.getContent(contentRequest);
			// LOGGER.debug("ContentResponse: " + contentResponse);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.verint.services.km.dao.ContentDAO#getContent(com.verint.services.km.
	 * model.ContentRequest)
	 */
	
	@Override
	public  ContentVersionResponse getContentVersion(ContentVersionRequest contentVersionRequest) 
			throws AppException, RemoteException {
		LOGGER.info("Entering getContentVersion()");
		ContentVersionResponse contentVersionResponse = new ContentVersionResponse();
		final GetContentVersionsRequestBodyType versionRequest = new GetContentVersionsRequestBodyType();
		versionRequest.setApplicationID(AppID);
		versionRequest.setContentID(contentVersionRequest.getContentId());
		versionRequest.setLocale(Locale);
		versionRequest.setPageSize(BigInteger.valueOf(10));
		versionRequest.setStartRow(BigInteger.valueOf(0));
		versionRequest.setSortAscending(contentVersionRequest.getSortAscending());
		versionRequest.setShowMinorVersions(contentVersionRequest.getShowMinorVersions());
		Instant start = Instant.now();
		final GetContentVersionsResponseBodyType versionResponse = ContentPortType.getContentVersions(versionRequest);	
		Instant end = Instant.now();
		LOGGER.debug("SERVICE_CALL_PERFORMANCE(" + versionRequest.getUsername() + ") - getContentVersions() duration: "
				+ Duration.between(start, end).toMillis() + "ms");
		LOGGER.debug("response: " + versionResponse);
		
		if (versionResponse != null) {
			
		contentVersionResponse.setResultSet(versionResponse.getResponse());
		contentVersionResponse.setErrorList(versionResponse.getErrorList());
		}else {
			// We have a problem with the service
			throw new AppException(500, AppErrorCodes.CONTENT_RETRIEVAL_ERROR, AppErrorMessage.CONTENT_RETRIEVAL_ERROR);
		}
		LOGGER.debug("ContentVersionResponse: " + contentVersionResponse);
		LOGGER.info("Exiting getContentVersion()");

		return contentVersionResponse;
	}

	@Override
	public ContentResponse getContent(ContentRequest contentRequest)
			throws RemoteException, IOException, SQLException, AppException {
		LOGGER.info("Entering getContent()");
		LOGGER.debug("ContentRequest: " + contentRequest);
		ContentResponse contentResponse = new ContentResponse();


		// Setup the request object to the SOAP call
		final GetContentDetailsRequestBodyType request = new GetContentDetailsRequestBodyType();
		request.setApplicationID(AppID);
		request.setContentID(contentRequest.getContentId());
		request.setLocale(Locale);
		request.setUsername(contentRequest.getUsername());
		request.setVersion(contentRequest.getVersion());
		request.setWorkflowState(contentRequest.getWorkflowState());
		request.setPassword(contentRequest.getPassword());
		request.setVerbose(false);
		request.setConvertFieldsToMap(false);

		Instant startWrap = Instant.now();
		
		// Get the content details

		// For Json -> Json requests we could use km-search-service vkm:url instead of creating the url ourselves
		String contentType = "vkm:AuthoredContent";
		if ("Spidered".equals(contentRequest.getContentType()) ||
			"Unstructured".equals(contentRequest.getContentType())) {
			contentType = "vkm:SpideredContent";
		}
		
		/*
		 * String contentUrl = REST_CONTENT_URL + "/default/content/" + contentType +
		 * "/" + URLEncoder.encode(contentRequest.getContentId(),
		 * StandardCharsets.UTF_8.toString()) + "/" + Locale +
		 * "?version="+contentRequest.getVersion();
		 */
		
		String contentUrl = REST_CONTENT_URL + "/default/content/" + contentType + "/" +
				URLEncoder.encode(contentRequest.getContentId(), StandardCharsets.UTF_8.toString()) +
				"/" + Locale;

		//add version parameter if it's there
		if (contentRequest.getVersion().length() >0) {
			contentUrl = contentUrl + "?version="+contentRequest.getVersion();
		}
		
		
		Instant start = Instant.now();
		RestContentResponse contentJsonResponse = RestUtil.getAndDeserialize(contentUrl,null,
				HttpMethod.GET, RestContentResponse.class, contentRequest.getOidcToken(), null, true);

//				final GetContentDetailsResponseBodyType soapResponse = ContentPortType.getContentDetails(request);
		Instant end = Instant.now();
		LOGGER.debug("SERVICE_CALL_PERFORMANCE(" + contentRequest.getUsername() + ") - getContent - km-content-service duration: "
				+ Duration.between(start, end).toMillis() + "ms");
		LOGGER.debug("response: " + contentJsonResponse.toString());

		
		//Setup Tag calling
		List<RestTag> jsonRestTagList = new ArrayList<>();
		for (Annotation anno : contentJsonResponse.getAnnotations()) {
			if (anno.getMotivation().equals("oa:tagging")) {
				Instant startTag = Instant.now();
				String restTagUrl = anno.getBody().getId();
				if (ConvertTagUrlToHttp) {
					restTagUrl = restTagUrl.replaceFirst("https", "http");
					restTagUrl = restTagUrl.replaceFirst(":443", ":80");
					if (!anno.getBody().getId().equals(restTagUrl)) {
						LOGGER.debug("Forced Tag URL to HTTP: " + anno.getBody().getId()+  " -> " + restTagUrl);
					}
				}
				//RestTag restTagJsonResponse = RestUtil.getAndDeserialize(anno.getBody().getId(),
				//		null, HttpMethod.GET, RestTag.class, contentRequest.getOidcToken(), null, true);
				RestTag restTagJsonResponse = RestUtil.getAndDeserialize(restTagUrl,
						null, HttpMethod.GET, RestTag.class, contentRequest.getOidcToken(), null, true);
				Instant endTag = Instant.now();
				int lastSlash = anno.getBody().getId().toString().lastIndexOf("/");
				String tagSystemName;
				if (lastSlash != -1){
					tagSystemName = anno.getBody().getId().toString().substring(lastSlash + 1);
				} else {
					tagSystemName =anno.getBody().getId().toString();
				}
				
				LOGGER.debug("SERVICE_CALL_PERFORMANCE(" + contentRequest.getUsername() + ") - getContent - oa:tagging ("+ tagSystemName + "): " + Duration.between(startTag, endTag).toMillis() + "ms");
				jsonRestTagList.add(restTagJsonResponse);
			}
		}
		
		//Start timing for parsing
		start = Instant.now();
		
		//TODO preference would be to get all tags and cache to populate the tags on contentresponse
		for (RestTag jt : jsonRestTagList) {
			String tagset = jt.getStart().getIdentifier();
			TagSet foundTs = null;

			for (TagSet ts : contentResponse.getTagSets()) {
				if (ts.getSystemTagName().equals(tagset)) {
					foundTs = ts;
					break;
				}
			}
			if (foundTs == null) {
				foundTs = new TagSet();
				foundTs.setSystemTagName(jt.getStart().getIdentifier());
				foundTs.setDisplayTagName(jt.getStart().getName());
				contentResponse.addTagSet(foundTs);
			}
			Tag tag = new Tag();
			tag.setSystemTagName(jt.getIdentifier());
			tag.setSystemTagDisplayName(jt.getName());
			tag.setParentTagName(jt.getUp().getName());
			tag.setSystemTagSetDisplayName(jt.getStart().getName());
			tag.setSystemTagSetName(jt.getStart().getIdentifier());
			foundTs.addTag(tag);
		}

		//Setup related content
		RelatedContent relatedContent = contentResponse.getRelatedContent();
		List<String[]> textReplaceList = new ArrayList<>();
		List<ContentEntry> publicSectionContents = new ArrayList<>();
		List<ContentEntry> privateSectionContents = new ArrayList<>();
		for (Annotation anno : contentJsonResponse.getAnnotations()) {
			if ("oa:linking".equals(anno.getMotivation()) &&
					anno.getTarget() != null &&
					anno.getTarget().getType() != null &&
					"oa:SpecificResource".equals(anno.getTarget().getType().get(0)) &&
					anno.getTarget().getSelector() != null &&
					!anno.getTarget().getSelector().isEmpty()) {
				int replaceStart = 0;
				int replaceEnd = 0;
				String path = null;
				for (AnnotationSelector selector : anno.getTarget().getSelector()) {
					if (selector.getType() != null) {
						if ("vkm:PropertyPathSelector".equals(selector.getType().get(0))) {
							path = selector.getPath().get(0).getId();
						} else if ("oa:TextPositionSelector".equals(selector.getType().get(0))) {
							replaceStart = selector.getStart();
							replaceEnd = selector.getEnd();
						}
					}
				}

				if (replaceEnd != 0 && path != null) {
					textReplaceList.add(new String[]{replaceStart+"",
							replaceEnd+"",
							path,
							RestUtil.getContentIdFromUrl(anno.getBody().getId(), anno.getBody().getType().get(0))});

				}
			} else if ("oa:linking".equals(anno.getMotivation()) &&
					anno.getTarget() != null &&
					anno.getTarget().getSelector() != null &&
					anno.getTarget().getSelector().size() == 1 &&
					anno.getTarget().getSelector().get(0).getType() != null &&
					"vkm:PropertyPathSelector".equals(anno.getTarget().getSelector().get(0).getType().get(0))) {
				String replaceLocation = anno.getTarget().getSelector().get(0).getPath().get(0).getId();
				String contentId = RestUtil.getContentIdFromUrl(anno.getBody().getId(), anno.getBody().getType().get(0));
				String contentVersion = RestUtil.getContentVersionFromUrl(anno.getBody().getId());
				ContentEntry contentEntry = new ContentEntry();
				contentEntry.setTitle(anno.getBody().getName());
				contentEntry.setId(contentId);
				contentEntry.setType(RestUtil.convertContentRestType(anno.getBody().getType().get(0)));
				contentEntry.setVersion(contentVersion);
				if ("vkm:articleBody".equals(replaceLocation)) {
					publicSectionContents.add(contentEntry);
//					contentJsonResponse.setArticleBody(addReuseContentLink(contentJsonResponse.getArticleBody(),
//							anno.getBody().getName(),
//							contentId));
				} else if ("vkm:privateBody".equals(replaceLocation)) {
					privateSectionContents.add(contentEntry);
//					contentJsonResponse.setPrivateBody(addReuseContentLink(contentJsonResponse.getPrivateBody(),
//							anno.getBody().getName(),
//							contentId));
				}
			} else if (anno.getMotivation().equals("oa:linking") && anno.getBody() != null && anno.getBody().getName() != null) {
				ContentEntry contentEntry = new ContentEntry();
				contentEntry.setType(RestUtil.convertContentRestType(anno.getBody().getType().get(0)));
				contentEntry.setTitle(anno.getBody().getName());
				contentEntry.setId(RestUtil.getContentIdFromUrl(anno.getBody().getId(), anno.getBody().getType().get(0)));
				contentEntry.setVersion(RestUtil.getContentVersionFromUrl(anno.getBody().getId()));
				relatedContent.addContentEntries(contentEntry);
			}
		}
		contentResponse.setPublicSectionContent(new LinkedHashSet<>(publicSectionContents));
		contentResponse.setPrivateSectionContent(new LinkedHashSet<>(privateSectionContents));
		textReplaceList.sort(Comparator.comparingInt(o -> Integer.parseInt(o[0])));
		Collections.reverse(textReplaceList);
		for (String[] textReplace : textReplaceList) {
			if ("vkm:articleBody".equals(textReplace[2])) {
				contentJsonResponse.setArticleBody(replaceEmbeddedContentLink(contentJsonResponse.getArticleBody(),
						Integer.parseInt(textReplace[0]), Integer.parseInt(textReplace[1]), textReplace[3]));
			} else if ("vkm:privateBody".equals(textReplace[2])) {
				contentJsonResponse.setPrivateBody(replaceEmbeddedContentLink(contentJsonResponse.getPrivateBody(),
						Integer.parseInt(textReplace[0]), Integer.parseInt(textReplace[1]), textReplace[3]));
			}
		}

		String includeJSFile = "\n <script type=\"text/javascript\" src=\"" + ExternalUrl + "/VEM_showHideDiv.js\" ></script>";
		ScriptSrc showHideDivFile = new ScriptSrc();
		showHideDivFile.setAsync("true");
		showHideDivFile.setSrc(ExternalUrl + "/VEM_showHideDiv.js");
		showHideDivFile.setType("text/javascript");
		contentResponse.addExternalSrcFiles(showHideDivFile);
		RestParser restParser = new RestParser();
		restParser.parseRestContent(contentResponse, contentJsonResponse, ExternalUrl);
			// Translated text
//			contentResponse.setDescription(contentDetails.getDescription());
//			contentResponse.setRawBody(contentDetails.getBody());
//			contentResponse.setIsDeleted(contentDetails.isIsDeleted());
//			contentResponse.setIsMigratable(contentDetails.isIsMigratable());
//			contentResponse.setMustRead(contentDetails.isMustRead());

//			// Fix Dates
//			contentResponse = setupPublishedDate(contentResponse);
			
			
			Set<ExternalContent> externalContents = contentResponse.getRelatedContent().getExternalContents();
			Set<ExternalContent> cleanExternalContent = new HashSet<ExternalContent>();

			for (ExternalContent externalContent : externalContents) {
				LOGGER.debug("externalContent.getName(): " + externalContent.getName());				
				LOGGER.debug("externalContent.getUrl(): " + externalContent.getUrl());
				
				if(externalLinkNeedsFixing(externalContent.getUrl())){
					externalContent.setName(updateUploadedNameForRelated(externalContent.getName()));
					externalContent.setUrl(updateUploadedURLForRelated(externalContent.getUrl()));
				}				
				cleanExternalContent.add(externalContent);
			}
			contentResponse.getRelatedContent().setExternalContents(cleanExternalContent);

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

//		} else {
//			// We have a problem with the service
//			throw new AppException(500, AppErrorCodes.CONTENT_RETRIEVAL_ERROR, AppErrorMessage.CONTENT_RETRIEVAL_ERROR);
//		}
		end = Instant.now();
		LOGGER.debug("SERVICE_CALL_PERFORMANCE(" + contentRequest.getUsername() + ") - getContent - Parsing content: " + Duration.between(start, end).toMillis() + "ms");
		Instant endWrap = Instant.now();
		LOGGER.debug("SERVICE_CALL_PERFORMANCE(" + contentRequest.getUsername() + ") - getContent - Total duration: " + Duration.between(startWrap, endWrap).toMillis() + "ms");
		LOGGER.debug("ContentResponse: " + contentResponse);
		LOGGER.info("Exiting getContent()");
		return contentResponse;
	}

	/**
	 * 
	 * @param bodyData
	 * @return
	 */
	private String parseBodyData(String bodyData) {
		LOGGER.info("Entering parseBodyData()");
		LOGGER.debug("Incoming bodyData: " + bodyData);

		// Check for an empty body
		if (bodyData != null && bodyData.length() > 0) {
			bodyData = bodyData.replace("></br>", "/>");
			bodyData = updateAHref(bodyData);
			bodyData = updateAHrefMalformed(bodyData);
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
	/**
	 * private String updateAHref(String body) { LOGGER.info("Entering
	 * updateAHref()"); String finalBody = ""; int ahref = body.indexOf("
	 * href=\""); String bodyAfter = body; String bodyBefore = ""; int
	 * gtxResource = 0; while (ahref != -1) { body = bodyAfter; gtxResource =
	 * body.indexOf("?gtxResource="); if (gtxResource != -1) { bodyBefore =
	 * body.substring(0, ahref + " href=\"".length()); bodyAfter = ExternalUrl +
	 * body.substring(gtxResource + "?gtxResource=".length()); bodyAfter =
	 * bodyAfter.replaceFirst("&gtxResourceFileName=", "?gtxResourceFileName=");
	 * ahref = bodyAfter.indexOf(" href=\""); finalBody += bodyBefore; } else {
	 * // go on to the next one could be regular content from customer ahref =
	 * -1; } } finalBody += bodyAfter; LOGGER.info("finalBody: " + finalBody);
	 * LOGGER.info("existing updateAHref()"); return finalBody; }
	 **/

	private String updateAHref(String body) {
		LOGGER.info("Entering updateAHref()");
		String finalBody = body;
		int searchIndex = 0;
		// Find the first href="
		int ahrefStart = body.indexOf(" href=\"");
		while (ahrefStart != -1) {
			searchIndex = ahrefStart + " href=\"".length();
			int aherfEnd = body.indexOf("\"", ahrefStart + " href=\"".length());
			if (aherfEnd != -1) {
				// we found the end of the ahref
				String hrefSource = body.substring(ahrefStart, aherfEnd + "\"".length());
				LOGGER.debug("--- Found href attribute: " + hrefSource);
				int gtxResource = 0;
				gtxResource = hrefSource.indexOf("?gtxResource=");
				String newAhref = "";
				if (gtxResource != -1) {
					finalBody = finalBody.substring(0, ahrefStart);
					// we found a gtxResource Link, need to change the link
					newAhref = ExternalUrl + hrefSource.substring(gtxResource + "?gtxResource=".length());
					newAhref = newAhref.replaceFirst("&gtxResourceFileName=", "?gtxResourceFileName=");
					LOGGER.debug("--- Transformed href attribute from " + hrefSource + " to " + newAhref);
					finalBody = finalBody + newAhref + body.substring(aherfEnd + "\"".length());
				}
			} else {
				LOGGER.error(
						"Parse error on search for href string, unable to find the \" after  href=\", started search at index "
								+ ahrefStart);
			}
			ahrefStart = body.indexOf(" href=\"", searchIndex);
		}
		LOGGER.info("finalBody: " + finalBody);
		LOGGER.info("exiting updateAHref()");
		return finalBody;
	}

	boolean externalLinkNeedsFixing(String url){
	
		boolean result = false;
		
		int beginGtxResource = url.indexOf("gtxResource=");
		if (beginGtxResource != -1){
			result = true;
		}
		
		return result;
	}
	
	
	private String updateUploadedNameForRelated(String name) {

		String newName = name;

		int lastSlash = name.lastIndexOf("/");
		if (lastSlash != -1) {
			newName = name.substring(lastSlash + 1);
			LOGGER.debug("fixed Name: " + newName);
		}

		return newName;
	}

	private String updateUploadedURLForRelated(String url) {
		LOGGER.info("Fixing the External URL");
		
		//fix filename
		String finalURL = url;	
		int beginGtxResource = finalURL.indexOf("gtxResource=");
		if (beginGtxResource != -1){
			int beginGtxResourceFileName = finalURL.indexOf("&gtxResourceFileName");
			if (beginGtxResourceFileName != -1){
				finalURL = ExternalUrl + "/" + finalURL.substring(finalURL.indexOf("gtxResource=") + "gtxResource=".length(), finalURL.indexOf("&gtxResourceFileName"));
			} else {
				LOGGER.debug("Invalid External URL found: " + finalURL);
			}
		} else {
			LOGGER.debug("No External (gtxResources=) URL found, url = " + finalURL);
		}
		

		return finalURL;
	}

	private String updateAHrefMalformed(String body) {
		// This is really stupid but the content call is returning a link like
		// this
		// <a href='null?gtxResource=/KM/files/uploaded/PNP_UHCG Shared Savings
		// v1_UHG_en-US.doc&gtxResourceFileName=/KM/files/uploaded/PNP_UHCG
		// Shared Savings v1_UHG.doc&mode=download'>/KM/files/uploaded/PNP_UHCG
		// Shared Savings v1_UHG.doc</a>
		// So now I have to handle this malformed link and fix it :(

		LOGGER.info("Entering updateAHrefMalformed()");
		String finalBody = body;
		int searchIndex = 0;
		int finalbodyIndex = 0;
		// Find the first href="
		int ahrefStart = body.indexOf("<a href='null");
		int finalBodyahrefStart = body.indexOf("<a href='null");
		;
		while (ahrefStart != -1) {
			searchIndex = ahrefStart + "<a href='null".length();
			int aherfEnd = body.indexOf("</a>", ahrefStart + "<a href='null".length());
			if (aherfEnd != -1) {
				// we found the end of the ahref
				String hrefSource = body.substring(ahrefStart, aherfEnd + "</a>".length());
				LOGGER.debug("--- Found malformed href attribute: " + hrefSource);
				int gtxResource = 0;
				gtxResource = hrefSource.indexOf("?gtxResource=");
				String newAhref = "";
				if (gtxResource != -1) {
					finalBody = finalBody.substring(0, finalBodyahrefStart);
					// LOGGER.debug("finalBody Front: [" + finalBody + "]");
					// we found a gtxResource Link, need to change the link
					newAhref = "<a href=\"" + ExternalUrl
							+ hrefSource.substring(gtxResource + "?gtxResource=".length());
					newAhref = newAhref.replaceFirst("&gtxResourceFileName=", "?gtxResourceFileName=");
					newAhref = newAhref.replaceFirst("&mode=download", "?mode=download");
					newAhref = newAhref.replaceFirst("'>", "\">");
					// Now lets fix the title
					int startOfFileName = newAhref.indexOf("?gtxResourceFileName=") + "?gtxResourceFileName=".length();
					if (startOfFileName != -1) {
						int endOfFileName = newAhref.indexOf("?mode=download", startOfFileName);
						if (endOfFileName != -1) {
							String filePath = newAhref.substring(startOfFileName, endOfFileName);
							LOGGER.debug("filePath: " + filePath);
							int lastSlash = filePath.lastIndexOf('/');
							if (lastSlash != -1) {
								if (lastSlash + 1 <= filePath.length()) {
									String fileName = filePath.substring(lastSlash + 1);
									//need this to handle the windows /  ie. /fileStorage/KM/uploaded\EM_15.1_FP1_Release_Pack.pdf
									int lastForwardSlash = fileName.lastIndexOf("\\");
									fileName = fileName.substring(lastForwardSlash + 1);
									LOGGER.debug("fileName: " + fileName);
									newAhref = newAhref.replaceAll(Pattern.quote(filePath), fileName);
								}
							}
						}
					}
					LOGGER.debug("--- Transformed malformed href attribute from " + hrefSource + " to " + newAhref);
					// This is the index of where the finalbody is complete
					finalbodyIndex = finalBody.length() - 1 + newAhref.length();
					finalBody = finalBody + newAhref + body.substring(aherfEnd + "</a>".length());
					// LOGGER.debug("Body: [" + body.substring(aherfEnd +
					// "</a>".length()) + "]");
					// LOGGER.debug("finalBody: " + finalBody);
				}
			} else {
				LOGGER.error(
						"Parse error on search for href string, unable to find the </a> after  href='null, started search at index "
								+ ahrefStart);
			}
			ahrefStart = body.indexOf("<a href='null", searchIndex);
			finalBodyahrefStart = finalBody.indexOf("<a href='null", finalbodyIndex);
		}
		LOGGER.info("finalBody: " + finalBody);
		LOGGER.info("exiting updateAHrefMalformed()");
		return finalBody;
	}

	/**
	 * 
	 * @param body
	 * @return
	 */
	public String updateImg(String body) {
		LOGGER.info("Entering updateImg()");
		// Example: <img
		// src="/GTConnect/UnifiedAcceptor/FrameworkDesktop.Main?gtxResource=/asset/images/clip_image004347501196479703654_ServiceDesk_en-US.jpg&gtxResourceFileName=clip_image004347501196479703654_ServiceDesk_en-US.jpg&mode=download"/>
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

			// indexes the popup image source in order to check that we can or
			// cant change the image
			int srcPopupIndex = tempBody.indexOf("src=\"images/ReadLaterGray16x16.png\"");
			int resourceIndex = tempBody.indexOf("gtxResource=");
			if (srcIndex != -1 && resourceIndex != -1) {
				bodyBefore = bodyBefore + tempBody.substring(0, srcIndex + "src=\"".length());
				bodyAfter = ExternalUrl + tempBody.substring(resourceIndex + "gtxResource=".length());

				// makes sure that the popup link doesn't get messed up
				if (srcIndex != srcPopupIndex) {
					finalBody = bodyBefore + bodyAfter;
				}
				img = bodyAfter.indexOf("<img");
				foundImg = true;
			} else if (srcIndex != -1) {
				// This means we just have an image; we need to check if an
				// image from our repository
				String tempCheck = tempBody.substring(srcIndex + "src=\"".length());
				if (tempCheck != null) {
					String firstChar = tempCheck.substring(0, 1);
					if (firstChar != null && firstChar.equals("/")) {
						// We found a match now let's put on the external url
						// link in front of it
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
			finalBody = finalBody.replaceAll("&amp;gtxResourceFileName", "?gtxResourceFileName");
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
		LOGGER.info("Exiting parseBody()");
	}

	private String replaceEmbeddedContentLink(String body, int startIndex, int endIndex, String contentId) {
		String openNewUrlInWindow = "<a class=\"sr_lr_link\" href=\"javascript:void(0);\" title=\"Open in new window\" onclick=\"$.fn.launchViewContent('" + contentId + "');\"><img src=\"images/ReadLaterGray16x16.png\"></a>";
		String newUrl = "<a href=\"#\" onclick=\"$.fn.retrieveContent('" + contentId + "');\">" + body.substring(startIndex, endIndex) + "</a>" + openNewUrlInWindow;
		return body.substring(0, startIndex) +
				newUrl +  body.substring(endIndex);
	}

	private String addReuseContentLink(String body, String title, String contentId) {
		String openNewUrlInWindow = "<a class=\"sr_lr_link\" href=\"javascript:void(0);\" title=\"Open in new window\" onclick=\"$.fn.launchViewContent('" + contentId + "');\"><img src=\"images/ReadLaterGray16x16.png\"></a>";
		String newUrl = "<a href=\"#\" onclick=\"$.fn.retrieveContent('" + contentId + "');\">" + title + "</a>" + openNewUrlInWindow;
		return body + "<p>" + newUrl + "</p>";
	}
}
