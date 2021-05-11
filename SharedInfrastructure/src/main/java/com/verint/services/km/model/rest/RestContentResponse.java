package com.verint.services.km.model.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.verint.services.km.model.ContentResponse;
import com.verint.services.km.util.ConfigInfo;

import com.verint.services.km.util.RestUtil;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@JsonIgnoreProperties(ignoreUnknown=true)
public class RestContentResponse {
    @JsonProperty("@id")
    private String id;
    @JsonProperty("@context")
    private String context;
    @JsonProperty("@type")
    private List<String> type;
    @JsonProperty("vkm:name")
    private String name;
    @JsonProperty("vkm:category")
    private List<Category> category;
    @JsonProperty("vkm:contentStatus")
    private ContentStatus contentStatus;
    @JsonProperty("vkm:identifier")
    private String identifier;
    @JsonProperty("vkm:dateModified")
    private Date dateModified;
    @JsonProperty("vkm:datePublished")
    private Date datePublished;
    @JsonProperty("vkm:featured")
    private boolean featured;
    @JsonProperty("vkm:inLanguage")
    private String inLanguage;
    @JsonProperty("vkm:userInteractionCount")
    private int userInteractionCount;
    @JsonProperty("vkm:version")
    private String version;
    @JsonProperty("vkm:articleBody")
    private String articleBody;
    @JsonProperty("vkm:privateBody")
    private String privateBody;
    @JsonProperty("vkm:feedback")
    private String feedback;
    @JsonProperty("vkm:annotations")
    private List<Annotation> annotations;
    @JsonProperty("vkm:versionHistory")
    private String versionHistory;
    //hydraOperation
    @JsonProperty("vkm:contentLocales")
    private List<String> contentLocales;
    @JsonProperty("vkm:url")
    private String url;
    @JsonProperty("vkm:customFields")
    private List<CustomField> customFields;
    @JsonProperty("vkm:aggregateRating")
    private AggregateRating aggregateRating;

    public RestContentResponse() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public ContentStatus getContentStatus() {
        return contentStatus;
    }

