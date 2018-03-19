package com.fdm.controllers;

import java.io.Serializable;
import org.junit.Before;
import org.junit.Test;

import com.fdm.commands.ReadCommand;
import com.fdm.commands.WriteCommand;
import com.fdm.dao.UserDao;
import com.fdm.factories.UserFactory;
import com.fdm.model.User;
import static org.mockito.Mockito.*;

public class RegistrationControllerTest {
	private final UserFactory factory = mock(UserFactory.class);
	private final UserDao userDao = mock(UserDao.class);
	private final RegistrationController controller = new RegistrationController(factory, userDao);
	private final User user = mock(User.class);
	
	@Before
	public void setUp(){
		when(factory.createUser()).thenReturn(user);
	}
	
	@Test
	public void register_user_should_get_factory_to_create_user(){
		controller.registerUser("username", "password", "role");
		
		verify(factory).createUser();
	}
	
	@Test
	public void should_set_user_name_password_and_role_on_the_user_returned_by_the_factory(){
		controller.registerUser("username", "password", "role");
		
		verify(user).setUsername("username");
		verify(user).setPassword("password");
		verify(user).setRole("role");
	}
	
	@Test
	public void register_user_should_write_user(){
		controller.registerUser("username", "password", "role");
		
		verify(userDao).addUser(user);
	}
}
