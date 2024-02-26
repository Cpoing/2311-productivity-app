package com.example.Components;
/**
 * The User class is the class that describes a user.
 * 
 * username is the Users' username as a String.
 * password is the USers' password as a String.
 */
public class User {
    private String username;
	private String password;
	
	/**
	 * User is the constructor for the user class.
	 * 
	 * @param username is the username of the user.
	 * @param password is the password of the user.
	 * @throws IllegalArgumentException is the exception thrown if the password length is less than 10 characters.
	 */
	public User(String username, String password) throws IllegalArgumentException {
		if(password.length() < 10) {
			throw new IllegalArgumentException();
		} else {
			this.username = username;
			this.password = password;
		}
	}
	/**
	 * getUsername is the method that retrieves a users' username as a String.
	 * @return Users' userrname as a String.
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * getPassword is the method that retrieves a users' password as a String.
	 * @return Users' password as a String.
	 */
	public String getPassword() {
		return password;
	}
}
