package com.verint.services.km.dao;

import java.util.LinkedHashSet;

import com.verint.services.km.model.Tag;

public interface TeamKBaseTagsDAO {
	
	public LinkedHashSet<Tag> getAllTeamKBaseTags(String username, String password) throws Exception;

}
