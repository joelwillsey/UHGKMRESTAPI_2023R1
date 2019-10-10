package com.verint.services.km.service;

import java.util.Set;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import com.verint.services.km.model.HoverTextResponse;
import com.verint.services.km.model.MigratableReferenceId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verint.services.km.dao.ContentDAO;
import com.verint.services.km.dao.ISETDAO;
import com.verint.services.km.dao.SearchDAO;
import com.verint.services.km.errorhandling.AppErrorCodes;
import com.verint.services.km.errorhandling.AppErrorMessage;
import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.MigratableReferenceId;
import com.verint.services.km.model.IsetResponse;
import com.verint.services.km.model.ContentRequest;
import com.verint.services.km.model.ContentResponse;

@Path("/hovertext")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Service
public class HoverTextService extends BaseService{
	private final Logger LOGGER = LoggerFactory.getLogger(HoverTextService.class);
	
	@Autowired
	private ISETDAO isetDAO;
	
	@Autowired
	private ContentDAO contentDAO;
	
	@Autowired
	private SearchDAO searchDAO;
	
	/**
	 * 
	 */
	public HoverTextService(){
		super();
		LOGGER.debug("Entering HoverText()");
		LOGGER.debug("Exiting HoverText()");
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args){
	}
	
	/**
	 * 
	 * @param userid
	 * @return
	 */
	@GET
	@Path("/hoverid/{hoverid}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public HoverTextResponse hovertext(@PathParam("hoverid") String hoverId,
    		@QueryParam("contentid") String contentId,
    		@QueryParam("referencename") String referenceName,
    		@QueryParam("appid") String applicationId,
    		@Context HttpServletRequest httpRequest) {
	
		LOGGER.info("Entering hovertext()");
		LOGGER.debug("ContentID: " + contentId + " Reference Name: " + referenceName + " HoverId: " + hoverId + " ApplicationId: " + applicationId);
		final HoverTextResponse hoverTextResponse = new HoverTextResponse();
		
		Boolean isError = false;
		
		try {
		
			// Get the authentication information
			final String[] credentials = getAuthenticatinCredentials(httpRequest);
			LOGGER.debug("Username: " + credentials[0]);
			LOGGER.debug("Password: " + credentials[1]);
						
			ContentResponse contentResponse = null;
			
			// Check for a valid request
			if (hoverId == null || hoverId.equals(""))  {
				LOGGER.error("No hover ID provided");
				throw new AppException(500, AppErrorCodes.HOVERTEXT_HOVERID_ERROR,
					AppErrorMessage.HOVERTEXT_HOVERID_ERROR);
			} else {
				LOGGER.debug("hoverid: " + hoverId);
			}
			
			if (contentId == null && referenceName == null)  {
				LOGGER.error("No content ID or referenceName provided");
				throw new AppException(500, AppErrorCodes.HOVERTEXT_CONTENT_ERROR,
					AppErrorMessage.HOVERTEXT_CONTENT_ERROR);
			} 

			if(contentId != null && !contentId.equals(""))	{
				//The content id is available so use that to get the content
				
			} else if (referenceName != null && !referenceName.equals("")){
				//use the reference name to get the content id
				
				//use the ISET DAO to get the migratableReferenceId
				Set<MigratableReferenceId> migratableReferenceId = isetDAO.getISETResponse(referenceName,"all","");
				
				if (migratableReferenceId.size() == 1){
					//found the if
					MigratableReferenceId myMigRefId = migratableReferenceId.iterator().next();
					
					contentId = myMigRefId.getMigratableReferenceId();
									
					LOGGER.info("migratableReferenceId: " + contentId);
				} else if (migratableReferenceId.size() == 0){
					// no id found
					hoverTextResponse.setErrorString("No content found with the reference name: " + referenceName);
					isError = true;
				} else {
					//more than one id found, something is wrong with the table
					hoverTextResponse.setErrorString("Multiple pieces of content found with the reference name: " + referenceName);
					isError = true;
				}
			} else {
				LOGGER.error("No content ID or referenceName provided");
				throw new AppException(500, AppErrorCodes.HOVERTEXT_CONTENT_ERROR,
					AppErrorMessage.HOVERTEXT_CONTENT_ERROR);
			}
			
			
			if (!isError){
				//Need to get the piece of content
				// Create the ContentRequest
				final ContentRequest contentRequest = new ContentRequest();
				contentRequest.setContentId(contentId);
				contentRequest.setUsername(credentials[0]);
				contentRequest.setPassword(credentials[1]);			
				
				// Call the service
				try{
					
					contentResponse = contentDAO.getContent(contentRequest);
										
					// Mark content as viewed for reporting
					contentResponse.setViewUUID(searchDAO.markAsViewed(contentId, credentials[0], credentials[1], null ));
				
				} catch(Exception ex){
					isError =true;
					LOGGER.error("Unexpected exception in hovertext() Message: " + ex.getMessage());
					hoverTextResponse.setErrorString(ex.getMessage());
				}
				
				if (!isError){
					ArrayList<String> hoverIdSpans = new ArrayList<String>();
					
					hoverIdSpans = findHoverIdText(hoverId, contentResponse.getPublicBody(), hoverIdSpans);
					hoverIdSpans = findHoverIdText(hoverId, contentResponse.getPrivateBody(), hoverIdSpans);
					hoverIdSpans = findHoverIdText(hoverId, contentResponse.getPrivateAnswer(), hoverIdSpans);
					hoverIdSpans = findHoverIdText(hoverId, contentResponse.getPublicAnswer(), hoverIdSpans);
					
					if (hoverIdSpans.size() == 1){
						//found the span
						hoverTextResponse.setHoverTextString(hoverIdSpans.get(0));
					} else if (hoverIdSpans.size() == 0){
						//No span found
						hoverTextResponse.setErrorString("No <span> tags found with the id of '" + hoverId + "'");
						isError = true;
					} else {
						//More than one span found
						hoverTextResponse.setErrorString("Multiple <span> tags found with the id of '" + hoverId + "' Values: " + hoverIdSpans.toString());
						isError = true;
					}
				}
			}
			
		}catch (AppException ae) {
			LOGGER.error("AppException in hovertext()", ae);
			throw ae;
		} catch (Throwable t) {
			LOGGER.error("Unexpected exception in hovertext()", t);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
		}
		
		return hoverTextResponse;
		
	}
	
