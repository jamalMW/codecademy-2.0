package com.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button buttonLogIn;

    @FXML
    private Button buttonCreateAccount;
    
    @FXML
    private Text wrongLogin;

    //Probeert in te loggen wanneer
    @FXML
    private void userlogin() {
        String enteredUsername = username.getText();
        String enteredPassword = password.getText();
        String jdbcUrl = "jdbc:sqlserver://LAPTOP-I0I2L5OV:1433;databaseName=UserAccountsDB;user=sa;password=031803;encrypt=false";
        String selectQuery = "SELECT * FROM UsersTable WHERE username = ? AND password = ?";

        if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
            wrongLogin.setText("Username and password cannot be empty");
            return;
            }

        try (Connection connection = DriverManager.getConnection(jdbcUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

            preparedStatement.setString(1, enteredUsername);
            preparedStatement.setString(2, enteredPassword);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    loadMainMenu(enteredUsername);
                } else {
                    System.out.println("Invalid username or password");
                    wrongLogin.setText("Invalid username or password");

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void createAccount() {
        String enteredUsername = username.getText();
        String enteredPassword = password.getText();
        String jdbcUrl = "jdbc:sqlserver://192.168.2.79:1433;databaseName=Codecademy;user=sa;password=031803;encrypt=false";
        String insertQuery = "INSERT INTO UsersTable (username, password) VALUES (?, ?)";
        
        if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
            wrongLogin.setText("Username and password cannot be empty");
            return;
            }

        try (Connection connection = DriverManager.getConnection(jdbcUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setString(1, enteredUsername);
            preparedStatement.setString(2, enteredPassword);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                loadMainMenu(enteredUsername);
            } else {
                
            }

        } catch (SQLException e) {
            e.printStackTrace();
            wrongLogin.setText("Account already exists!");
        }
    }


     private void loadMainMenu(String username) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
            Parent MainMenuRoot = loader.load();
            Scene MainMenu = new Scene(MainMenuRoot);

            Stage currentStage = (Stage) buttonCreateAccount.getScene().getWindow();
            currentStage.setScene(MainMenu);
            currentStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
