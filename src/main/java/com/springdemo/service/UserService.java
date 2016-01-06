package com.springdemo.service;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springdemo.entity.User;
import com.springdemo.error.AppException;
import com.springdemo.error.ErrorCode;
import com.springdemo.repository.UserRepository;

/**
 * service of the CRUD operation for user data
 * 
 * @author kenny
 * 
 */
@Service
@Transactional(readOnly = true)
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Transactional(rollbackFor = AppException.class)
	public void saveUser(User user) throws AppException {
		try {
			userRepository.save(user);
		}
		catch (Exception e) {
			throw new AppException(ErrorCode.RUNTIME_ERROR, ExceptionUtils.getStackTrace(e));
		}
	}
	
	public User getUser(String name, String password) {
		return userRepository.findByNameAndPassword(name, password);
	}

	public User getUser(Long id) {
		return userRepository.findOne(id);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Transactional(rollbackFor = AppException.class)
	public void deleteUser(Long id) throws AppException {
		try {
			userRepository.delete(id);
		}
		catch (Exception e) {
			throw new AppException(ErrorCode.RUNTIME_ERROR, ExceptionUtils.getStackTrace(e));
		}
	}

}
