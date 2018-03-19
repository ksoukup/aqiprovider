package com.fdm.commands;

import com.fdm.dao.UserDao;
import com.fdm.dao.UserDaoSQLImpl;
import com.fdm.factories.DbSingelton;
import com.fdm.model.User;

public class DbWriteCommand implements WriteCommand {
	
	public void write(User user) {
		UserDao userDao = new UserDaoSQLImpl();
		userDao.addUser(user);

	}

}
