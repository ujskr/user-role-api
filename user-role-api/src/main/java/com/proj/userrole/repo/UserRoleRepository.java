package com.proj.userrole.repo;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.proj.entity.quiz.UserRole;

@Repository
public interface UserRoleRepository {
	public UserRole findAll() throws SQLException;
	
}
