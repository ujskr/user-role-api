package com.proj.userrole.repo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.proj.entity.quiz.UserRole;
import com.proj.userrole.mapper.UserRoleMapper;

public class UserRoleRepoMapper implements RowMapper<UserRole> {

	private final UserRoleMapper userRoleMapper;

	public UserRoleRepoMapper(UserRoleMapper userRoleMapper) {
		this.userRoleMapper = userRoleMapper;
	}

	@Override
	public UserRole mapRow(ResultSet rs, int rowNum) throws SQLException {
		return userRoleMapper.resultSetToUserRole(rs);
	}

}
