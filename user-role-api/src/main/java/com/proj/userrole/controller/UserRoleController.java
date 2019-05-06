package com.proj.userrole.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proj.entity.quiz.UserRole;
import com.proj.userrole.service.UserRoleService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserRoleController {
	private final UserRoleService userRoleService;

	public UserRoleController(UserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}

	@GetMapping("/user/get/allroles")
	private ResponseEntity<List<UserRole>> fetchUserRoleIdAndName() throws SQLException {
		log.info("START method fetchUserRoleIdAndName in UserRoleController");
		List<UserRole> userRoles = userRoleService.fetchUserRoleIdAndName();
		log.info("END method fetchUserRoleIdAndName in UserRoleController");
		if (!ObjectUtils.isEmpty(userRoles)) {
			return new ResponseEntity<>(userRoles, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

	@PostMapping("/user/post/roles")
	private ResponseEntity<Boolean> createUserRoles(@RequestParam String userRoleId, @RequestParam String userRole)
			throws SQLException {
		log.info("START method createUserRoles in UserRoleController");
		Boolean check = userRoleService.insertUserRoles(userRoleId, userRole);
		log.info("END method createUserRoles in UserRoleController");
		return (check == true ? new ResponseEntity<>(true, HttpStatus.OK)
				: new ResponseEntity<>(false, HttpStatus.NO_CONTENT));
	}
}
