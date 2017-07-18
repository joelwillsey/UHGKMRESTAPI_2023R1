/**
 * 
 */
package com.verint.services.km.dao.parser;

import java.sql.ResultSet;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.verint.services.km.model.ScriptSrc;
import com.verint.services.km.model.Attachment;
import com.verint.services.km.model.ContentEntry;
import com.verint.services.km.model.ContentResponse;
import com.verint.services.km.model.CustomField;
import com.verint.services.km.model.ExternalContent;
import com.verint.services.km.model.RelatedContent;
import com.verint.services.km.model.Tag;
import com.verint.services.km.model.TagSet;

/**
 * @author jmiller
 *
 */
public class ElementParser {
	private static final Logger LOGGER = LoggerFactory.getLogger(ElementParser.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ElementParser elementParser = new ElementParser();
//		String bodyContent = "<![CDATA[<body><featured>false</featured><publicBody><p>This is public content.</p><p><br></p><p>This is reusable segment.<br data-mce-bogus=\"1\"></p><p><br></p><p><a href='null?gtxResource=/KM/files/uploaded/Benefit_Inquiries_-_DME_List_SVC_Time1456496076706.html&gtxResourceFileName=/KM/files/uploaded\\Benefit_Inquiries_-_DME_List_SVC.html&mode=download'>/KM/files/uploaded\\Benefit_Inquiries_-_DME_List_SVC.html</a></p></publicBody><publicSectionContent><content><entryId><id>xSgjojOym19Zj9UBaIBEd5</id><locale>en-GB</locale><type>KnowledgeUploadED</type><version>3.0</version></entryId><title>Upload Spy Example</title></content></publicSectionContent><publicSegmentContent><content><entryId><id>oxvkMI8ydL1F7LKsAqjs49</id><locale>en-GB</locale><type>KnowledgeSegmentED</type><version>1.0</version></entryId><title>Reusable Segment</title></content></publicSegmentContent><relatedContent><a href='null?gtxResource=/KM/files/uploaded/Benefit_Inquiries_-_DME_List_SVC_Time1456496076706.html&gtxResourceFileName=/KM/files/uploaded\\Benefit_Inquiries_-_DME_List_SVC.html&mode=download'>/KM/files/uploaded\\Benefit_Inquiries_-_DME_List_SVC.html</a><content><entryId><id>YtpKMFxhQq32E8Is58Nzs6</id><locale>en-GB</locale><type>KnowledgeArticleED</type><version></version></entryId><title>Spry Example Article</title></content></relatedContent><referenceName>VERINTEMKM</referenceName><title>Verint EM KM</title><attachments><a href='http://contenthost/GTConnect/UnifiedAcceptor/FrameworkDesktop.Main?gtxResource=/email/outbound/attachments\\createFeedbackWorkItem_Time1459225859136.txt&gtxResourceFileName=createFeedbackWorkItem.txt&mode=download'>createFeedbackWorkItem.txt</a><a href='http://contenthost/GTConnect/UnifiedAcceptor/FrameworkDesktop.Main?gtxResource=/email/outbound/attachments\\Cross_Tag_-_Country_State_Time1459225889396.xlsx&gtxResourceFileName=Cross_Tag_-_Country_State.xlsx&mode=download'>Cross_Tag_-_Country_State.xlsx</a></attachments><publishedDate>2016-03-29T04:31:54.340+00:00</publishedDate><expiredDate></expiredDate><lastModifiedBy>kmmanager</lastModifiedBy><owner>kmmanager</owner><weighting></weighting><status>PUBLISHED</status><tagsets><tagset> <systemTagSetName>product</systemTagSetName><displayTagSetName>Product</displayTagSetName><tag><systemTagName>product_iphone4</systemTagName><displayTagName>iPhone4</displayTagName></tag><tag><systemTagName>product_iphone5</systemTagName><displayTagName>iPhone5</displayTagName></tag></tagset><tagset> <systemTagSetName>topic</systemTagSetName><displayTagSetName>Topic</displayTagSetName><tag><systemTagName>topic_fullcoverage</systemTagName><displayTagName>Full Coverage</displayTagName></tag></tagset><tagset> <systemTagSetName>region</systemTagSetName><displayTagSetName>Region</displayTagSetName><tag><systemTagName>region_pacific</systemTagName><displayTagName>Pacific</displayTagName></tag><tag><systemTagName>region_west</systemTagName><displayTagName>West</displayTagName></tag><tag><systemTagName>region_westsouthcentral</systemTagName><displayTagName>West South Central</displayTagName></tag></tagset></tagsets></body>]]>";
//		String bodyContent = "<body><featured>false</featured><publicAnswer><p>Public Answer<br data-mce-bogus=\"1\"></p></publicAnswer><publicSectionContent></publicSectionContent><publicSegmentContent></publicSegmentContent><relatedContent></relatedContent><title>Another FAQ</title><attachments></attachments><publishedDate>2016-04-21T17:15:44.455+00:00</publishedDate><expiredDate></expiredDate><lastModifiedBy>kmmanager</lastModifiedBy><owner>kmmanager</owner><weighting></weighting><status>PUBLISHED</status><tagsets><tagset><systemTagSetName>territory</systemTagSetName><displayTagSetName>Territory</displayTagSetName><tag><systemTagName>territory_antiguabarbuda</systemTagName><displayTagName>Antigua & Barbuda</displayTagName></tag><tagset> <systemTagSetName>legal</systemTagSetName><displayTagSetName>Legal Construct</displayTagSetName><tag><systemTagName>legal_cameroon</systemTagName><displayTagName>CIMA - Cameroon</displayTagName></tag></tagset><tagset> <systemTagSetName>products</systemTagSetName><displayTagSetName>Products</displayTagSetName><tag><systemTagName>products_iptproductrecall</systemTagName><displayTagName>IPT - Product Recall</displayTagName></tag></tagset><tagset> <systemTagSetName>entity</systemTagSetName><displayTagSetName>Entity</displayTagSetName><tag><systemTagName>entity_alhamraainsuran</systemTagName><displayTagName>Al Hamraa Insurance Company</displayTagName></tag></tagset><tagset><systemTagSetName>function</systemTagSetName><displayTagSetName>Function</displayTagSetName><tag><systemTagName>function_operations</systemTagName><displayTagName>Operations</displayTagName></tag></tagset><tagset> <systemTagSetName>attach</systemTagSetName><displayTagSetName>Has Attachment</displayTagSetName><tag><systemTagName>attach_yes</systemTagName><displayTagName>Yes</displayTagName></tag></tagset></tagsets></body>";
//		String bodyContent = "<body><featured>false</featured><effectiveDate>2016-07-05T04:00:00.0+00:00</effectiveDate><expirationDate>2017-07-05T04:00:00.0+00:00</expirationDate><recertificationDate>2017-07-05T19:46:34.605+00:00</recertificationDate><longAnswer><p>some long answer</p></longAnswer><relatedContent></relatedContent><title>CVG - Does the Country have a national health care system that will provide medical services to injured employees?</title><attachments></attachments><publishedDate>2016-07-05T19:47:02.231+00:00</publishedDate><expiredDate></expiredDate><lastModifiedBy>admin</lastModifiedBy><owner>admin</owner><weighting></weighting><status>PUBLISHED</status><tagsets><tagset> <systemTagSetName>territory</systemTagSetName><displayTagSetName>Territory</displayTagSetName><tag><systemTagName>territory_argentina</systemTagName><displayTagName>Argentina</displayTagName></tag></tagset><tagset> <systemTagSetName>entity</systemTagSetName><displayTagSetName>Entity</displayTagSetName><tag><systemTagName>entity_aig</systemTagName><displayTagName>AIG</displayTagName></tag></tagset><tagset> <systemTagSetName>products</systemTagSetName><displayTagSetName>Products</displayTagSetName><tag><systemTagName>products_employersliab</systemTagName><displayTagName>Casualty - Employers Liability</displayTagName></tag></tagset>null<tagset> <systemTagSetName>legal</systemTagSetName><displayTagSetName>Legal Construct</displayTagSetName><tag><systemTagName>legal_centafricanrep</systemTagName><displayTagName>CIMA - Central African Republic</displayTagName></tag></tagset><tagset> <systemTagSetName>short</systemTagSetName><displayTagSetName>Short Answer</displayTagSetName><tag><systemTagName>short_kesa8pct</systemTagName><displayTagName>KE - 8%</displayTagName></tag></tagset><tagset> <systemTagSetName>attach</systemTagSetName><displayTagSetName>Has Attachment</displayTagSetName><tag><systemTagName>attach_no</systemTagName><displayTagName>No</displayTagName></tag></tagset></tagsets></body>";
//		String bodyContent = "<body><featured>false</featured><effectiveDate>2016-07-05T04:00:00.0+00:00</effectiveDate><expirationDate>2017-07-05T04:00:00.0+00:00</expirationDate><recertificationDate>2017-07-05T19:46:34.605+00:00</recertificationDate><longAnswer><p>some long answer</p></longAnswer><relatedContent></relatedContent><title>CVG - Does the Country have a national health care system that will provide medical services to injured employees?</title><attachments></attachments><publishedDate>2016-07-05T19:47:02.231+00:00</publishedDate><expiredDate></expiredDate><lastModifiedBy>admin</lastModifiedBy><owner>admin</owner><weighting></weighting><status>PUBLISHED</status><tagsets><tagset> <systemTagSetName>territory</systemTagSetName><displayTagSetName>Territory</displayTagSetName><tag><systemTagName>territory_argentina</systemTagName><displayTagName>Argentina</displayTagName></tag></tagset><tagset> <systemTagSetName>entity</systemTagSetName><displayTagSetName>Entity</displayTagSetName><tag><systemTagName>entity_aig</systemTagName><displayTagName>AIG</displayTagName></tag></tagset><tagset> <systemTagSetName>products</systemTagSetName><displayTagSetName>Products</displayTagSetName><tag><systemTagName>products_employersliab</systemTagName><displayTagName>Casualty - Employers Liability</displayTagName></tag></tagset><tagset> <systemTagSetName>ph</systemTagSetName><displayTagSetName>Question</displayTagSetName><tag><systemTagName>ph_phquest1018</systemTagName><displayTagName>CVG - Does the Country have a national health care system that will provide medical services to injured employees?</displayTagName></tag></tagset><tagset> <systemTagSetName>legal</systemTagSetName><displayTagSetName>Legal Construct</displayTagSetName><tag><systemTagName>legal_centafricanrep</systemTagName><displayTagName>CIMA - Central African Republic</displayTagName></tag></tagset><tagset> <systemTagSetName>short</systemTagSetName><displayTagSetName>Short Answer</displayTagSetName><tag><systemTagName>short_kesa8pct</systemTagName><displayTagName>KE - 8%</displayTagName></tag></tagset><tagset> <systemTagSetName>attach</systemTagSetName><displayTagSetName>Has Attachment</displayTagSetName><tag><systemTagName>attach_no</systemTagName><displayTagName>No</displayTagName></tag></tagset></tagsets></body>";
		String bodyContent = "<body><title>Program Descriptions</title><featured>false</featured><file>http://contenthost/GTConnect/UnifiedAcceptor/FrameworkDesktop.Main?gtxResource=/KM/files/uploaded/Open_Program Descriptions_UHG_en-US.html&gtxResourceFileName=/KM/files/uploaded/Open_Program Descriptions_UHG.html&mode=download</file><fileDescription>Open_Program Descriptions.html</fileDescription><referenceName>program_descriptions</referenceName><attachments></attachments><publishedDate></publishedDate><expiredDate></expiredDate><lastModifiedBy>kmmanager</lastModifiedBy><owner>kmmanager</owner><weighting></weighting><status>PUBLISHED</status><tagsets><tagset> <systemTagSetName>kbase</systemTagSetName><displayTagSetName>Knowledge Base</displayTagSetName><tag><systemTagName>kbase_default</systemTagName><displayTagName>Default</displayTagName></tag></tagset><tagset> <systemTagSetName>search</systemTagSetName><displayTagSetName>Searchability</displayTagSetName><tag><systemTagName>search_showinsearch</systemTagName><displayTagName>Show In Search</displayTagName></tag></tagset><tagset> <systemTagSetName>newchange</systemTagSetName><displayTagSetName>New or Changed</displayTagSetName><tag><systemTagName>newchange_neworchanged</systemTagName><displayTagName>New or Changed</displayTagName></tag></tagset></tagsets></body>";
		ContentResponse contentResponse = new ContentResponse();
		elementParser.parseBody(bodyContent, contentResponse);
		System.out.println("contentResponse: " + contentResponse);
		LOGGER.info("contentResponse: " + contentResponse);
	}

