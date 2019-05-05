package com.proj.userrole.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.proj.entity.quiz.UserRole;
import com.proj.userrole.repo.UserRoleRepository;

@Service
public class UserRoleService {
	private final UserRoleRepository userRoleRepository;

	public UserRoleService(UserRoleRepository userRoleRepository) {
		this.userRoleRepository = userRoleRepository;
	}
	public UserRole fetchUserRole() throws SQLException {
		return userRoleRepository.findAll();
		
	}
	

}
