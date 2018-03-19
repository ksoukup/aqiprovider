package com.fdm.dao;

import java.util.List;

import com.fdm.model.User;

public interface UserDao {

	public void addUser(User user);
	public void removeUser(String userName);
	public void updateUser(User user);
	public User getUser(String userName);
	public List<User> getAllUsers();
	
}
