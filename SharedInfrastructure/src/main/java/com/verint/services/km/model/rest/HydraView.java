package com.verint.services.km.model.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class HydraView {
    @JsonProperty("@id")
    private String id;
    @JsonProperty("@type")
    private List<String> type;
    @JsonProperty("hydra:viewStartItem")
    private String viewStartItem;
    @JsonProperty("hydra:viewPageSize")
    private String viewPageSize;
    @JsonProperty("hydra:first")
    private String first;
    @JsonProperty("hydra:previous")
    private String previous;
    @JsonProperty("hydra:next")
    private String next;
    @JsonProperty("hydra:last")
    private String last;

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

    public String getViewStartItem() {
        return viewStartItem;
    }

    public void setViewStartItem(String viewStartItem) {
        this.viewStartItem = viewStartItem;
    }

    public String getViewPageSize() {
        return viewPageSize;
    }

    public void setViewPageSize(String viewPageSize) {
        this.viewPageSize = viewPageSize;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }
}
