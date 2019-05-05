package com.proj.userrole.repo.impl;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.proj.entity.quiz.UserRole;
import com.proj.userrole.repo.UserRoleRepository;

@Repository
public class UserRoleRepositoryImpl implements UserRoleRepository {

	private final SimpleJdbcCall createUSRROLESPInstance;

	public UserRoleRepositoryImpl(@Qualifier("createUSRROLESPInstance") SimpleJdbcCall createUSRROLESPInstance) {
		this.createUSRROLESPInstance = createUSRROLESPInstance;
	}

	@Override
	public UserRole findAll() throws SQLException {
		UserRole userRole = null;
		try {
			Map<String, Object> results = createUSRROLESPInstance.execute();
			userRole = (UserRole) results.get("RESULT1");
		} catch (UncategorizedSQLException ex) {

		}
		return userRole;
	}

}
