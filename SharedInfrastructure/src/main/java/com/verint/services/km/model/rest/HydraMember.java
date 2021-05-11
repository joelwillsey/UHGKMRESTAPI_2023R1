package com.verint.services.km.model.rest;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class HydraMember {
	
    @JsonProperty("@id")
    private String id; 

    //@JsonProperty("@type")
    //private String type;
    
    //Hydra member details
    @JsonProperty("hydra:member")
    private List<MemberDetails> memberDetails;

    // number of results
    @JsonProperty("hydra:totalItems")
    private String totalItems;
    
    // contentID
    @JsonProperty("vkm:identifier")
    private String identifier;
    
    // title
    @JsonProperty("vkm:name")
    private String name;

    public HydraMember() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<MemberDetails> getMemberDetails() {
        return memberDetails;
    }

    public void setMemberDetails(List<MemberDetails> memberDetails) {
        this.memberDetails = memberDetails;
    }

    public String getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(String totalItems) {
        this.totalItems = totalItems;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentId() {
        //There is currently an issue in the rest service where the id changes spaces to _
        //When this is passed to EM it is causing a failure because solr is expecting a space instead of a _
        if (this.getMemberDetails() != null && !this.getMemberDetails().isEmpty() && this.getMemberDetails().get(0) != null &&
            this.getMemberDetails().get(0).getCategory() != null && !this.getMemberDetails().get(0).getCategory().isEmpty() &&
                "vkm:DecisionTreeCategory".equals(this.getMemberDetails().get(0).getCategory().get(0).getId())) {
            return this.getMemberDetails().get(0).getUrl().substring(this.getMemberDetails().get(0).getUrl().lastIndexOf("/")+1);
        }
		return id.substring(id.indexOf("-") + 1);
	}
}
