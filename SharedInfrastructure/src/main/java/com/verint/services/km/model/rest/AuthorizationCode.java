package com.verint.services.km.model.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AuthorizationCode {

	@JsonProperty("code")
	private String code;

	public String getCode() {
		 return code;
	}

	public void setCode(String code) {
		 this.code = code;
	}

	public String toString() {
		 return "{\"code\":\"" + code + "\"}";
	 }
}