	/**
	 * 
	 * @param bodyContent
	 * @param response
	 * @return
	 */
	public boolean parseBody(String bodyContent, ContentResponse response) {
		LOGGER.info("Entering parseBody()");
		LOGGER.debug("bodyContent: " + bodyContent);
		Instant start = Instant.now();


		
		bodyContent = bodyContent.replaceAll("<!\\[CDATA\\[", "");
		bodyContent = bodyContent.replaceAll("]]>", "");
		
		/** This is related to the HFR12 bug fix for uploaded content, it came back as url originally, now
		 * it comes back as a url and in <upload> elements.  The <upload> elements should be <content> elements
		 * to match the format of all the other content formats
		 * <a href='null?gtxResource=/KM/files/uploaded/Sample2_Time1489596147222.htm&gtxResourceFileName=/KM/files/uploaded\Sample2.htm&mode=download'>/KM/files/uploaded\Sample2.htm</a>
		 * <upload>
		 *  <entryId>
		 * 		<id>g9ECFrthkT008uOFxf8Ex5</id>
		 *		<locale>en-US</locale>
		 *		<type>KnowledgeUploadED</type>
		 *		<version>5.0</version>
		 *	</entryId>
		 *	<title>Paul's Puri Test</title>
		 * </upload> 
		 * 
		 * We need to remove the <a> </a> element as it is a duplicate of what is contained in <upload> </upload>
		 * The we replace the <upload> with <content> to make it match the correct format.
		 * I'll open a ticket on this so they can fix it.
		**/	
		bodyContent = removeUploadLinks(bodyContent);
		bodyContent = bodyContent.replaceAll("<upload>", "<content>");
		bodyContent = bodyContent.replaceAll("</upload>", "</content>");
		
// BEGIN JGM - Fix for UHG specific content
		//just put in <script so it can allow attributes so I expect to see #startscript#>
		//bodyContent = bodyContent.replaceAll("#startscript#&gt;", "<script>");
		//bodyContent = bodyContent.replaceAll("#startscript#", "<script");		
		//bodyContent = bodyContent.replaceAll("#endscript#", "</script>");

		//bodyContent = parseScriptPlaceHolders(bodyContent);
		resultParseScriptPlaceHolders ScriptPlaceHolders =parseScriptPlaceHolders(bodyContent);
		bodyContent = ScriptPlaceHolders.getData();
		response.setExternalSrcFiles(ScriptPlaceHolders.getExternalSrcFiles());
		bodyContent = parseLinkPlaceHolders(bodyContent);
		
// END JGM
		
		//Start PRS
		// parseInlineContent was only happening in public body, moving to here to it effects all sections of content
		//bodyContent= inlineContentEntry(bodyContent);
		//End PRS
		
		LOGGER.debug("bodyContent2: " + bodyContent);
		
		ParserInfo parserInfo = parseData(bodyContent, "publicBody");
		String publicBody = parseInlineContent(parserInfo.getElementData());
		publicBody = inlineContentEntry(publicBody);		
		response.setPublicBody(publicBody);
		
		parserInfo = parseData(parserInfo.getData(), "publicAnswer");
		String publicAnswer = parseInlineContent(parserInfo.getElementData());
		publicAnswer = inlineContentEntry(publicAnswer);
		response.setPublicAnswer(publicAnswer);

		parserInfo = parseData(parserInfo.getData(), "publicSectionContent");
		String publicSectionContent = parserInfo.getElementData();
		List<ContentEntry> publicSectionContents = getContentEntry(publicSectionContent, "public");
		final Set<ContentEntry> hashPublicContent = new LinkedHashSet<ContentEntry>(publicSectionContents);
		response.setPublicSectionContent(hashPublicContent);
		
		parserInfo = parseData(parserInfo.getData(), "publicSegmentContent");
		String publicSectionSegment = parserInfo.getElementData();
		List<ContentEntry> publicSectionSegments = getContentEntry(publicSectionSegment, "public");
		final Set<ContentEntry> hashPublicSegment = new LinkedHashSet<ContentEntry>(publicSectionSegments);
		response.setPublicSegmentContent(hashPublicSegment);
		
		parserInfo = parseData(parserInfo.getData(), "privateBody");
		String privateBody = parseInlineContent(parserInfo.getElementData());
		privateBody = inlineContentEntry(privateBody);
		response.setPrivateBody(privateBody);

		parserInfo = parseData(parserInfo.getData(), "privateAnswer");
		String privateAnswer = parseInlineContent(parserInfo.getElementData());
		privateAnswer = inlineContentEntry(privateAnswer);
		response.setPrivateAnswer(privateAnswer);

		parserInfo = parseData(parserInfo.getData(), "privateSectionContent");
		String privateSectionContent = parserInfo.getElementData();
		List<ContentEntry> privateSectionContents = getContentEntry(privateSectionContent, "private");		
		final Set<ContentEntry> hashPrivateContent = new LinkedHashSet<ContentEntry>(privateSectionContents);
		response.setPrivateSectionContent(hashPrivateContent);
		
		parserInfo = parseData(parserInfo.getData(), "privateSegmentContent");
		String privateSectionSegment = parserInfo.getElementData();
		List<ContentEntry> privateSectionSegments = getContentEntry(privateSectionSegment, "private");
		final Set<ContentEntry> hashPrivateSegment = new LinkedHashSet<ContentEntry>(privateSectionSegments);
		response.setPrivateSectionContent(hashPrivateSegment);

		parserInfo = parseData(parserInfo.getData(), "publishedDate");
		response.setPublishedDate(parserInfo.getElementData());

		parserInfo = parseData(parserInfo.getData(), "expiredDate");
		response.setExpiredDate(parserInfo.getElementData());

		parserInfo = parseData(parserInfo.getData(), "lastModifiedBy");
		response.setLastModifiedBy(parserInfo.getElementData());

		parserInfo = parseData(parserInfo.getData(), "owner");
		response.setOwner(parserInfo.getElementData());

		parserInfo = parseData(parserInfo.getData(), "weighting");
		response.setWeighting(parserInfo.getElementData());

		parserInfo = parseData(parserInfo.getData(), "status");
		response.setStatus(parserInfo.getElementData());

		parserInfo = parseData(parserInfo.getData(), "relatedContent");
		String relatedContent = parserInfo.getElementData();
		List<ContentEntry> relatedEntries = getContentEntry(relatedContent, "public");
		final Set<ContentEntry> hashRelatedEntries = new LinkedHashSet<ContentEntry>(relatedEntries);
		List<ExternalContent> relatedExternals = getExternalContent(relatedContent);
		final Set<ExternalContent> hashRelatedExternals = new LinkedHashSet<ExternalContent>(relatedExternals);
		RelatedContent relatedContentObject = new RelatedContent();
		relatedContentObject.setContentEntries(hashRelatedEntries);
		relatedContentObject.setExternalContents(hashRelatedExternals);
		response.setRelatedContent(relatedContentObject);
		
		parserInfo = parseData(parserInfo.getData(), "attachments");
		String attachments = parserInfo.getElementData();
		List<Attachment> attachment = getAttachments(attachments);
		final Set<Attachment> hashAttachments = new LinkedHashSet<Attachment>(attachment);
		response.setAttachments(hashAttachments);

		parserInfo = parseData(parserInfo.getData(), "tagsets");
		String tagSetsData = parserInfo.getElementData();
		System.out.println("TagSetsData: " + tagSetsData);
		List<TagSet> tagSets = getTags(tagSetsData);
		System.out.println("tagSets: " + tagSets);
		int x = 0;
		while ((tagSets != null) && (x < tagSets.size())) {
			TagSet ts = tagSets.get(x);
			System.out.println("TagSet: " + ts);
			response.addTagSet(ts);
			x++;
			System.out.println("contentResponse2: " + response);
		}
		System.out.println("contentResponse2: " + response);
		
		// Now remove these fields
		String[] elementsToRemove = new String[2];
		elementsToRemove[0] = "title";
		elementsToRemove[1] = "featured";		
		LOGGER.debug("bodyContent3: " + parserInfo.getData());
		bodyContent = removeUnwantedElments(parserInfo.getData(), elementsToRemove);
		LOGGER.debug("bodyContent4: " + bodyContent);
		
		List<CustomField> customFields = parseCustomFields(bodyContent);
		final Set<CustomField> hashCustomFields = new LinkedHashSet<CustomField>(customFields);
		response.setCustomFields(hashCustomFields);

		Instant end = Instant.now();
		LOGGER.debug("Parsing Execution - elementParser.parseBody() duration: " + Duration.between(start, end).toMillis() + "ms");
		return true;
	}
	
