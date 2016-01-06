package com.springdemo.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springdemo.entity.User;
import com.springdemo.service.UserService;

@Controller
@RequestMapping(value = "/session")
public class SessionController {
	private Logger logger = LoggerFactory.getLogger(SessionController.class);

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam String userName, @RequestParam String password, HttpServletRequest req) {
		logger.info("try to login with user name : {}, password: {}", userName, password);
		User user = userService.getUser(userName, password);
		
		if(user != null){
			req.getSession().setAttribute("user", user);
		}
		return "content";
	}
}
