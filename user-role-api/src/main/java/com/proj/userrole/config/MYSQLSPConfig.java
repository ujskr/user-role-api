package com.proj.userrole.config;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.proj.userrole.mapper.UserRoleMapper;
import com.proj.userrole.repo.mapper.UserRoleRepoMapper;

@Configuration
public class MYSQLSPConfig {

	private final DataSource dataSource;
	private final UserRoleMapper userRoleMapper;

	public MYSQLSPConfig(DataSource dataSource, UserRoleMapper userRoleMapper) {
		this.dataSource = dataSource;
		this.userRoleMapper = userRoleMapper;
	}

	@Bean("createUSRROLESPInstance")
	public SimpleJdbcCall createUSRROLESPInstance() {
		SimpleJdbcCall user_roleSP = new SimpleJdbcCall(dataSource).withSchemaName("dev").withProcedureName("USRRALL")
				.declareParameters(new SqlOutParameter("OUT_PK_VALUE", Types.VARCHAR))
				.returningResultSet("RESULT1", new UserRoleRepoMapper(userRoleMapper));
		return user_roleSP;
	}
}