	/**
	 * 
	 * @param data
	 * @param element
	 * @return
	 */
	public ParserInfo parseData(String data, String element) {
		LOGGER.info("Entering parseData()");
		LOGGER.debug("data: " + data);
		LOGGER.debug("element: " + element);
		final String emptyElement = "<" + element + "/>";
		final ParserInfo parserInfo = new ParserInfo();

		// First check for an empty element
		if (data != null && data.length() > 0) {
			int index = data.indexOf(emptyElement);
			if (index != -1) {
				// We have an empty element and return such
				parserInfo.setEmptyElement(true);
				parserInfo.setStartLocation(index);
				parserInfo.setEndLocation(index + emptyElement.length());
				parserInfo.setElementData("");
				parserInfo.setData(data.substring(0, index) + data.substring(index + emptyElement.length()));
			} else {
				final String beginElement = "<" + element + ">";
				final String endElement = "</" + element + ">";
				int beginIndex = data.indexOf(beginElement);
				int endIndex = data.indexOf(endElement);
				if (beginIndex == -1 || endIndex == -1) {
					// We don't have proper being/end elements
					LOGGER.debug("ParserTags do not have proper enpoints " + beginElement + " " + endElement);
					parserInfo.setElementData("");
					parserInfo.setData(data);
				} else if (beginIndex != -1 && endIndex != -1) {
					// Ok we have both start and end
					parserInfo.setEmptyElement(false);
					parserInfo.setStartLocation(beginIndex);
					parserInfo.setEndLocation(endIndex + endElement.length());
					parserInfo.setElementData(data.substring(beginIndex + beginElement.length(), endIndex)); 
					parserInfo.setData(data.substring(0, beginIndex) + data.substring(endIndex + endElement.length()));
				}
			}
		} else {
			parserInfo.setEmptyElement(true);
		}
		LOGGER.debug("ParserInfo: " + parserInfo);
		LOGGER.info("Exiting parseData()");
		return parserInfo;
	}

