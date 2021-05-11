package com.verint.services.km.model.rest;

import java.math.BigInteger;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MemberDetails {
	
    //@JsonProperty("@id")
    //private String id; 
    //@JsonProperty("@type")
    //private String type;
    
    @JsonProperty("vkm:category")
    private List<Category> category;
    
    // modified date
    @JsonProperty("vkm:dateModified")
    private String dateModified;
    
    // published date
    @JsonProperty("vkm:datePublished")
    private String datePublished;
    
    // Aggregate Rating
    @JsonProperty("vkm:aggregateRating")
    private AggregateRating aggregateRating;

    // description
    @JsonProperty("vkm:description")
    private String description;
    
    // is featured
    @JsonProperty("vkm:featured")
    private boolean featured;

    //locale
    @JsonProperty("vkm:inLanguage")
    private String inLanguage;
    
    // title
    @JsonProperty("vkm:name")
    private String name;
    
    // relevance
    @JsonProperty("vkm:relevance")
    private String relevance;
    
    // rating
    @JsonProperty("vkm:ratingValue")
    private String rating;
    
    // url
    @JsonProperty("vkm:url")
    private String url;
    
    // view count
    @JsonProperty("vkm:userInteractionCount")
    private BigInteger userInteractionCount;
    
    // version
    @JsonProperty("vkm:version")
    private String version;

    @JsonProperty("vkm:annotations")
	private List<Annotation> annotations;

    public MemberDetails() {

	}

	public List<Category> getCategory() {
		return category;
	}

	public void setCategory(List<Category> category) {
		this.category = category;
	}

	public String getDateModified() {
		return dateModified;
	}

	public void setDateModified(String dateModified) {
		this.dateModified = dateModified;
	}

	public String getDatePublished() {
		return datePublished;
	}

	public void setDatePublished(String datePublished) {
		this.datePublished = datePublished;
	}

	public AggregateRating getAggregateRating() {
		return aggregateRating;
	}

	public void setAggregateRating(AggregateRating aggregateRating) {
		this.aggregateRating = aggregateRating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRelevance() {
		return relevance;
	}

	public void setRelevance(String relevance) {
		this.relevance = relevance;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public BigInteger getUserInteractionCount() {
		return userInteractionCount;
	}

	public void setUserInteractionCount(BigInteger userInteractionCount) {
		this.userInteractionCount = userInteractionCount;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<Annotation> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<Annotation> annotations) {
		this.annotations = annotations;
	}
}
