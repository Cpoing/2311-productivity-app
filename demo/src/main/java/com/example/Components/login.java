package com.example.Components;

import com.example.DB.DB;

/**
 * This class describes the login function of the application.
 * 
 * userList is the list of users' information in a hash map.
 */
public class login {
    private DB db;

    /**
     * login is the constructor for login.
     */
    public login(){
        this.db = new DB();
    }
    /**
     * Register is the method used to add user information into the hash map
     * @param user is the user object.
     */
    public void register(User user){
        db.insertUserInfo(user.getUsername(), user.getPassword());
    }
    /**
     * loginTo is the method that authenticates whether the user is registered.
     * @param id is the username.
     * @param password is the password.
     * @return True if the user exists in the system, false if the user does not (Needs to register first).
     */
    public boolean loginTo(String id, String password){
        String Password = db.getPassword(id);
        if(!Password.isEmpty() && Password.equals(password)){
            return true;
        } else {
            return false;
        }
    }
    /**
     * size is the method used to display the amount of users there are.
     * @return the size of the list.
     */
    public int size() {
    	return db.getNumberofUsers();
    }

}
