package com.example.Components;

import com.example.DB.DB;
import java.util.HashMap;

/**
 * This class describes the login function of the application.
 * 
 * userList is the list of users' information in a hash map.
 * db is the database.
 */
public class login {
    private HashMap<String, String> userList;
    private DB db;

    /**
     * login is the constructor for login.
     */
    public login(){
        this.userList = new HashMap<>();
        this.db = new DB();
    }
    /**
     * Register is the method used to add user information into the hash map
     * @param user is the user object.
     */
    public void register(User user){
        // if(this.userList.containsKey(user.getUsername())){
        //     throw new IllegalArgumentException();
        // } else {
        //     this.userList.put(user.getUsername(), user.getPassword());
        // }

        //for DB:
        if(user.getUsername().length() <= 0 || user.getPassword().length() < 10 || user.getUsername().equals(db.getID(user.getUsername()))){
            throw new IllegalArgumentException();
        } else {
            db.insertUserInfo(user.getUsername(), user.getPassword());
        }
    }
    /**
     * loginTo is the method that authenticates whether the user is registered.
     * @param id is the username.
     * @param password is the password.
     * @return True if the user exists in the system, false if the user does not (Needs to register first).
     */
    public boolean loginTo(String id, String password){
        // if(this.userList.containsKey(id)) {
        //     if(this.userList.get(id).equals(password)) {
        //         return true;
        //     } else {
        //         return false;
        //     }
        // } else {
        //     throw new IllegalArgumentException();
        // }

        // For DB version:
        String Password = db.getPassword(id);
        if(!Password.isEmpty() && Password.equals(password)){
            return true;
        } else {
            return false;
        }

    }
    /**
     * size is the method used to display the amount of users there are.
     * @return the number of the users.
     */
    public int size() {
    	return userList.size();
    }

}
