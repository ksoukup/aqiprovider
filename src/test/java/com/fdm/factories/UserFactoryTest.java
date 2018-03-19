package com.fdm.factories;

import static org.junit.Assert.*;

import org.junit.Test;

import com.fdm.model.User;

public class UserFactoryTest {
	
	@Test
	public void creates_a_user_with_base_values_set_to_null(){
		UserFactory factory = new UserFactory();
		
		User user = factory.createUser();
		
		assertNotNull(user);
		assertNull(user.getUsername());
		assertNull(user.getPassword());
		assertNull(user.getRole());
	}
}
