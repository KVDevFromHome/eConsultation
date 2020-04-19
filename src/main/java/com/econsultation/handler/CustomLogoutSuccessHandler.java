package com.econsultation.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.econsultation.controller.UserController;

public class CustomLogoutSuccessHandler extends
  SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {
 
    //@Autowired
    //private AuditService auditService; 
	private static final Logger log = LoggerFactory
			.getLogger(UserController.class);
 
    @Override
    public void onLogoutSuccess(
      HttpServletRequest request, 
      HttpServletResponse response, 
      Authentication authentication) 
      throws IOException, ServletException {
  
        String refererUrl = request.getHeader("Referer");
        //auditService.track("Logout from: " + refererUrl);
        log.info("Logout from: " + refererUrl);
        super.onLogoutSuccess(request, response, authentication);
    }
}