	/**
	 * 
	 * @param data
	 * @param element
	 * @return
	 */
	public List<CustomField> parseCustomFields(String data) {
		LOGGER.info("Entering parseCustomFields()");
		LOGGER.debug("data: " + data);
		List<CustomField> customFields = new ArrayList<CustomField>();
		if (data != null && data.length() > 0) {
			// First get past the <body>
			int indexBody = data.indexOf("<body>");
			if (indexBody != -1) {
				// Go to next element
				String startingBody = data.substring(indexBody + "<body>".length());
				while (startingBody != null && startingBody.length() > 0 && !startingBody.equals("</body>")) {
					// search for start and end <>
					int startBracket = startingBody.indexOf("<");
					int endBracket = startingBody.indexOf(">");
					if (startBracket != -1 && endBracket != -1) {
						// We have a match; now find the end element
						System.out.println(startBracket);
						System.out.println(endBracket);
						System.out.println(startingBody);
						// Get the name of the element
						String name = startingBody.substring(startBracket+1, endBracket);
						int endingBracket = startingBody.indexOf("</" + name + ">");
						if (endingBracket != -1) {
							// Now get all the data in between
							final String customFieldData = startingBody.substring(endBracket + 1, endingBracket);
							if (name != null && !name.equals("file") && !name.equals("fileDescription") ) {
								final CustomField customField = new CustomField();
								customField.setName(name);
								customField.setData(customFieldData);
								customFields.add(customField);
							}
							startingBody = startingBody.substring(endingBracket + ("</" + name + ">").length());
						}
					}
				}
			}
		}
		LOGGER.info("Exiting parseCustomFields()");
		return customFields;
	}

