package com.springdemo.controller;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springdemo.entity.User;
import com.springdemo.error.AppException;
import com.springdemo.json.JsonResponse;
import com.springdemo.json.JsonResponseWithData;
import com.springdemo.service.UserService;

/**
 * controller of the CRUD operation for user data
 * 
 * @author kenny
 * 
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	/**
	 * Listing all users
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public JsonResponseWithData<List<User>> getAllUsers() {
		JsonResponseWithData<List<User>> response = new JsonResponseWithData<List<User>>();
		try {
			List<User> userList = userService.getAllUsers();
			response.setData(userList);
		}
		catch (Exception e) {
			response.setSuccess(false);
			response.setMessage(e.getMessage());
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return response;
	}

	/**
	 * Reading a single user
	 */
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public JsonResponseWithData<User> getUser(@PathVariable Long userId) {
		JsonResponseWithData<User> response = new JsonResponseWithData<User>();
		try {
			User user = userService.getUser(userId);
			response.setData(user);
		}
		catch (Exception e) {
			response.setSuccess(false);
			response.setMessage(e.getMessage());
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return response;
	}

	/**
	 * Creating a single user
	 */
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public JsonResponseWithData<User> createUser(@RequestBody User user) {
		JsonResponseWithData<User> response = new JsonResponseWithData<User>();
		try {
			userService.saveUser(user);
			response.setData(user);
		}
		catch (Exception e) {
			response.setSuccess(false);
			response.setMessage(e.getMessage());
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return response;
	}

	/**
	 * Updating a single user
	 */
	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	public JsonResponseWithData<User> updateUser(@PathVariable Long userId, @RequestBody User user) {
		JsonResponseWithData<User> response = new JsonResponseWithData<User>();
		try {
			User oldUser = userService.getUser(userId);
			if (oldUser != null) {
				oldUser.setEmail(user.getEmail());
				oldUser.setName(user.getName());
				userService.saveUser(oldUser);
				response.setData(oldUser);
			}
		}
		catch (Exception e) {
			response.setSuccess(false);
			response.setMessage(e.getMessage());
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return response;
	}

	/**
	 * Deleting a single user
	 */
	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	public JsonResponse deleteUser(@PathVariable Long userId) {
		JsonResponse response = new JsonResponse();
		try {
			userService.deleteUser(userId);
		}
		catch (AppException e) {
			response.setSuccess(false);
			response.setMessage(e.getMessage());
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return response;
	}
}
