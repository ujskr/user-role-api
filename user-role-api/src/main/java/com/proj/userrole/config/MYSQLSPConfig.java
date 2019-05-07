package com.proj.userrole.config;

import static com.proj.userrole.constants.DatabaseConstants.IN_USER_ROLE;
import static com.proj.userrole.constants.DatabaseConstants.IN_USER_ROLE_ID;
import static com.proj.userrole.constants.DatabaseConstants.OUT_PARAMETER_PK_VALUE;
import static com.proj.userrole.constants.DatabaseConstants.OUT_PARAMETER_ROW_COUNT;
import static com.proj.userrole.constants.DatabaseConstants.RESULTS_GET_ALL;
import static com.proj.userrole.constants.DatabaseConstants.RESULTS_GET_BYID;
import static com.proj.userrole.constants.DatabaseConstants.SCHEMA_NAME;
import static com.proj.userrole.constants.DatabaseConstants.SP_NAME_UPDURLS;
import static com.proj.userrole.constants.DatabaseConstants.SP_NAME_URPOST0;
import static com.proj.userrole.constants.DatabaseConstants.SP_NAME_USRRALL;
import static com.proj.userrole.constants.DatabaseConstants.SP_NAME_USRRBID;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
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
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource).withSchemaName(SCHEMA_NAME)
				.withProcedureName(SP_NAME_USRRALL)
				.declareParameters(new SqlOutParameter(OUT_PARAMETER_ROW_COUNT, Types.INTEGER))
				.returningResultSet(RESULTS_GET_ALL, new UserRoleRepoMapper(userRoleMapper));
		return simpleJdbcCall;
	}

	@Bean("createUSRRBIDSPInstance")
	public SimpleJdbcCall createUSRRBIDSPInstance() {
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource).withSchemaName(SCHEMA_NAME)
				.withProcedureName(SP_NAME_USRRBID).declareParameters(new SqlParameter(IN_USER_ROLE_ID, Types.VARCHAR))
				.returningResultSet(RESULTS_GET_BYID, new UserRoleRepoMapper(userRoleMapper));
		return simpleJdbcCall;
	}

	@Bean("createURPOST0SPInstance")
	public SimpleJdbcCall createURPOST0SPInstance() {
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource).withSchemaName(SCHEMA_NAME)
				.withProcedureName(SP_NAME_URPOST0).declareParameters(new SqlParameter(IN_USER_ROLE_ID, Types.VARCHAR))
				.declareParameters(new SqlParameter(IN_USER_ROLE, Types.VARCHAR))
				.declareParameters(new SqlOutParameter(OUT_PARAMETER_PK_VALUE, Types.VARCHAR));
		simpleJdbcCall.compile();
		return simpleJdbcCall;
	}

	@Bean("createUPDURLSSPInstance")
	public SimpleJdbcCall createUPDURLSSPInstance() {
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource).withSchemaName(SCHEMA_NAME)
				.withProcedureName(SP_NAME_UPDURLS).declareParameters(new SqlParameter(IN_USER_ROLE_ID, Types.VARCHAR))
				.declareParameters(new SqlParameter(IN_USER_ROLE, Types.VARCHAR))
				.declareParameters(new SqlOutParameter(OUT_PARAMETER_ROW_COUNT, Types.INTEGER));
		simpleJdbcCall.compile();
		return simpleJdbcCall;
	}
}
