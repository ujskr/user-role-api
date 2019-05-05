package com.proj.userrole.controller;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.entity.quiz.UserRole;
import com.proj.userrole.service.UserRoleService;

@RestController
public class UserRoleController {
	private final UserRoleService userRoleService;

	public UserRoleController(UserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}

	@GetMapping("/user/getallroles")
	private ResponseEntity<UserRole> fetchUserRole() throws SQLException{
		userRoleService.fetchUserRole();
		return new ResponseEntity<UserRole>(HttpStatus.OK);
	}
}
