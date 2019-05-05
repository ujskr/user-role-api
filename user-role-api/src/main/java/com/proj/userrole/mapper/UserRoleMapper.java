package com.proj.userrole.mapper;

import static com.proj.userrole.constants.DatabaseConstants.STRING_COLUMN_1;
import static com.proj.userrole.constants.DatabaseConstants.STRING_COLUMN_2;

import java.sql.ResultSet;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.proj.entity.quiz.UserRole;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {
	
	@Mapping(target = "userRoleId", expression = STRING_COLUMN_1)
	@Mapping(target = "userRole", expression = STRING_COLUMN_2)
	UserRole resultSetToUserRole(ResultSet rs);
}