	/**
	 * 
	 * @param data
	 * @param elements
	 * @return
	 */
	public String removeUnwantedElments(String data, String[] elements) {
		LOGGER.info("Entering removeUnwantedElments()");
		LOGGER.debug("data: " + data);
		LOGGER.debug("elements: " + elements);
		String newData = data;
		if ((data != null && data.length() > 0) && (elements != null && elements.length > 0)) {
			for (int x = 0; x < elements.length; x++) {
				ParserInfo parserInfo = parseData(newData, elements[x]);
				newData = parserInfo.getData();
			}
		}
		LOGGER.info("Exiting removeUnwantedElments()");
		return newData;
	}

	/**
	 * 
	 * @param data
	 * @param view
	 * @return
	 */
	public List<ExternalContent> getExternalContent(String data) {
		LOGGER.info("Entering getExternalContent()");
		LOGGER.debug("data: " + data);
		List<ExternalContent> externalContents  = new ArrayList<ExternalContent>();
		while (data != null && data.length() > 0) {
			int begin = data.indexOf("<a ");
			int end = data.indexOf("</a>");
			LOGGER.debug("begin: " + begin);
			LOGGER.debug("end: " + end);			
			if (begin != -1 && end != -1) {
				int findBeginEnd = data.indexOf("'>");
				if (findBeginEnd != -1){
					LOGGER.debug("findBeginEnd: " + findBeginEnd);
					ExternalContent externalContent = new ExternalContent();
					String name = data.substring(findBeginEnd + "'>".length(), end);
					externalContent.setName(name);
					// Get href
					int beginHref = data.indexOf("href='");
					LOGGER.debug("beginHref: " + beginHref);
					if (beginHref != -1) {
						String tempString = data.substring(beginHref + "href='".length());
						LOGGER.debug("tempString: " + tempString);
						int endHref = tempString.indexOf("'>");
						LOGGER.debug("endHref: " + endHref);
						if (endHref != -1) {
							externalContent.setUrl(tempString.substring(0, endHref));
							externalContents.add(externalContent);
							data = data.substring(end + 1);
						} else {
							LOGGER.error("Unable to find '> for the end of href=' for start of href. Link Substring: " + data.substring(begin, end));
							break;
						}
					} else {
						LOGGER.error("Unable to find href=' for start of link. Link Substring: " + data.substring(begin, end));
						break;
					}
				} else {
					LOGGER.error("Unable to find '> for closing of end tag of <a.  Link Substring: " + data.substring(begin, end));
					break;
				}
			} else {
				break;
			}
		}
		LOGGER.info("Exiting getExternalContent()");
		return externalContents;
	}

	/**
	 * 
	 * @param data
	 * @param view
	 * @return
	 */
	public List<Attachment> getAttachments(String data) {
		LOGGER.info("Entering getAttachments()");
		LOGGER.debug("data: " + data);
		List<Attachment> attachments  = new ArrayList<Attachment>();
		while (data != null && data.length() > 0) {
			int begin = data.indexOf("<a ");
			int end = data.indexOf("</a>");
			LOGGER.debug("begin: " + begin);
			LOGGER.debug("end: " + end);
			if (begin != -1 && end != -1) {
				int findBeginEnd = data.indexOf("'>");
				LOGGER.debug("findBeginEnd: " + findBeginEnd);
				if (findBeginEnd != -1){
					Attachment attachment = new Attachment();
					String name = data.substring(findBeginEnd + "'>".length(), end);
					attachment.setName(name);
					// Get href
					//int beginHref = data.indexOf("href=\"");
					int beginHref = data.indexOf("href='");
					LOGGER.debug("beginHref: " + beginHref);
					if (beginHref != -1) {
						//String tempString = data.substring(beginHref + "href=\"".length());
						String tempString = data.substring(beginHref + "href='".length());
						LOGGER.debug("tempString: " + tempString);
						//int endHref = tempString.indexOf("\">");
						int endHref = tempString.indexOf("'>");
						LOGGER.debug("endHref: " + endHref);
						if (endHref != -1) {
							attachment.setUrl(tempString.substring(0, endHref));
							attachments.add(attachment);
							data = data.substring(end + 1);
						} else {
							//LOGGER.error("Unable to find '\">\" for closing of href tag.  Link Substring: " + data.substring(begin, end));
							LOGGER.error("Unable to find '> for closing of href tag.  Link Substring: " + data.substring(begin, end));
						}
					} else {
						LOGGER.error("Unable to find href=' for start of link. Link Substring: " + data.substring(begin, end));
						break;
					}
				}else {
					//LOGGER.error("Unable to find '\">\" for closing of end tag of <a  findBeginEnd: " + findBeginEnd);
					LOGGER.error("Unable to find '> for closing of end tag of <a  findBeginEnd: " + findBeginEnd);
				}
			} else {
				break;
			}
		}
		LOGGER.info("Exiting getAttachments()");
		return attachments;
	}

	

	
	/**
	 * 
	 * @param data
	 * @param view
	 * @return
	 */
	public List<TagSet> getTags(String data) {
		ParserInfo info = parseData(data, "tagset");
		String next = info.getData();
		List<TagSet> tagSets = new ArrayList<TagSet>();
		while (info != null && !info.isEmptyElement() && info.getElementData() != null && info.getElementData().length() > 0) {
			TagSet tagSet = new TagSet();
			info = parseData(info.getElementData(), "systemTagSetName");
			if (info != null && !info.isEmptyElement()) {
				tagSet.setSystemTagName(info.getElementData());
			}
			info = parseData(info.getData(), "displayTagSetName");
			if (info != null && !info.isEmptyElement()) {
				tagSet.setDisplayTagName(info.getElementData());
			}

			info = parseData(info.getData(), "tag");
			String nextTag = info.getData();
			while (info != null && !info.isEmptyElement() && info.getElementData() != null && info.getElementData().length() > 0) {
				Tag tag = new Tag();
				info = parseData(info.getElementData(), "systemTagName");
				tag.setSystemTagName(info.getElementData());
				info = parseData(info.getData(), "displayTagName");
				tag.setSystemTagDisplayName(info.getElementData());
				tagSet.addTag(tag);
				if (info.getData() != null) {
					info = parseData(nextTag, "tag");
					nextTag = info.getData();
				} else {
					info = null;
				}
			}
			tagSets.add(tagSet);
			if (info.getData() != null) {
				info = parseData(next, "tagset");
				next = info.getData();
			} else {
				info = null;
			}
		}
		return tagSets;
	}

