package com.econsultation.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.econsultation.jpa.UserRepository;
import com.econsultation.model.User;



@Service
public class UserService {

	@Autowired
	UserRepository userRepo;
	
	public User deleteUser(long userId) {
		//if(id==1)
		//throw new RuntimeException("Something went wrong");
		//repository.deleteById(userId);
		User user = userRepo.findByUserId(userId);
		user.setEndDate(new Date());
		//service.deleteTodo(id);		
		userRepo.save(user);
		return user;
	}
}