	private ArrayList<String> findHoverIdText(String hoverId, String data, ArrayList<String> hoverSpans){
	
		LOGGER.info("Entering findHoverIdText()");
		
		
		String searchSingleQuote = "id='" + hoverId + "'";
		String searchDoubleQuote = "id=\"" + hoverId + "\"";
		String spanStartTag = "<span";
		String spanStartTagEnd = ">";
		String spanEndTag = "</span>";
		String hoverText = "";
		Boolean isIdAttribute = false;
		
		
		
		int indexOfHoverId = data.indexOf(hoverId);
		
		if (indexOfHoverId == -1){
			LOGGER.debug("hoverId: '" + hoverId + "' was not found in the data");
		}
		
		while (indexOfHoverId > -1){
			LOGGER.debug("indexOfHoverId: " + indexOfHoverId);
			
			isIdAttribute = false;
			//found the hoverId need to check if it's an id or just text
			//make sure the index number is above 4, the spaces id='  takes up
			if(indexOfHoverId  > 4 && (indexOfHoverId + hoverId.length() + 1 <= data.length())){
				//4 = spaces id=' or id-"  and + 1 gets the "
				String idAttribute = data.substring(indexOfHoverId - 4, indexOfHoverId + hoverId.length() + 1);
				LOGGER.debug("idAttribute: " + idAttribute);				
				if (idAttribute.equals(searchSingleQuote) ||  idAttribute.equals(searchDoubleQuote)){
					isIdAttribute = true;
				}
			}
			LOGGER.debug("isIdAttribute: " + isIdAttribute);
			
			if (isIdAttribute){
				
				int indexOfBeginSpan = data.substring(0, indexOfHoverId).lastIndexOf(spanStartTag);
				LOGGER.debug("indexOfBeginSpan: " + indexOfBeginSpan);
				
				if (indexOfBeginSpan >= -1){
					//found a span tag
					int indexEndOfSpan = data.indexOf(spanEndTag, indexOfBeginSpan);
					LOGGER.debug("indexEndOfSpan: " + indexEndOfSpan);
					
					if (indexEndOfSpan > -1){
						//found the end tag
						
						String tag = data.substring(indexOfBeginSpan, indexEndOfSpan + spanEndTag.length());
						LOGGER.debug("tag: " + tag);
						
						//Need to check for nested <span> tags
						int nestedCount = 0;
						int indexNestedSpanTag = tag.indexOf(spanStartTag, spanStartTag.length());
							
						LOGGER.debug("indexNestedSpanTag: " + indexNestedSpanTag);
						
						while (indexNestedSpanTag > -1){
							nestedCount++;
							indexNestedSpanTag = tag.indexOf(spanStartTag, indexNestedSpanTag + spanStartTag.length());
						}
						
						LOGGER.debug("nestedCount: " + nestedCount);
						
						//Now we know how many nested span tags there are in nestedCount, there should be same number of corresponding end tags
						if (nestedCount > 0){
							//need to grab all the nested span tags inside the one we have
							int i;
							
							int indexOflastSpanEndTag;
							
							for (i=0; i<nestedCount; i++){
								indexOflastSpanEndTag = data.indexOf(spanEndTag, indexEndOfSpan + spanEndTag.length());
								LOGGER.debug("indexOflastSpanEndTag: " + indexOflastSpanEndTag);
								
								if (indexOflastSpanEndTag > -1){
									indexEndOfSpan = indexOflastSpanEndTag;
									LOGGER.debug("indexEndOfSpan: " + indexEndOfSpan);
								}							
							}
		
							//now we know were the last span tag is, this string should contain the span tag for hoverId and all the nested <span> tags
							tag = data.substring(indexOfBeginSpan, indexEndOfSpan +  spanEndTag.length());
							
							//Need to remove the start and end tag so we have the text
							int indexOfGT = tag.indexOf(spanStartTagEnd);
							
							if (indexOfGT > -1){
								//found the end of the start tag
								int indexOfLastSpanEndTag = tag.lastIndexOf(spanEndTag);
								
								if (indexOfLastSpanEndTag > -1){
									//save the text to return
									hoverText = tag.substring(indexOfGT + spanStartTagEnd.length(), indexOfLastSpanEndTag);
									hoverSpans.add(hoverText);
									
									//need to set the data up for the next loop
									data = data.substring(indexEndOfSpan + spanEndTag.length());
									indexOfHoverId = data.indexOf(hoverId);
									
								} else {
									LOGGER.error("Could not find the </span> - data: " + tag);
									indexOfHoverId = -1;
								}							
							} else {
								// Could not find the > sign to complete the <span tag
								LOGGER.error("Could not find the > sign to complete the <span tag - data: " + tag);
								indexOfHoverId = -1;
							}	
						} else {
							// no nested <span tags
							LOGGER.debug("No nested <span> tags found");
							
							//Need to remove the start and end tag so we have the text
							int indexOfGT = tag.indexOf(spanStartTagEnd);
							
							if (indexOfGT > -1){
								//found the end of the start tag
								int indexOfLastSpanEndTag = tag.lastIndexOf(spanEndTag);
								
								if (indexOfLastSpanEndTag > -1){
									//save the text to return
									hoverText = tag.substring(indexOfGT + spanStartTagEnd.length(), indexOfLastSpanEndTag);
									hoverSpans.add(hoverText);
									
									//need to set the data up for the next loop
									data = data.substring(indexEndOfSpan + spanEndTag.length());
									indexOfHoverId = data.indexOf(hoverId);
									
								}else {
									LOGGER.error("Could not find the </span> - data: " + tag);
									indexOfHoverId = -1;
								}
							} else {
								// Could not find the > sign to complete the <span tag
								LOGGER.error("Could not find the > sign to complete the <span tag - data: " + tag);
								indexOfHoverId = -1;
							}						
						}						
					} else{
						//did not find the </span> tag
						LOGGER.error("Could not find the </span> tag - data: " + data.substring(indexOfBeginSpan));
						indexOfHoverId = -1;
					}
				} else {
					//Did not find the <span in front of the hoverID
					LOGGER.error("Found hoverId '" + hoverId + "' but unable to find the '<span' tag - data: " + data.substring(0, indexOfHoverId));
					indexOfHoverId = -1;
				}
				
			} else {
				//was not an id attribute
				//need to set the data up for the next loop
				data = data.substring(indexOfHoverId + hoverId.length());
				indexOfHoverId = data.indexOf(hoverId);
			}
		}
		
		LOGGER.info("Exiting findHoverIdText()");		
		return hoverSpans;
					
	}
		
		

}