	/**
	 * 
	 * @param data
	 * @param view
	 * @return
	 */
	public List<ContentEntry> getContentEntry(String data, String view) {
		ParserInfo info = parseData(data, "content");
		String next = info.getData();
		List<ContentEntry> contentEntries = new ArrayList<ContentEntry>();
		while (info != null && !info.isEmptyElement() && info.getElementData() != null && info.getElementData().length() > 0) {
			ContentEntry contentEntry = new ContentEntry();
			contentEntry.setView(view);
			System.out.println(info.getElementData());
			info = parseData(info.getElementData(), "title");
			if (info != null && !info.isEmptyElement()) {
				contentEntry.setTitle(info.getElementData());
			}
			info = parseData(info.getData(), "entryId");
			if (info != null && !info.isEmptyElement()) {
				info = parseData(info.getElementData(), "id");
				contentEntry.setId(info.getElementData());
				info = parseData(info.getData(), "locale");
				contentEntry.setLocale(info.getElementData());
				info = parseData(info.getData(), "type");
				contentEntry.setType(info.getElementData());
				info = parseData(info.getData(), "version");
				contentEntry.setVersion(info.getElementData());
			}
			contentEntries.add(contentEntry);
			if (info.getData() != null) {
				info = parseData(next, "content");
				next = info.getData();
			} else {
				info = null;
			}
		}
		
		return contentEntries;
	}

	/**
	 * 
	 * @param data
	 * @param element
	 * @return
	 */
	public String parseInlineData(String data, String element) {
		LOGGER.info("Entering parseInlineData()");
		LOGGER.debug("data: " + data);
		LOGGER.debug("element: " + element);
		final String emptyElement = "<" + element + "/>";

		// First check for an empty element
		if (data != null && data.length() > 0) {
			int index = data.indexOf(emptyElement);
			if (index != -1) {
				// We have an empty element and return such
				data = data.substring(0, index) + data.substring(index + emptyElement.length());
			} else {				
				final String beginElement = "<" + element + ">";
				final String endElement = "</" + element + ">";
				int beginIndex = data.indexOf(beginElement);
				int endIndex = data.indexOf(endElement);
				if (beginIndex == -1 || endIndex == -1) {
					// We don't have proper being/end elements
					LOGGER.debug("ParserTags do not have proper endpoints " + beginElement + " " + endElement);
				} else if (beginIndex != -1 && endIndex != -1) {
					// Ok we have both start and end					
					String tempData = data.substring(beginIndex + ("<" + element + ">").length(), endIndex);
					//LOGGER.debug("parseInlineData searching element: '" + element + "' data: "  + tempData);
					ParserInfo info = parseData(tempData, "title");
					String title = "";
					String id = "";
					if (info != null && !info.isEmptyElement()) {
						title = info.getElementData();
					}
					info = parseData(info.getData(), "entryId");
					if (info != null && !info.isEmptyElement()) {
						info = parseData(info.getElementData(), "id");
						id = info.getElementData();
						 //info = parseData(info.getData(), "locale");
						 //String locale = info.getElementData();
						 //info = parseData(info.getData(), "type");
						 //String type = info.getElementData();
						 //info = parseData(info.getData(), "version");
						 //String version = info.getElementData();
					}
					String openNewUrlInWindow = "<a class=\"sr_lr_link\" href=\"javascript:void(0);\" title=\"Open in new window\" onclick=\"$.fn.launchViewContent('" + id + "');\"><img src=\"images/ReadLaterGray16x16.png\"></a>";
					//String openNewUrlInWindowWithDiv = "<a class=\"sr_lr_link\" href=\"javascript:void(0);\" title=\"Open in new window\" onclick=\"$.fn.launchViewContent('" + id + "');\"><img src=\"images/ReadLaterGray16x16.png\"></a></div>";
					//The below line should be uncommented if you do not want the pop out icon
					//String newUrl = "<a href=\"#\" onclick=\"$.fn.retrieveContent('" + id + "');\">" + title + "</a>";
					//The below lines adds the pop-out icon to embedded links
					//String newUrlWithDiv = "<div class = \"sr_embedded_content\"><a href=\"#\" onclick=\"$.fn.retrieveContent('" + id + "');\">" + title + "</a>" + openNewUrlInWindow;
					String newUrl = "<a href=\"#\" onclick=\"$.fn.retrieveContent('" + id + "');\">" + title + "</a>" + openNewUrlInWindow;
					data = data.substring(0, beginIndex) + newUrl + data.substring(endIndex + ("</" + element + ">").length());
					//LOGGER.debug("parseInlineData new url:" +newUrl);
					
					
					
					
				}
			}
		} else {
		}
		LOGGER.debug("data: " + data);
		LOGGER.info("Exiting parseInlineData()");
		return data;
	}

	/**
	 * 
	 * @param data
	 * @param view
	 * @return
	 */
	public String inlineContentEntry(String data) {
		String newData = parseInlineData(data, "content");
		int index = newData.indexOf("<content>");
		int indexLoopControl = -1;
		while (index != -1) {
			indexLoopControl = newData.indexOf("<content>");
			newData = parseInlineData(newData, "content");
			index = newData.indexOf("<content>");
			if(index != -1 && indexLoopControl == index){
				//entered an infinite loop set index to -1 to exit.  Most likely caused by missing </content> element
				index = -1;
				LOGGER.error("inlineContentEntry has entered an infinite loop. Most likely caused by missing </content> element. data: " + data);
				
			}
		}
		return newData;
	}
	

