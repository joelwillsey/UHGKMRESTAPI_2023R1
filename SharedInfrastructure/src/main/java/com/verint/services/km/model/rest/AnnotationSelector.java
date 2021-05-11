package com.verint.services.km.model.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AnnotationSelector {
    @JsonProperty("@type")
    private List<String> type;
    @JsonProperty("sh:path")
    private List<AnnotationPath> path;
    @JsonProperty("oa:start")
    private int start;
    @JsonProperty("oa:end")
    private int end;

    public AnnotationSelector() {

    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public List<AnnotationPath> getPath() {
        return path;
    }

    public void setPath(List<AnnotationPath> path) {
        this.path = path;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
