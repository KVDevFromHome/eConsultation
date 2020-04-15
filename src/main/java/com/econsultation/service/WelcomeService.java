package com.econsultation.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

//Spring to manage this bean and create an instance of this
@Component
public class WelcomeService {

	@Value("${welcome.message}")
	private String welcomeMessage;

	public String retrieveWelcomeMessage() {
		//Complex Method
		return welcomeMessage;
	}
	
	public String getLoggedinUserName() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails) {
			
			return ((UserDetails) principal).getUsername();
		}
		
		return principal.toString();
	}
}