	/**
	 * 
	 * @param data
	 * @return
	 */
	public String parseInlineContent(String data) {
		LOGGER.info("Entering parseInlineContent()");
		//[[--ContentED.tC7ZxVwafh6P2ZGru9SQP6||1. Overview||KM1000521||Article--]]
		if (data != null && data.length() > 0) {
			int beginIndex = data.indexOf("[[--");
			while (beginIndex != -1) {
				int endIndex = data.indexOf("--]]");
				if (endIndex != -1) {
					// We have a match
					String tempData = data.substring(beginIndex + "[[--".length(), endIndex);
					LOGGER.debug("ContentData: " + tempData);
					if (tempData != null) {
						String[] tokens = tempData.split("\\|\\|");
						//if (tokens != null && tokens.length == 4) {
						//There should be 4 tokens but if it's the Migration Tool it could be only 2
						if (tokens != null && tokens.length >= 2) {
							// Get rid of ContentED.
							int tempIndex = tokens[0].indexOf("ContentED.");
							String id = tokens[0].substring(tempIndex + "ContentED.".length());
							String openNewUrlInWindow = "<a class=\"sr_lr_link\" href=\"javascript:void(0);\" title=\"Open in new window\" onclick=\"$.fn.launchViewContent('" + id + "');\"><img src=\"images/ReadLaterGray16x16.png\"></a>";
							//String openNewUrlInWindowWithDiv = "<a class=\"sr_lr_link\" href=\"javascript:void(0);\" title=\"Open in new window\" onclick=\"$.fn.launchViewContent('" + id + "');\"><img src=\"images/ReadLaterGray16x16.png\"></a></div>";
							//The below line should be uncommented if you do not want the pop out icon
							//String newUrl = "<a href=\"#\" onclick=\"$.fn.retrieveContent('" + id + "');\">" + tokens[1] + "</a>";
							//The below lines adds the pop-out icon to embedded links
							//String newUrlWithDiv = "<div class = \"sr_listing_result\"><a href=\"#\" onclick=\"$.fn.retrieveContent('" + id + "');\">" + tokens[1] + "</a>" + openNewUrlInWindowWithDiv;
							String newUrl = "<a href=\"#\" onclick=\"$.fn.retrieveContent('" + id + "');\">" + tokens[1] + "</a>" + openNewUrlInWindow;
							LOGGER.debug("newUrl: " + newUrl);
							data = data.substring(0, beginIndex) + newUrl + data.substring(endIndex + ("--]]").length());
						} else {
							//No tokens need to end the loop
							LOGGER.error("parseInlineContent - Missing the correct number of tokens in ContentData: " + tempData);
							break;
						}
					} else {
						//No data need to end the loop
						LOGGER.error("parseInlineContent - Missing the embedded link in data (beginIndex="+beginIndex+" endIndex="+endIndex + " data:" + data);
						break;
					}
				}
				beginIndex = data.indexOf("[[--");
			}
		}
		LOGGER.info("Exiting parseInlineContent()");
		return data;
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	public String parsePublic(String data) {
		return null;
	}
	
	/**
	 * Find script placeholders and convert it to the correct script verbiage and move it to the top
	 * 
	 * @param data
	 * @return resultParseScriptPlaceHolders
	 */
	public resultParseScriptPlaceHolders parseScriptPlaceHolders(String data){
		LOGGER.info("Entering parseScriptPlaceHolders()");
		
		resultParseScriptPlaceHolders result = new resultParseScriptPlaceHolders();
		String originalData = data;			
		String publicBody = "<publicBody>";
		String srcAttribute = "src=";
		String typeAttribute = "type=";
		String asyncAttribute = "async";
		String startscriptPlaceHolder ="#startscript#";
		String stopscriptPlaceHolder ="#endscript#";
		
		int startSearchIndex = 0;
		String scriptString = "";
		
		int indexStartScript = originalData.indexOf(startscriptPlaceHolder, startSearchIndex);
		
		while (indexStartScript != -1) {
			
			// script tag could be self contained i.e #startscript# type="text/javascript" src="http://localhost:8090/fileStorage/lastModified.js">
			// or have an end tag i.e. #startscript# type="text/javascript">function StartCountdown(){for(var i=1; i{{&lt;}}=3; i++) {alert("Count "+i+" is done!") }}#endscript#
			// so we need to find the next start to make sure it is not before the end tag
			int indexEndScript = originalData.indexOf(stopscriptPlaceHolder, indexStartScript + startscriptPlaceHolder.length());
			if (indexEndScript != -1){
				scriptString = originalData.substring(indexStartScript, indexEndScript + stopscriptPlaceHolder.length());
				String newScriptString = replaceEscapeChar(scriptString);
				
				
				int indexSrcAttr = newScriptString.indexOf(srcAttribute);
				
				
				if (indexSrcAttr == -1){
					data = data.replaceAll(Pattern.quote(scriptString), newScriptString);
					LOGGER.debug("parseScriptPlaceHolders: Replacing script placeholders: \"" + scriptString + "\" with \"" + newScriptString + "\"");
				} else {
					ScriptSrc externalFile = new ScriptSrc();
					//default it to a type="text/javascript"  it will get overwritten later if they set the type
					externalFile.setType("text/javascript");
					
					//get the src attribute
					int indexofStartQout = newScriptString.indexOf( "\"", indexSrcAttr + srcAttribute.length());
					if (indexofStartQout != -1){
						int indexofEndQout = newScriptString.indexOf( "\"", indexofStartQout + 1);
						if (indexofEndQout != -1){
							String scrString = newScriptString.substring(indexofStartQout + 1, indexofEndQout);
							externalFile.setSrc(scrString);
							LOGGER.debug("Found scr attribute of script, src=\"" +  scrString + "\"");
							
							//lets see if they added the type attribute
							int indexofType = newScriptString.indexOf(typeAttribute);
							if (indexofType != -1){
								// found it
								int startOfTypeAttr = newScriptString.indexOf("\"", indexofType);
								if (startOfTypeAttr != -1){
									int endOfTypeAttr = newScriptString.indexOf("\"", startOfTypeAttr+1);
									if (endOfTypeAttr != -1){
										String typeString = newScriptString.substring(startOfTypeAttr + 1, endOfTypeAttr);
										externalFile.setType(typeString);
										LOGGER.debug("Found type attribute of script, type=\"" +  typeString + "\"");
									} 
								}
							} 							
							
							//lets see if they added the async attribute
							int indexofAsync = newScriptString.indexOf(asyncAttribute);
							if (indexofAsync != -1){
								externalFile.setAsync("true");
								LOGGER.debug("Found async attribute of script");
							} else {
								externalFile.setAsync("false");
							}
							
							result.addExternalSrcFiles(externalFile);
							LOGGER.debug("New external file added:" + externalFile.toString());
						}					
					}
					
					//this is an external file
					//int indexPublicBody = data.indexOf(publicBody);
					
					data = data.replaceAll(Pattern.quote(scriptString), "<!-- Removed remote js script tag to be added back in by the system -->");						
					LOGGER.debug("parseScriptPlaceHolders: Replacing script placeholders: \"" + scriptString + "\" with \"" + newScriptString + "\"");
					
					/** 
					if (indexPublicBody != -1) {
						data = data.replaceAll(Pattern.quote(scriptString), "<!-- Moved remote js script tag to top of publicBody content from here -->");	
						data = data.substring(0, indexPublicBody + publicBody.length()) + newScriptString + data.substring(indexPublicBody + publicBody.length());
						LOGGER.debug("parseScriptPlaceHolders: Replacing script placeholders: \"" + scriptString + "\" with \"" + newScriptString + "\"");
					}
					**/
				}
			}

			startSearchIndex = 	indexStartScript + startscriptPlaceHolder.length();
			
			if (startSearchIndex == -1){
				indexStartScript = -1;				
			} else {
				indexStartScript = originalData.indexOf(startscriptPlaceHolder, startSearchIndex);
			}
		
		}
		LOGGER.info("External files found: " + result.getExternalSrcFiles().toString());
		LOGGER.info("Exiting parseScriptPlaceHolders()");
		
		
		result.setData(data);		
		//return data;
		return result;
		
	}
	
	//Find link placeholders and convert it to the correct script verbiage and move it to the top
		public String parseLinkPlaceHolders(String data){
			LOGGER.info("Entering parseLinkPlaceHolders()");
			
			String originalData = data;			
			String publicBody = "<publicBody>";
			String linkPlaceHolder = "#startlink#";
			//HTML escape character > = &gt;
			String greaterThanEsc = "&gt;";

			
			int startSearchIndex = 0;
			
			int indexStartLink = originalData.indexOf(linkPlaceHolder, startSearchIndex);
			
			
			while (indexStartLink != -1) {
				
				// link tag;  #startlink# media="screen" type="text/css" href="/http://localhost:8090/fileStorage/SpryTabbedPanels.css" rel="stylesheet" &gt;
				
				int indexStartLinkClosing = originalData.indexOf(greaterThanEsc, indexStartLink);
				
				
				if (indexStartLinkClosing != -1){
					String linkString = originalData.substring(indexStartLink, indexStartLinkClosing) + originalData.substring(indexStartLinkClosing, indexStartLinkClosing + greaterThanEsc.length());
					
					String newLinkString = replaceEscapeChar(linkString);
					
					int indexPublicBody = data.indexOf(publicBody);
					
					if (indexPublicBody != -1) {
						data = data.replaceAll(Pattern.quote(linkString), "<!-- Moved link tag to top of publicBody content from here -->");
						data = data.substring(0, indexPublicBody + publicBody.length()) + newLinkString + data.substring(indexPublicBody + publicBody.length());
						LOGGER.debug("parseLinkPlaceHolders: Replacing Link placeholders: \"" + linkString + "\" with \"" + newLinkString + "\"");
					}
					
					
				}
				
				startSearchIndex = 	indexStartLink + linkPlaceHolder.length();
				
				if (startSearchIndex == -1){
					indexStartLink = -1;
				} else {
					indexStartLink = originalData.indexOf(linkPlaceHolder, startSearchIndex);	
				}
			
			}
			LOGGER.info("Exiting parseLinkPlaceHolders()");
			return data;
		}
		
	//PRS replace html escape characters for script with correct characters
	public String replaceEscapeChar(String data){
		//HTML Escape characters
		//& becomes &amp;
		//< becomes &lt;
		//> becomes &gt;
		//In attribute values you must also escape the quote character [spec]:
		//" becomes &quot;
		//' becomes &#39;
		data = data.replaceAll(Pattern.quote("#startscript#"), "<script");
		data = data.replaceAll(Pattern.quote("#endscript#"), "</script>");
		data = data.replaceAll(Pattern.quote("#startlink#"), "<link");
		data = data.replaceAll(Pattern.quote("&amp;"), "&");
		data = data.replaceAll(Pattern.quote("&lt;"), "<");
		data = data.replaceAll(Pattern.quote("&gt;"), ">");
		data = data.replaceAll(Pattern.quote("&quot;"), "\"");
		data = data.replaceAll(Pattern.quote("&#39;"), "'");
		
		return data;
	}
	
	public String removeUploadLinks(String data){
		/** The file upload content comes across like this
		 * <a href='null?gtxResource=/KM/files/uploaded/Sample2_Time1489596147222.htm&gtxResourceFileName=/KM/files/uploaded\Sample2.htm&mode=download'>/KM/files/uploaded\Sample2.htm</a>
		 * <upload>
		 * <entryId>
		 * 		<id>g9ECFrthkT008uOFxf8Ex5</id>
		 *		<locale>en-US</locale>
		 *		<type>KnowledgeUploadED</type>
		 *		<version>5.0</version>
		 *	</entryId>
		 *	<title>Paul's Puri Test</title>
		 * </upload> 
		 * 
		 * We need to remove the <a> </a> element as it is a duplicate of what is in <upload>
		**/	
		LOGGER.debug("Entering removeUploadLinks()");
		
		String newData = data;
		
		//lets check to see if this content even has any upload links before parsing it
		if (data.indexOf("<upload>") != -1){
			int indexOfStartLink = data.indexOf("<a");
			int indexOfEndLink = -1;
			while(indexOfStartLink != -1){
				indexOfEndLink = data.indexOf("</a>", indexOfStartLink);
				if (indexOfEndLink != -1){
					//found link check if it has path to KM/files/uploaded
					String theLink = data.substring(indexOfStartLink, indexOfEndLink + "</a>".length());
					//LOGGER.debug("Found Link: " + theLink);
					if( theLink.indexOf("gtxResource=/KM/files/uploaded") != -1 || theLink.indexOf("gtxResource=\\KM\\files\\uploaded") != -1){								
						//found a link that containing the path to the file uploads, now need to check right after the </a> should be <upload> if it is remove the link
						//LOGGER.debug("Passed file upload path check");
						if(data.indexOf("<upload>", indexOfEndLink + "</a>".length()) == indexOfEndLink + "</a>".length()){
							//remove the link
							int indexOfTheLink = newData.indexOf(theLink);
							if(indexOfTheLink != -1){
							newData = newData.substring(0, indexOfTheLink) +  data.substring(indexOfStartLink + theLink.length());
							LOGGER.debug("Removed link preceding content type upload: " + theLink);
							//LOGGER.debug("Data: " + newData);
							}
						}
					}	
					indexOfStartLink = data.indexOf("<a", indexOfEndLink + "</a>".length());
				} else {
				indexOfStartLink = -1;
				}
			}
		}
		
		LOGGER.debug("Exiting removeUploadLinks()");
		
		return newData;
	}
}