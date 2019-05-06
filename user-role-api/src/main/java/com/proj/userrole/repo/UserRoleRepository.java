package com.proj.userrole.repo;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proj.entity.quiz.UserRole;

@Repository
public interface UserRoleRepository {
	public List<UserRole> findAll() throws SQLException;
	public Boolean saveAll(String userRoleId, String userRole) throws SQLException; 
}
