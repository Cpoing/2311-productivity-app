package com.example;

import com.example.Components.User;
import com.example.Components.login;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * This class allows user to create their username and the password
 */
public class RegisterController {

    static LoginController loginController = new LoginController();
    private static login login;

    public RegisterController(login login){
        this.login = login;
    }

    protected EventHandler<ActionEvent> onRegisterButtonClick(Text actionTarget, TextField userTextField, TextField pwBox, Stage stage){
        return new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String username = userTextField.getText();
                String password = pwBox.getText();

                try{
                    User user = new User(username,password);
                    login.register(user);
                    actionTarget.setFill(javafx.scene.paint.Color.GREEN);
                    actionTarget.setText("Register successful");

                    LoginView loginpage = new LoginView(loginController);
                    loginpage.start(stage);
                    
                } catch (IllegalArgumentException e){
                    actionTarget.setFill(javafx.scene.paint.Color.RED);
                    actionTarget.setText("Register unsuccessful");
                }
            }
        };
    }

    public static com.example.Components.login getLogin() {
        return login;
    }

   
}