package com.fdm.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdm.commands.ReadCommand;
import com.fdm.commands.WriteCommand;
import com.fdm.dao.UserDao;
import com.fdm.factories.UserFactory;
import com.fdm.model.User;

public class RegistrationController {
	private final UserFactory factory;
	private final UserDao userDao;
	static private Logger logger;
	private int usersRegistered;

	public RegistrationController(UserFactory factory, UserDao userDao) {
		this.factory = factory;
		this.userDao = userDao;
		usersRegistered = 0;
		this.logger = LogManager.getLogger(RegistrationController.class);
		
	}

	public void registerUser(String username, String password, String role) {
		User user = factory.createUser();
		user.setUsername(username);
		user.setPassword(password);
		user.setRole(role);
		userDao.addUser(user);
		logger.trace("Created new user");
	}
	

	
	public User retrieveUser(String username){
		return userDao.getUser(username);
		
	}

}
