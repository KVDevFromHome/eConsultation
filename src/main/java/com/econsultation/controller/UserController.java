package com.econsultation.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.econsultation.jpa.UserRepository;
import com.econsultation.jpa.UserRoleRespository;
import com.econsultation.model.User;
import com.econsultation.model.UserRole;
import com.econsultation.service.UserRoleService;
import com.econsultation.service.UserService;
import com.econsultation.service.WelcomeService;

@Controller
public class UserController {
	
//	private static final long PATIENT = 1l;

	private static final Logger log = LoggerFactory
			.getLogger(UserController.class);
	
	@Autowired
	WelcomeService welcomeService;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRoleService roleService;
	
	@Autowired
	UserRoleRespository roleRepo;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date - dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	@RequestMapping(value = "/list-users", method = RequestMethod.GET)
	public String listUsersAction(@RequestParam long roleId, ModelMap model) {
		//String name = getLoggedInUserName(model);
		//model.put("users", repository.findByRoleId(1l)); //called internal method of JPA Repository
		Date endDate = null;
		UserRole role = roleRepo.findUserRoleByRoleId(roleId);
		List<User> findUserByRoleId = userRepo.getActiveUserByRoleIdAndEndDate(role.getRoleId(), endDate);
		model.put("users", findUserByRoleId); //called custom repository method
		model.put("role", roleId);
		model.put("loggedUser", welcomeService.getLoggedinUserName() );
		return "list-users";
	}
	
	@RequestMapping(value = "/add-user", method = RequestMethod.GET)
	public String loadAddUserForm(@RequestParam long roleId, ModelMap model) //throws Throwable 
	{
		log.info("Add User Page - for role = " + roleId);
		UserRole role  = roleRepo.findUserRoleByRoleId(roleId);
		model.addAttribute("user", new User(0l,"","","","","","",role,new Date()));//,null));
		model.addAttribute("userRoles", roleService.fetchUserRoles());
		return "create-user";
	}

	@RequestMapping(value = "/delete-user", method = RequestMethod.GET)
	public String deleteUserAction(@RequestParam long userId) {
		User user = userService.deleteUser(userId);
		Long roleId = user.getUserRole().getRoleId(); 
		return "redirect:/list-users?roleId="+roleId;
	}

	@RequestMapping(value = "/update-user", method = RequestMethod.GET)
	public String showUpdateUserForm(@RequestParam long userId, ModelMap model) {
		User user = userRepo.findByUserId(userId);
		log.info("Update User - form " + user.toString());
		model.addAttribute("userRoles", roleService.fetchUserRoles());
		model.addAttribute("user", user);
		return "create-user";
	}

	@RequestMapping(value = "/update-user", method = RequestMethod.POST)
	public String updateUserAction(ModelMap model, @Valid User user,
			BindingResult result) {

		Long roleId = user.getUserRole().getRoleId();
		user.setUserRole(roleRepo.findUserRoleByRoleId(roleId));
		
		if (result.hasErrors()) {
			log.info("Result : " + result);
			model.addAttribute("userRoles", roleService.fetchUserRoles());
			return "create-user";
		}
		
		userRepo.save(user);
		//service.updateTodo(todo);
		return "redirect:/list-users?roleId="+roleId;
	}

	@RequestMapping(value = "/add-user", method = RequestMethod.POST)
	public String addUserAction(ModelMap model, @Valid User user, BindingResult result) {

		log.info("Add Users Action - user : " + user.toString());
		log.info("Add Users Action -model : " + model.toString());
		log.info("Add Users Action -result : " + result.toString());
		Long roleId = user.getUserRole().getRoleId();
		user.setUserRole(roleRepo.findUserRoleByRoleId(roleId));
		
		if (result.hasErrors()) {
			model.addAttribute("userRoles", roleService.fetchUserRoles());
			System.out.println("Create User Result : " + result);
			return "create-user";
		}
		//user.setUser(getLoggedInUserName(model));

		userRepo.save(user);
		/*service.addTodo(getLoggedInUserName(model), todo.getDesc(), todo.getTargetDate(),
				false);*/
		return "redirect:/list-users?roleId="+roleId;
	}
}
