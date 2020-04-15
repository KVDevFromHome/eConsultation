package com.econsultation.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.econsultation.model.UserRole;

@Repository
@Transactional
public interface UserRoleRespository extends JpaRepository<UserRole, Long> //,UserRepositoryCustom 
//extends CrudRepository<User, Long> 
{
	@Query("Select r from UserRole r where endDate is null")
	public List<UserRole> findAll();
	
	//@Query("Select r from UserRole r where r.roleId = ?1 and endDate is null")
	public UserRole findUserRoleByRoleId(long roleId);
}

