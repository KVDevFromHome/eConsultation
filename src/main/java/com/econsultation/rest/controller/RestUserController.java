package com.econsultation.rest.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.econsultation.controller.UserController;
import com.econsultation.jpa.UserRepository;
import com.econsultation.jpa.UserRoleRespository;
import com.econsultation.model.User;
import com.econsultation.model.UserRole;
import com.econsultation.service.WelcomeService;


@RestController
@RequestMapping(path = "/users")
public class RestUserController {

    @Autowired
    private UserRepository userRepository;
    
	@Autowired
	UserRoleRespository roleRepo;
	
	@Autowired
	WelcomeService welcomeService;

    @GetMapping
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

	private static final Logger log = LoggerFactory
			.getLogger(RestUserController.class);
    
	@PostMapping (consumes = "application/json", produces = "application/json")
	public User updateUserAction(@RequestBody @Valid User userObj) {
		
		return userRepository.save(userObj);
		//return "Result : Success";
	}


	@PutMapping  (consumes = "application/json", produces = "application/json")
	public User addUserAction(@RequestBody @Valid User userObj ) {
		
		return userRepository.save(userObj);
		
	}

	@GetMapping("/roles/{roleId}")
	public List<User> listUsersAction(@PathVariable ("roleId") long roleId) {
		Date endDate = null;
		UserRole role = roleRepo.findUserRoleByRoleId(roleId);
		List<User> usersByRoleId = userRepository.getActiveUserByRoleIdAndEndDate(role.getRoleId(), endDate);
		return usersByRoleId;
	}
	

	@GetMapping("/{userId}")
	public User getUser(@PathVariable long userId) {
		User user = userRepository.findByUserId(userId);
		log.info("Authorities of User " + user.getUserName() + " are " + welcomeService.getPrincipal().getAuthorities());
		if(	welcomeService.getLoggedinUserName().contentEquals(user.getUserName()))
			return user;
		else
			//return new User();
			throw new AccessDeniedException("You don't have access to view details of user: " + user.getUserName());
	}

	

}
