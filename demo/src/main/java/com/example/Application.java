package com.example;

import javafx.stage.Stage;
import java.io.IOException;

import com.example.DB.DB;

public class Application extends javafx.application.Application {
   static LoginController loginController = new LoginController();

   @Override
   public void start(Stage stage) throws IOException {
      LoginView login = new LoginView(loginController);
      login.start(stage);
   }

   public static void main(String[] args) {
      DB db = new DB();
      if(db.init()){
         System.out.println("Connected");
      } else {
         System.out.println("Connection failed");
      }
      launch();
   }
}