    public void setContentStatus(ContentStatus contentStatus) {
        this.contentStatus = contentStatus;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public String getInLanguage() {
        return inLanguage;
    }

    public void setInLanguage(String inLanguage) {
        this.inLanguage = inLanguage;
    }

    public int getUserInteractionCount() {
        return userInteractionCount;
    }

    public void setUserInteractionCount(int userInteractionCount) {
        this.userInteractionCount = userInteractionCount;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getArticleBody() {
        return articleBody;
    }

    public void setArticleBody(String articleBody) {
        this.articleBody = articleBody;
    }

    public String getPrivateBody() {
        return privateBody;
    }

    public void setPrivateBody(String privateBody) {
        this.privateBody = privateBody;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<Annotation> annotations) {
        this.annotations = annotations;
    }

    public String getVersionHistory() {
        return versionHistory;
    }

    public void setVersionHistory(String versionHistory) {
        this.versionHistory = versionHistory;
    }

    public List<String> getContentLocales() {
        return contentLocales;
    }

    public void setContentLocales(List<String> contentLocales) {
        this.contentLocales = contentLocales;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<CustomField> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(List<CustomField> customFields) {
        this.customFields = customFields;
    }

    public AggregateRating getAggregateRating() {
        return aggregateRating;
    }

    public void setAggregateRating(AggregateRating aggregateRating) {
        this.aggregateRating = aggregateRating;
    }

    public ContentResponse mapToContentResponse(ContentResponse contentResponse, String externalUrl) throws UnsupportedEncodingException {
        boolean isSpidered = false;
        contentResponse.setPublishedId(this.getIdentifier());
        if (this.getDatePublished() != null) {
            contentResponse.setPublishedDate(new SimpleDateFormat("yyyy-MM-dd").format(this.getDatePublished()));
        }
        if (this.getDateModified() != null) {
            contentResponse.setLastModifiedDate(new SimpleDateFormat("yyyy-MM-dd").format(this.getDateModified()));
        }
//        accessControls = null
        contentResponse.setVersion(this.getVersion());
//        isMigratable = false
        contentResponse.setViewContent(BigInteger.valueOf(this.getUserInteractionCount()));
        contentResponse.setIsFeatured(this.isFeatured());
        if (this.getCategory().get(0).getId().equals("vkm:FAQCategory")) {
            contentResponse.setPublicAnswer(replaceAssetImageURLs(this.getArticleBody(), externalUrl));           
            contentResponse.setPrivateAnswer(replaceAssetImageURLs(this.getPrivateBody(), externalUrl));
        } else if (this.getCategory().get(0).getId().equals("vkm:UploadedCategory")) {
            //If upload remove public body because it will be included in the custom field
        } else if (this.getCategory().get(0).getId().equals("vkm:SpideredCategory")) {
            isSpidered = true;
            contentResponse.setPublicBody("<p><iframe src=\"" + this.getUrl() + "\" width=\"100%\" height=\"450px\"/></p>");
            contentResponse.setPrivateBody("");
        } else {
            contentResponse.setPublicBody(replaceAssetImageURLs(this.getArticleBody(), externalUrl));
            contentResponse.setPrivateBody(replaceAssetImageURLs(this.getPrivateBody(), externalUrl));
        }

//        body = "<body><mustRead>false</mustRead><featured>false</featured><publicBody><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">\n<div class="GTCollapseContent"><div class="pod fluid expanded"><h2 class="title" style="height: auto;" data-mce-style="height: auto;"><span class="expander">Show Hide Header</span></h2><div class="content-holder GTDisplay"><div class="content" style="height: auto;" data-mce-style="height: auto;">Show Hide Content</div><div class="content" style="height: auto;" data-mce-style="height: auto;">Content</div><div class="content" style="height: auto;" data-mce-style="height: auto;">Content</div></div></div></div><p> Test Show/Hide</p><p><br></p><p><br></p><p>To view the test <a onclick="showHideDiv('hidden');return false" target="_blank">click here</a></p><p><br></p><div id="hidden" style="display: none;" data-mce-style="display: none;"><span style="font-size: 11.0pt;font-family: Calibri , sans-serif;" data-mce-style="font-size: 11.0"
//                isDeleted = false
        if (this.getAggregateRating() != null) {
            contentResponse.setAverageRating(Double.parseDouble(this.getAggregateRating().getRatingValue()));
            contentResponse.setNumberOfRatings(BigInteger.valueOf(this.getAggregateRating().getRatingCount()));
        }
//        mustRead = false

        String[] idArr = this.getId().split("/");
        contentResponse.setId(idArr[idArr.length-2]);
//        viewDefinition = null
        contentResponse.setLanguage(this.getInLanguage()); //this was just "en" in SOAP
        contentResponse.setLocale(this.getInLanguage());
//        bodyMap = null
//        description = null
//        translatedTo = {StringItem[0]@48407}
//        weighting = null

//        contentType = "KnowledgeArticleED"
        String[] categoryArr = this.getCategory().get(0).getCategoryTag().split("/");
        contentResponse.setContentCategory(categoryArr[categoryArr.length-1]);
        contentResponse.setLastModifiedDate(this.getDateModified().toString()); //lastModifiedDate = "2020-09-14T16:04:53.179+00:00"
        contentResponse.setTitle(this.getName());
        contentResponse.setContentType(RestUtil.convertContentRestType(this.getCategory().get(0).getId()));
//        contentResponse.setRestFeedbackUrl(this.getFeedback());
//        __equalsCalc = null
//        __hashCodeCalc = false


        if (this.getUrl() != null && !isSpidered) {
            final com.verint.services.km.model.CustomField customField = new com.verint.services.km.model.CustomField();
            customField.setName("File Information");
            String desc = "";
            if (this.getCustomFields() != null) {
                for (CustomField cf : this.getCustomFields()) {
                    if (cf.getFieldName().equals("fileDescription")) {
                        desc = "<p>" + cf.getFieldValue().getFieldValue() + "</p>";
                        break;
                    }
                }
            }
            customField.setData(desc + "<p><iframe src=\"" +
                            "/contentservices/km/asset/getasset?assetUrl=" +
                            URLEncoder.encode(this.getUrl(), StandardCharsets.UTF_8.toString()) +
                            "\" width=\"100%\" height=\"400px\"/></p>");
            contentResponse.addCustomField(customField);
        }
        if (this.getCustomFields() != null && !this.getCustomFields().isEmpty()) {
            for (CustomField customField : this.getCustomFields()) {
                com.verint.services.km.model.CustomField kmCustomField = new com.verint.services.km.model.CustomField();
                kmCustomField.setName(customField.getFieldName());
                kmCustomField.setData(customField.getFieldValue().getFieldValue());
                contentResponse.addCustomField(kmCustomField);
            }
        }
        return contentResponse;
    }

    private String replaceAssetImageURLs(String body, String externalUrl) throws UnsupportedEncodingException {
 
    	
    	Matcher matcher = Pattern.compile("<img.*?src=\"(.*?)\"").matcher(body);
        while (matcher.find()) {
            if (matcher.groupCount() > 0 && matcher.group(1) != null) {
            	
                String imgTag = matcher.group(1);
                if (!StringUtils.isEmpty(imgTag) && !imgTag.contains("ReadLaterGray16x16.png")) {
                	String newImgTag = "";
                	
                	if (imgTag.contains("km-asset-service")){
                		String assetWrapperUrl = "";
                		ConfigInfo kmConfiguration = new ConfigInfo();
                		assetWrapperUrl = kmConfiguration.getAssetWrapperUrl();

                    
                		newImgTag = assetWrapperUrl +
                				URLEncoder.encode(imgTag, StandardCharsets.UTF_8.toString());
                		body = body.replace(imgTag, newImgTag);
                    
                   // String newImgTag = imgTag;
                   // body = body.replace(imgTag, newImgTag);
                    
                	}else {
                		newImgTag = URLEncoder.encode(imgTag, StandardCharsets.UTF_8.toString());
                		
                	}
                }
            }
        }
        
        body = updateAHrefMalformed(body, externalUrl);
        
        
        
        return body;
    }
    
    
	private String updateAHrefMalformed(String body, String externalUrl) {
		// This is really stupid but the content call is returning a link like
		// this
		// <a href='null?gtxResource=/KM/files/uploaded/PNP_UHCG Shared Savings
		// v1_UHG_en-US.doc&gtxResourceFileName=/KM/files/uploaded/PNP_UHCG
		// Shared Savings v1_UHG.doc&mode=download'>/KM/files/uploaded/PNP_UHCG
		// Shared Savings v1_UHG.doc</a>
		// So now I have to handle this malformed link and fix it :(
		
		String AssetServiceURL;

		ConfigInfo kmConfiguration = new ConfigInfo();
		AssetServiceURL = kmConfiguration.getRestKmAssetService();


		//LOGGER.info("Entering updateAHrefMalformed()");
		String finalBody = body;
		int searchIndex = 0;
		int finalbodyIndex = 0;
		// Find the first href="
		int ahrefStart = body.indexOf("<a href=\"null");
		int finalBodyahrefStart = body.indexOf("<a href=\"null");
		;
		while (ahrefStart != -1) {
			searchIndex = ahrefStart + "<a href=\"null".length();
			int aherfEnd = body.indexOf("</a>", ahrefStart + "<a href=\"null".length());
			if (aherfEnd != -1) {
				// we found the end of the ahref
				String hrefSource = body.substring(ahrefStart, aherfEnd + "</a>".length());
				//LOGGER.debug("--- Found malformed href attribute: " + hrefSource);
				int gtxResource = 0;
				gtxResource = hrefSource.indexOf("?gtxResource=");
				String newAhref = "";
				if (gtxResource != -1) {
					finalBody = finalBody.substring(0, finalBodyahrefStart);
					// LOGGER.debug("finalBody Front: [" + finalBody + "]");
					// we found a gtxResource Link, need to change the link
					newAhref = "<a href=\"" + AssetServiceURL
					//newAhref = "<a href=\"" + externalUrl
							+ hrefSource.substring(gtxResource + "?gtxResource=".length());
					newAhref = newAhref.replaceFirst("&gtxResourceFileName=", "?gtxResourceFileName=");
					newAhref = newAhref.replaceFirst("&mode=download", "?mode=download");
					newAhref = newAhref.replaceFirst("'>", "\">");
					newAhref = newAhref.replaceFirst("%5C", "%2F");
					// Now lets fix the title
					int startOfFileName = newAhref.indexOf("gtxResourceFileName=") + "gtxResourceFileName=".length();
					if (startOfFileName != -1) {
						int endOfFileName = newAhref.indexOf("mode=download", startOfFileName);
						if (endOfFileName != -1) {
							//String filePath = newAhref.substring(startOfFileName, endOfFileName);
							//LOGGER.debug("filePath: " + filePath);
							
							/*
							int lastSlash = filePath.lastIndexOf('/');
							
							
							if (lastSlash != -1) {
								if (lastSlash + 1 <= filePath.length()) {
									String fileName = filePath.substring(lastSlash + 1).toString();
									//need this to handle the windows /  ie. /fileStorage/KM/uploaded\EM_15.1_FP1_Release_Pack.pdf
									int lastForwardSlash = fileName.lastIndexOf("%5C");
									fileName = fileName.substring(lastForwardSlash + 1);
									//LOGGER.debug("fileName: " + fileName);
									newAhref = newAhref.replaceAll(Pattern.quote(filePath), fileName);
								}
							}
							*/
							
							
							int startOfDisplayName = newAhref.indexOf("mode=download\">") + "mode=download\">".length();
							if (startOfDisplayName != -1) {
								int endOfDisplayName = newAhref.indexOf("</a>");
								if (endOfDisplayName != -1) {
									String currentFileName = newAhref.substring(startOfDisplayName, endOfDisplayName);

									newAhref = newAhref.replaceAll(Pattern.quote(currentFileName), "");	
								}
							}
							
						}
					}
					
					//LOGGER.debug("--- Transformed malformed href attribute from " + hrefSource + " to " + newAhref);
					// This is the index of where the finalbody is complete
					finalbodyIndex = finalBody.length() - 1 + newAhref.length();
					finalBody = finalBody + newAhref + body.substring(aherfEnd + "</a>".length());
					// LOGGER.debug("Body: [" + body.substring(aherfEnd +
					// "</a>".length()) + "]");
					// LOGGER.debug("finalBody: " + finalBody);
				}
			} else {
				//LOGGER.error(
				//		"Parse error on search for href string, unable to find the </a> after  href='null, started search at index "
				//				+ ahrefStart);
			}
			ahrefStart = body.indexOf("<a href=\"null", searchIndex);
			finalBodyahrefStart = finalBody.indexOf("<a href=\"null", finalbodyIndex);
		}
		//LOGGER.info("finalBody: " + finalBody);
		//LOGGER.info("exiting updateAHrefMalformed()");
		return finalBody;
	}
    
    
    
}
