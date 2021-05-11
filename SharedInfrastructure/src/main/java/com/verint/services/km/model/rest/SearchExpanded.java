package com.verint.services.km.model.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SearchExpanded {
    @JsonProperty("@type")
    private List<String> type;
    @JsonProperty("id")
    private String id;
    @JsonProperty("hydra:totalItems")
    private int totalItems;
    @JsonProperty("hydra:member")
    private List<HydraMember> hydraMember = new ArrayList();
    @JsonProperty("vkm:searchCriteria")
    private SearchCriteria searchCriteria;

    public SearchExpanded() {

    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public List<HydraMember> getHydraMember() {
        return hydraMember;
    }

    public void setHydraMember(List<HydraMember> hydraMember) {
        this.hydraMember = hydraMember;
    }

    public SearchCriteria getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }
}
