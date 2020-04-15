package com.econsultation.service;

import org.springframework.stereotype.Service;

@Service
//@Component
public class UserValidationService {


	public boolean isUserValid(String user, String pwd)
	{
		if (user.equals("Saurabh") && pwd.equals("Password"))
			return true;
		
		return false;
	}
}
