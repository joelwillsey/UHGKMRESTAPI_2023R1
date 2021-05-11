package com.verint.services.km.model.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AnnotationTarget {
    @JsonProperty("@type")
    private List<String> type;
    @JsonProperty("oa:source")
    private String source;
    @JsonProperty("oa:selector")
    private List<AnnotationSelector> selector;

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<AnnotationSelector> getSelector() {
        return selector;
    }

    public void setSelector(List<AnnotationSelector> selector) {
        this.selector = selector;
    }
}
