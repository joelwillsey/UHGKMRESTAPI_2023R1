package com.verint.services.km.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedHashSet;

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
	public LinkedHashSet<Tag> getAllTeamKBaseTags(String username, String password) throws SQLException {
		LOGGER.info("Entering getAllTeamKBaseTags()");
		LinkedHashSet<Tag> kBaseTagSet = new LinkedHashSet<Tag>();
		Tag tagObj;
		// Get the connection and statement
		//final Connection connection = ConnectionPool.getConnection();
		PreparedStatement stmt = null;

		Connection connection = null;
		
		// Execute query
		try {
			
			LOGGER.info("Entering getAllTeamKBaseTags() - get db connection");
			connection = ConnectionPool.getConnection();
			LOGGER.info("Connection acquired getAllTeamKBaseTags()");
			
			final String query =  "SELECT EVA_TAG_DISPLAY_NAME_LOC.DISPLAY_NAME "
								+ 	",EVA_TAG.SYSTEM_CODE "
								+ "FROM EVA_TAG "
								+ 	",EVA_TAG_DISPLAY_NAME_LOC "
								+ "WHERE EVA_TAG.ID = EVA_TAG_DISPLAY_NAME_LOC.ID "
								+ 	"AND EVA_TAG.ENV_ID = EVA_TAG_DISPLAY_NAME_LOC.ENV_ID "
								+ 	"AND EVA_TAG.RELEASE_ID = EVA_TAG_DISPLAY_NAME_LOC.RELEASE_ID "
								+ 	"AND EVA_TAG_DISPLAY_NAME_LOC.LOCALE = 'en-US' "
								+ 	"AND EVA_TAG.IS_DELETED = 'N' "
								+ 	"AND EVA_TAG.RELEASE_ID = 1 "
								+ 	"AND EVA_TAG.ENV_ID = 666 "
								+ 	"AND EVA_TAG.SYSTEM_CODE IN ("
								+ 		"SELECT DISTINCT TAG_NAME "
								+ 		"FROM EVA_TAGSET_SELECTION "
								+ 		"WHERE ID IN ("
								+ 			"SELECT TAG_SELECTION "
								+ 			"FROM EVA_ENTITY__TAGSET_SELECTION "
								+ 			"WHERE ENTITY_RELEASE_ID = ENTITY_TYPE_RELEASE_ID "
								+ 				"AND ENTITY_TYPE_ID = ("
								+ 					"SELECT TYPE_ID "
								+ 					"FROM EVA_ENTITY_DEFINITION "
								+ 					"WHERE NAME = 'TeamED') "
								+ 				"AND ENTITY_TYPE_ENV_ID = 666 "
								+ 				"AND ENTITY_ENV_ID = 666 "
								+ 				"AND ENTITY_ID IN ("
								+ 					"SELECT DISTINCT CE_POSITION.TEAM_ID "
								+ 					"FROM CE_TEAM "
								+ 						",CE_POSITION "
								+ 						",CE_TEAM_ROLE "
								+ 						",CE_PERSON "
								+ 					"WHERE CE_TEAM.ID = CE_POSITION.TEAM_ID "
								+ 						"AND CE_TEAM.ENV_ID = CE_POSITION.TEAM_ENV_ID "
								+ 						"AND CE_TEAM_ROLE.ID = CE_POSITION.ROLE_ID "
								+ 						"AND CE_PERSON.ID = CE_POSITION.AGENT_ID "
								+ 						"AND CE_POSITION.AGENT_ID = ("
								+ 							"SELECT CE_AGENT.ID "
								+ 							"FROM FU_USER "
								+ 								",CE_AGENT "
								+ 							"WHERE FU_USER.ID = CE_AGENT.USER_ID "
								+ 								"AND FU_USER.USERNAME = ? "
								+ 								"AND FU_USER.IS_DELETED = 'N') "
								+ 						"AND CE_TEAM.IS_DELETED = 'N')"
								+ 					") "
								+ 				"AND EVA_TAGSET_SELECTION.ENV_ID = 666 "
								+ 				"AND RELEASE_ID = 1 "
								+ 				"AND TAGSET_NAME = 'kbase')";
			stmt = connection.prepareStatement(query);
			stmt.setString(1, username);
			
			Instant start = Instant.now();
			final ResultSet rs = stmt.executeQuery();
			Instant end = Instant.now();
			LOGGER.debug("SERVICE_CALL_PERFORMANCE("+username+") - getAllTeamKBaseTags() duration: " + Duration.between(start, end).toMillis() + "ms");


			// loop through all records
			while (rs != null && rs.next()) {
				String tag = rs.getString("SYSTEM_CODE");
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
