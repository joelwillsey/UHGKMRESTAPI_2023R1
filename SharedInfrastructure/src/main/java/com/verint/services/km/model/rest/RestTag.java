package com.verint.services.km.model.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class RestTag {
    @JsonProperty("@context")
    private String context;
    @JsonProperty("@id")
    private String id;
    @JsonProperty("@type")
    private List<String> type;
    @JsonProperty("hydra:totalItems")
    private int totalItems;
    @JsonProperty("iana:start")
    private RestTag start;
    @JsonProperty("iana:up")
    private RestTag up;
    @JsonProperty("vkm:coverage")
    private String coverage;
    @JsonProperty("vkm:depth")
    private int depth;
    @JsonProperty("vkm:identifier")
    private String identifier;
    @JsonProperty("vkm:name")
    private String name;
    @JsonProperty("vkm:retired")
    private boolean retired;
    @JsonProperty("hydra:member")
    private List<RestTag> member;
    @JsonProperty("hydra:view")
    private HydraView view;

    public RestTag() {

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

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public RestTag getStart() {
        return start;
    }

    public void setStart(RestTag start) {
        this.start = start;
    }

    public RestTag getUp() {
        return up;
    }

    public void setUp(RestTag up) {
        this.up = up;
    }

    public String getCoverage() {
        return coverage;
    }

    public void setCoverage(String coverage) {
        this.coverage = coverage;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRetired() {
        return retired;
    }

    public void setRetired(boolean retired) {
        this.retired = retired;
    }

    public List<RestTag> getMember() {
        return member;
    }

    public void setMember(List<RestTag> member) {
        this.member = member;
    }

    public HydraView getView() {
        return view;
    }

    public void setView(HydraView view) {
        this.view = view;
    }
}
