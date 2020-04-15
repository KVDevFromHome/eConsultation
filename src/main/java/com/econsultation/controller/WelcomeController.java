package com.econsultation.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.econsultation.service.WelcomeService;

@Controller
public class WelcomeController {

	// Auto wiring
	@Autowired
	private WelcomeService service;
//
//	@Autowired
//	private BasicConfiguration configuration;

	@GetMapping("/login")
	public String login() {
		return "login-form";
	}

	//@RequestMapping(value = "/", method = RequestMethod.GET)
	@GetMapping("/")
	public String showLoginPage(ModelMap model) {
		model.addAttribute("time", LocalDateTime.now().toString());
		model.put("name", service.getLoggedinUserName());
		return "welcome";//"login-form";
	}
	
	//@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	@GetMapping("/welcome")
	public String showWelcomePage(ModelMap model) {
		model.addAttribute("time", LocalDateTime.now().toString());
		model.put("name", service.getLoggedinUserName());
		return "welcome";
	}

	//@RequestMapping(value = "/welcome	", method = RequestMethod.POST)
	@PostMapping ("/welcome")
	public String loginAction(ModelMap model) {
		model.addAttribute("time", LocalDateTime.now().toString());
		model.put("userName", service.getLoggedinUserName());
		return "welcome";
	}


//	@RequestMapping("/dynamic-configuration")
//	public Map dynamicConfiguration() {
//		Map map = new HashMap();
//		map.put("message", configuration.getMessage());
//		map.put("number", configuration.getNumber());
//		map.put("value", configuration.isValue());
//		return map;
//	}

}
