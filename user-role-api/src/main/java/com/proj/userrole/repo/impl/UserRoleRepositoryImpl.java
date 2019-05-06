package com.proj.userrole.repo.impl;

import static com.proj.userrole.constants.DatabaseConstants.OUT_PARAMETER_PK_VALUE;
import static com.proj.userrole.constants.DatabaseConstants.OUT_PARAMETER_ROW_COUNT;
import static com.proj.userrole.constants.DatabaseConstants.RESULTS_GET_ALL;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.proj.entity.quiz.UserRole;
import com.proj.userrole.repo.UserRoleRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class UserRoleRepositoryImpl implements UserRoleRepository {

	private final SimpleJdbcCall createUSRROLESPInstance;
	private final SimpleJdbcCall createURPOST0SPInstance;

	public UserRoleRepositoryImpl(SimpleJdbcCall createUSRROLESPInstance, SimpleJdbcCall createURPOST0SPInstance) {
		this.createUSRROLESPInstance = createUSRROLESPInstance;
		this.createURPOST0SPInstance = createURPOST0SPInstance;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRole> findAll() throws SQLException {
		log.info("START method findAll in UserRoleRepositoryImpl");
		List<UserRole> userRoles = null;
		int rowCount = 0;
		try {
			Map<String, Object> results = createUSRROLESPInstance.execute();
			userRoles = (List<UserRole>) results.get(RESULTS_GET_ALL);
			rowCount = (int) results.get(OUT_PARAMETER_ROW_COUNT);
		} catch (UncategorizedSQLException ex) {
			log.info("Error in fetching data - " + ex.getLocalizedMessage());
		}
		log.info("END method findAll in UserRoleRepositoryImpl");
		if (rowCount == userRoles.size()) {
			return userRoles;
		} else
			return null;
	}

	@Override
	public Boolean saveAll(String userRoleId, String userRole) throws SQLException {
		log.info("START method saveAll in UserRoleRepositoryImpl");
		try {
			Map<String, Object> results = createURPOST0SPInstance.execute(userRoleId, userRole);
			String pkValue = (String) results.get(OUT_PARAMETER_PK_VALUE);
			log.info("END method saveAll in UserRoleRepositoryImpl");
			if (pkValue.equals(userRoleId)) {
				return true;
			}
		} catch (UncategorizedSQLException ex) {
			log.info("Error in Saving data - " + ex.getLocalizedMessage());
		}
		return false;
	}

}
