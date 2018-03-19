package com.fdm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 
 * User Class which models user registered in the system.  This class is Serializable.
 * 
 * @author kristian.soukup
 * @version 1.0
 * 
 * 
 */

//The @Entity annotation indicates this is a JPA Entity
@Entity

//Define table name if different from Entity name
@Table(name = "registered_user")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userID;
	@Column(name = "user_name")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "role")
	private String role;

	
	/**
	 * Used to set the users username attribute.
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	/**
	 * 
	 * @return String that represents a Password
	 */
	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Username: " + this.username);
		sb.append(" Password: " + this.password);
		sb.append(" Role: " + this.role);
		return sb.toString();
	}

}
