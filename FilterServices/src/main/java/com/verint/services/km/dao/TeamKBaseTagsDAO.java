package com.verint.services.km.dao;

import java.util.Set;

import com.verint.services.km.model.Tag;

public interface TeamKBaseTagsDAO {
	
	public Set<Tag> getAllTeamKBaseTags(String username, String password) throws Exception;

}
