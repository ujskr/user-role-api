package com.proj.userrole.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.proj.entity.quiz.UserRole;
import com.proj.userrole.repo.UserRoleRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserRoleService {
	private final UserRoleRepository userRoleRepository;

	public UserRoleService(UserRoleRepository userRoleRepository) {
		this.userRoleRepository = userRoleRepository;
	}

	public List<UserRole> fetchUserRoleIdAndName() throws SQLException {
		log.info("START method fetchUserRoleIdAndName in UserRoleService");
		List<UserRole> userRoles = userRoleRepository.findAll();
		log.info("END method fetchUserRoleIdAndName in UserRoleService");
		if (!ObjectUtils.isEmpty(userRoles)) {
			return userRoles;
		} else
			return null;
	}

	public Boolean insertUserRoles(String userRoleId, String userRole) throws SQLException {
		log.info("START method insertUserRoles in UserRoleService");
		Boolean check = userRoleRepository.saveAll(userRoleId, userRole);
		log.info("END method insertUserRoles in UserRoleService");
		return (check == true ? true : false);
	}

}
