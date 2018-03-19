package com.fdm.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdm.factories.DbSingelton;
import com.fdm.factories.UserFactory;
import com.fdm.model.User;
import com.fdm.runner.Client;

public class UserDaoSQLImpl implements UserDao {

	private Logger logger = LogManager.getLogger(UserDaoSQLImpl.class);
	private static EntityManagerFactory emf ;

	
	public UserDaoSQLImpl(){
		
		
		emf = Persistence.createEntityManagerFactory("airquality");
	}

	private EntityManager getEntityManager(){
		return this.emf.createEntityManager();
	}
	
	public void addUser(User user) {
		EntityManager em = this.getEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
		logger.info("Completed adding User " + user.toString() );
	}

	public void removeUser(String userName) {
		// TODO Auto-generated method stub
		
	}

	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	public User getUser(String userName) {
		EntityManager em = this.getEntityManager();
		User user = em.find(User.class, userName);
		em.close();
		logger.info("Got following user " + user);
		return user;
		
			
	}

	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}
	


}
