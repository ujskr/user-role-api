package com.proj.userrole.repo.impl;

import static com.proj.userrole.constants.DatabaseConstants.OUT_PARAMETER_PK_VALUE;
import static com.proj.userrole.constants.DatabaseConstants.OUT_PARAMETER_ROW_COUNT;
import static com.proj.userrole.constants.DatabaseConstants.RESULTS_GET_ALL;
import static com.proj.userrole.constants.DatabaseConstants.RESULTS_GET_BYID;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import com.proj.entity.quiz.UserRole;
import com.proj.userrole.repo.UserRoleRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class UserRoleRepositoryImpl implements UserRoleRepository {

	private final SimpleJdbcCall createUSRROLESPInstance;
	private final SimpleJdbcCall createURPOST0SPInstance;
	private final SimpleJdbcCall createUPDURLSSPInstance;
	private final SimpleJdbcCall createUSRRBIDSPInstance;

	public UserRoleRepositoryImpl(SimpleJdbcCall createUSRROLESPInstance, SimpleJdbcCall createURPOST0SPInstance,
			SimpleJdbcCall createUPDURLSSPInstance, SimpleJdbcCall createUSRRBIDSPInstance) {
		this.createUSRROLESPInstance = createUSRROLESPInstance;
		this.createURPOST0SPInstance = createURPOST0SPInstance;
		this.createUPDURLSSPInstance = createUPDURLSSPInstance;
		this.createUSRRBIDSPInstance = createUSRRBIDSPInstance;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRole> findAll() throws SQLException {
		log.info("START method findAll in UserRoleRepositoryImpl");
		List<UserRole> userRoles = null;
		int rowCount = 0;
		try {
			log.info("SP-USRRALL :: Executing");
			Map<String, Object> results = createUSRROLESPInstance.execute();
			log.info("SP-USRRALL :: Executed Successfully");
			userRoles = (List<UserRole>) results.get(RESULTS_GET_ALL);
			rowCount = (int) results.get(OUT_PARAMETER_ROW_COUNT);
			log.info("END method findAll in UserRoleRepositoryImpl");
			return (rowCount == userRoles.size() ? userRoles : null);
		} catch (UncategorizedSQLException ex) {
			log.info("Error in fetching data - " + ex.getLocalizedMessage());
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserRole findAllById(String userRoleId) throws SQLException {
		log.info("START method findAllById in UserRoleRepositoryImpl");
		List<UserRole> userRoles = null;
		try {
			log.info("SP-USRRBID :: Executing");
			Map<String, Object> results = createUSRRBIDSPInstance.execute(userRoleId);
			log.info("SP-USRRBID :: Executed Successfully");
			userRoles = (List<UserRole>) results.get(RESULTS_GET_BYID);
			log.info("END method findAllById in UserRoleRepositoryImpl");
			return (!ObjectUtils.isEmpty(userRoles) ? userRoles.get(0) : null);
		} catch (UncategorizedSQLException ex) {
			log.info("Error in fetching data - " + ex.getLocalizedMessage());
		}
		return null;
	}

	@Override
	public Boolean saveAll(String userRoleId, String userRole) throws SQLException {
		log.info("START method saveAll in UserRoleRepositoryImpl");
		try {
			log.info("SP-URPOST0 :: Executing");
			Map<String, Object> results = createURPOST0SPInstance.execute(userRoleId, userRole);
			log.info("SP-URPOST0 :: Executed Successfully");
			String pkValue = (String) results.get(OUT_PARAMETER_PK_VALUE);
			log.info("END method saveAll in UserRoleRepositoryImpl");
			return (pkValue.equals(userRoleId) ? true : false);
		} catch (UncategorizedSQLException ex) {
			log.info("Error in Saving data - " + ex.getLocalizedMessage());
		}
		return false;
	}

	@Override
	public Boolean updateById(String userRoleId, String userRole) throws SQLException {
		log.info("START method updateById in UserRoleRepositoryImpl");
		int rowCount = 0;
		try {
			log.info("SP-UPDURLS :: Executing");
			Map<String, Object> results = createUPDURLSSPInstance.execute(userRoleId, userRole);
			log.info("SP-UPDURLS :: Executed Successfully");
			rowCount = (int) results.get(OUT_PARAMETER_ROW_COUNT);
			log.info("END method updateById in UserRoleRepositoryImpl");
			return (rowCount == 1 ? true : false);
		} catch (UncategorizedSQLException ex) {
			log.info("Error in Updating data - " + ex.getLocalizedMessage());
		}
		return false;
	}

}
