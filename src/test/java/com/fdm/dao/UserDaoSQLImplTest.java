package com.fdm.dao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import com.fdm.factories.DbSingelton;
import com.fdm.model.User;

public class UserDaoSQLImplTest {

	private final DbSingelton dbSingelton = mock(DbSingelton.class);
	private final User user = mock(User.class);
	private final PreparedStatement statement = mock(PreparedStatement.class);
	private final UserDao userDao = new UserDaoSQLImpl();
	private final String sql = "INSERT INTO users (userName, password, role) VALUES (?,?,?);";
	
	@Before
	public void setUp(){
		when(user.getUsername()).thenReturn("userName");
		when(user.getPassword()).thenReturn("password");
		when(user.getRole()).thenReturn("role");
		when(dbSingelton.getPreparedStatement(sql)).thenReturn(statement);
	}

	@Test
	public void createUserDaoSQLReturnsAUserDAO(){

		
		
		assertEquals(UserDaoSQLImpl.class, userDao.getClass());
		
	}
	

}
