package com.econsultation.jpa;

import java.util.Date;
import java.util.List;

import com.econsultation.model.User;

public interface UserRepositoryCustom {
	List<User> getActiveUserByRoleIdAndEndDate(long roleId);
}
