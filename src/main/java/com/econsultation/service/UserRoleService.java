package com.econsultation.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.econsultation.jpa.UserRoleRespository;
import com.econsultation.model.UserRole;

@Service
public class UserRoleService {

	@Autowired
	UserRoleRespository roleRepo;
	
	public Map<Long, String> fetchUserRoles() {
		List<UserRole> roles = roleRepo.findAll();
		Map<Long, String> rolesMap = new HashMap<Long, String>();

		for (Iterator<UserRole> iterator = roles.iterator(); iterator.hasNext();) {
			UserRole userRole = iterator.next();
			rolesMap.put(userRole.getRoleId(), userRole.getRoleName());
			
		}
		return rolesMap; 
				//model.addAttribute("userRoles", rolesMap);
	}
}
