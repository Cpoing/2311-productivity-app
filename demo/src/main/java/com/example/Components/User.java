package com.example.Components;

public class User {
    private String username;
	private String password;
	
	public User(String username, String password) throws IllegalArgumentException {
		if(password.length() < 10) {
			throw new IllegalArgumentException();
		} else {
			this.username = username;
			this.password = password;
		}
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
