package com.example.Components;

import java.util.HashMap;

public class login {
    private HashMap<String, String> userList;
    
    public login(){
        this.userList = new HashMap<>();
        
    }

    public void register(User user){
        if(this.userList.containsKey(user.getUsername())){
            throw new IllegalArgumentException();
        } else {
            this.userList.put(user.getUsername(), user.getPassword());
        }
    }

    public boolean loginTo(String id, String password){
       if(this.userList.containsKey(id)) {
    	   if(this.userList.get(id).equals(password)) {
    		   return true;
    	   } else {
    		   return false;
    	   }
       } else {
    	   throw new IllegalArgumentException();
       }
    }
    
    public int size() {
    	return userList.size();
    }

}
