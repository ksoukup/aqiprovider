package com.fdm.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {
	
	@Test
	public void setters_should_set_correctly(){
		User user = new User();
		
		user.setPassword("pass");
		user.setUsername("bill");
		user.setRole("role");
		
		assertEquals("pass", user.getPassword());
		assertEquals("bill", user.getUsername());
		assertEquals("role", user.getRole());
	}
}
