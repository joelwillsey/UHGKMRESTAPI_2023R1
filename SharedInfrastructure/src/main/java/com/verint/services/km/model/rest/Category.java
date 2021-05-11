package com.verint.services.km.model.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Category {
    @JsonProperty("@id")
    private String id;
    @JsonProperty("@type")
    private List<String> type;
    @JsonProperty("vkm:name")
    private String name;
    @JsonProperty("vkm:categoryTag")
    private String categoryTag;

    public Category() {

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryTag() {
        return categoryTag;
    }

    public void setCategoryTag(String categoryTag) {
        this.categoryTag = categoryTag;
    }

    public String getDisplayName() {

        String contentType = null;

        if (this.id.equals("vkm:ArticleCategory")) {
            contentType = "KnowledgeArticleED";
        } else if (this.id.equals("vkm:AlertCategory")) {
            contentType = "KnowledgeAlertED";
        } else if (this.id.equals("vkm:FAQCategory")) {
            contentType = "KnowledgeFAQED";
        } else if (this.id.equals("vkm:UploadedCategory")) {
            contentType = "KnowledgeUploadED";
        } else if (this.id.equals("content_remotedocument")) {
            contentType = "KnowledgeRemoteDocumentED";
        } else if (this.id.equals("vkm:DecisionTreeCategory")) {
            contentType = "pageSet";
        } else if (this.id.equals("vkm:SpideredCategory")) {
            contentType = "Spidered";
        } else if (this.id.equals("content_segment")) {
            contentType = "Segment";
        } else {
            contentType = "";
        }



        return contentType;
    }

    public String getContentCategoryTag() {
        String contentCategoryTag = null;

        if (this.id.equals("vkm:ArticleCategory")) {
            contentCategoryTag = "content_article";
        } else if (this.id.equals("vkm:AlertCategory")) {
            contentCategoryTag = "content_knowledgealert";
        } else if (this.id.equals("vkm:FAQCategory")) {
            contentCategoryTag = "content_faq";
        } else if (this.id.equals("vkm:UploadedCategory")) {
            contentCategoryTag = "content_uploadeddocument";
        } else if (this.id.equals("content_remotedocument")) {
            contentCategoryTag = "content_remotedocument";
        } else if (this.id.equals("vkm:DecisionTreeCategory")) {
            contentCategoryTag = "content_decisiontree";
        } else if (this.id.equals("vkm:SpideredCategory")) {
            contentCategoryTag = "Spidered";
        } else if (this.id.equals("content_segment")) {
            contentCategoryTag = "content_segment";
        } else {
            contentCategoryTag = "";
        }
        // remote document
        // decision tree
        //Spidered Document
        //segment


        return contentCategoryTag;

    }
}
