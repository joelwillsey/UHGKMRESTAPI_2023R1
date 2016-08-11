package com.verint.services.km.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.verint.services.km.dbcp.ConnectionPool;
import com.verint.services.km.model.Tag;

@Repository
public class TeamKBaseTagsDAOImpl extends BaseDAOImpl implements TeamKBaseTagsDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(TeamKBaseTagsDAOImpl.class);

	@Autowired
	private TagsDAO tagsDAO;
	
	/**
	 * Hansraj
	 */
	public TeamKBaseTagsDAOImpl() {
		super();
		LOGGER.info("Entering TeamKBaseTagsDAOImpl()");
		LOGGER.info("Exiting TeamKBaseTagsDAOImpl()");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	}

	
	@Override
	public Set<Tag> getAllTeamKBaseTags(String username, String password) throws SQLException {
		LOGGER.info("Entering getAllTeamKBaseTags()");
		Set<Tag> kBaseTagSet = new HashSet<Tag>();
		Tag tagObj;
		// Get the connection and statement
		final Connection connection = ConnectionPool.getConnection();
		PreparedStatement stmt = null;

		// Execute query
		try {
			final String query = "SELECT EVA_TAG_DISPLAY_NAME_LOC.DISPLAY_NAME, KBASE_TAG.TAG_NAME FROM EVA_TAG_DISPLAY_NAME_LOC, (SELECT DISTINCT * FROM EVA_TAGSET_SELECTION WHERE ID IN ( SELECT TAG_SELECTION FROM EVA_ENTITY__TAGSET_SELECTION WHERE ( ENTITY_RELEASE_ID IN ( SELECT DISTINCT ID FROM CCADMIN_IDMAP WHERE KEYSET = 'RELEASE' AND KEYNAME = 'ID' ) AND ENTITY_TYPE_RELEASE_ID IN ( SELECT DISTINCT ID FROM CCADMIN_IDMAP WHERE KEYSET = 'RELEASE' AND KEYNAME = 'ID' ) ) AND ( ENTITY_TYPE_ID = ( SELECT TYPE_ID FROM EVA_ENTITY_DEFINITION WHERE NAME = 'TeamED' ) AND ENTITY_TYPE_ENV_ID IN ( SELECT DISTINCT ID FROM CCADMIN_IDMAP WHERE KEYSET = 'ENV' AND KEYNAME = 'Dflt' ) AND ENTITY_ID IN ( SELECT CE_POSITION.TEAM_ID FROM CE_TEAM ,CE_POSITION ,CE_TEAM_ROLE ,CE_PERSON ,CE_TEAM_LOC WHERE ( CE_TEAM.ID = CE_POSITION.TEAM_ID AND CE_TEAM.ENV_ID = CE_POSITION.TEAM_ENV_ID AND CE_TEAM_ROLE.ID = CE_POSITION.ROLE_ID AND CE_PERSON.ID = CE_POSITION.AGENT_ID AND CE_TEAM_LOC.ID = CE_TEAM.ID AND CE_TEAM_LOC.ENV_ID = CE_TEAM.ENV_ID AND CE_TEAM_LOC.LOCALE = 'en-US' ) AND ( CE_POSITION.AGENT_ID IN ( SELECT CE_AGENT.ID FROM FU_USER ,CE_AGENT WHERE (FU_USER.ID = CE_AGENT.USER_ID) AND FU_USER.USERNAME = ? ) ) AND CE_TEAM.IS_DELETED = 'N' ) AND ENTITY_ENV_ID IN ( SELECT DISTINCT ID FROM CCADMIN_IDMAP WHERE KEYSET = 'ENV' AND KEYNAME = 'Dflt' ) ) ) AND EVA_TAGSET_SELECTION.ENV_ID IN ( SELECT DISTINCT ID FROM CCADMIN_IDMAP WHERE KEYSET = 'ENV' AND KEYNAME = 'Dflt' ) AND RELEASE_ID IN ( SELECT DISTINCT ID FROM CCADMIN_IDMAP WHERE KEYSET = 'RELEASE' AND KEYNAME = 'ID' ) AND TAGSET_NAME = 'kbase') AS KBASE_TAG, EVA_TAG WHERE EVA_TAG.SYSTEM_CODE = KBASE_TAG.TAG_NAME AND EVA_TAG.ID = EVA_TAG_DISPLAY_NAME_LOC.ID";
			stmt = connection.prepareStatement(query);
			stmt.setString(1, username);
			final ResultSet rs = stmt.executeQuery();

			// loop through all records
			while (rs != null && rs.next()) {
				String tag = rs.getString("TAG_NAME");
				String displayName = rs.getString("DISPLAY_NAME");
				tagObj = new Tag();
				tagObj.setSystemTagDisplayName(displayName);
				tagObj.setSystemTagName(tag);
				kBaseTagSet.add(tagObj);
			}
			rs.close();
		} catch (SQLException sqle) {
			LOGGER.error("SQLException", sqle);
			throw sqle;
		} finally {
			if (stmt != null)
				stmt.close();
			if (connection != null)
				connection.close();
		}
		LOGGER.debug("getAllTeamKBaseTags: "  + kBaseTagSet);
		LOGGER.info("Exiting getAllTeamKBaseTags()");
		return kBaseTagSet;
	}

}
