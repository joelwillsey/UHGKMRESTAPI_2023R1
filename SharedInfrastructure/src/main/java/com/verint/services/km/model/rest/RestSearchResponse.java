package com.verint.services.km.model.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;

@JsonIgnoreProperties(ignoreUnknown=true)
public class RestSearchResponse {
	
    @JsonProperty("@context")
    private String context;  

    @JsonProperty("@id")
    private String id;

    @JsonProperty("@type")
    private List<String> type;
    
    // total number of results
    @JsonProperty("hydra:totalItems")
    private BigInteger totalItems;
    
    // search feedback url
    @JsonProperty("vkm:feedback")
    private String searchFeedbackURL;
    
    //Hydra member details
    @JsonProperty("hydra:member")
    private List<HydraMember> hydraMember = new ArrayList();
    @JsonProperty("vkm:alternative")
    private List<SearchAlternative> alternative = new ArrayList();
    @JsonProperty("vkm:expanded")
    private List<SearchExpanded> expanded = new ArrayList();

    public RestSearchResponse() {

    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public BigInteger getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(BigInteger totalItems) {
        this.totalItems = totalItems;
    }

    public String getSearchFeedbackURL() {
        return searchFeedbackURL;
    }

    public void setSearchFeedbackURL(String searchFeedbackURL) {
        this.searchFeedbackURL = searchFeedbackURL;
    }

    public List<HydraMember> getHydraMember() {
        return hydraMember;
    }

    public void setHydraMember(List<HydraMember> hydraMember) {
        this.hydraMember = hydraMember;
    }

    public List<SearchAlternative> getAlternative() {
        return alternative;
    }

    public void setAlternative(List<SearchAlternative> alternative) {
        this.alternative = alternative;
    }

    public List<SearchExpanded> getExpanded() {
        return expanded;
    }

    public void setExpanded(List<SearchExpanded> expanded) {
        this.expanded = expanded;
    }
}
