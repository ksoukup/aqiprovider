package com.fdm.commands;

import com.fdm.dao.UserDao;
import com.fdm.dao.UserDaoSQLImpl;
import com.fdm.factories.DbSingelton;
import com.fdm.model.User;

public class DbReadCommand implements ReadCommand {

	
	public User readuser(String userName) {
		UserDao userDao = new UserDaoSQLImpl();
		return userDao.getUser(userName);
	}

}
