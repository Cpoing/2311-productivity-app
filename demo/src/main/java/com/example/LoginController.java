package com.example;

import com.example.Components.login;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;

/**
 * This class checkes if the user id and the password is matching. If matching then user can access to the app
 */

public class LoginController {


    List<String> UserList = List.of("user1", "user2", "user3");
    List<String> PasswordList = List.of("password1", "password2", "password3");

    protected EventHandler<ActionEvent> onLoginButtonClick(Text actionTarget, TextField userTextField, PasswordField pwBox, Stage stage) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String username = userTextField.getText();
                String password = pwBox.getText();
                // check if user_i and password_i match
                for (int i = 0; i < UserList.size(); i++) {
                    if (username.equals(UserList.get(i)) && password.equals(PasswordList.get(i))) {
                        actionTarget.setFill(javafx.scene.paint.Color.GREEN);
                        actionTarget.setText("Login successful");
                        // change scene
                        App todolist = new App(stage);
                        return;
                    }
                }
                actionTarget.setFill(javafx.scene.paint.Color.FIREBRICK);
                actionTarget.setText("Login failed");
            }
        };
    }
        
    //         @Override
    //         public void handle(ActionEvent event) {

    //             String username = userTextField.getText();
    //             String password = pwBox.getText();
    //             login login = new login();

    //             try{
    //                 if(login.loginTo(username, password)){
    //                     actionTarget.setFill(javafx.scene.paint.Color.GREEN);
    //                         actionTarget.setText("Login successful");
    
    //                         // change scene
    //                         todolist todolist = new todolist(stage);
    //                 } else {
    //                     actionTarget.setFill(javafx.scene.paint.Color.FIREBRICK);
    //                     actionTarget.setText("Login failed");
    //                 }
    //             } catch (IllegalArgumentException e){
    //                 actionTarget.setFill(javafx.scene.paint.Color.FIREBRICK);
    //                 actionTarget.setText("Login failed");
    //             }
    //         }
    //     };
    // }

    protected EventHandler<ActionEvent> onRegisterButtonClick(Stage stage){
        return new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                RegisterView reg = new RegisterView(stage);
            }
        };
    }

    protected ChangeListener<? super String> onUserNameTextChange(Text actionTarget) {
        return (observable, oldValue, newValue) -> {
            actionTarget.setText("");
        };
    }

    protected ChangeListener<? super String> onPasswordTextChange(Text actionTarget) {
        return (observable, oldValue, newValue) -> {
            actionTarget.setText("");
        };
    }
}