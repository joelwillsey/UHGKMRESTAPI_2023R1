package com.verint.services.km.model.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class VerintOIDCKeys {
    @JsonProperty("keys")
    private List<VerintOIDCKey> keys;

    public List<VerintOIDCKey> getKeys() {
        return keys;
    }

    public void setKeys(List<VerintOIDCKey> keys) {
        this.keys = keys;
    }
}
