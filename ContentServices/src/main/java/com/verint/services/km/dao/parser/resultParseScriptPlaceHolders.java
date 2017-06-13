package com.verint.services.km.dao.parser;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.verint.services.km.model.ScriptSrc;

public class resultParseScriptPlaceHolders {

	private String data = "";
	private Set<ScriptSrc> externalSrcFiles = new HashSet<ScriptSrc>();
	
	void setData(String data){
		this.data = data;
	}
	
	String getData() {
		return this.data;
	}
	
	void addExternalSrcFiles(ScriptSrc scriptSrcFile){
		this.externalSrcFiles.add(scriptSrcFile);
	}
	
	void setExternalSrcFiles(Set<ScriptSrc> externalSrcFiles){
		this.externalSrcFiles = externalSrcFiles;
	}
	
	Set<ScriptSrc> getExternalSrcFiles() {
		return externalSrcFiles;
	}
}
 