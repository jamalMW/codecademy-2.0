package com.example;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent; 
import javafx.scene.text.Text;

public class topwebcastsController {

    @FXML
    private Text topWebcast1;

    @FXML
    private Text topWebcast2;

    @FXML
    private Text topWebcast3;

    //hier staan de waardes voor de connectie met de db
    private static final String JDBC_URL = "jdbc:sqlserver://aei-sql2.avans.nl:1443;databaseName=CodeCademyGroepB3;encrypt=false;trustServerCertificate=true;";
    private static final String username = "LiWaAlBa";
    private static final String password = "Sout(wacht);";

    private Text[] topWebcastTexts;
    private int count; 

    public void initialize() {
        topWebcastTexts = new Text[]{topWebcast1, topWebcast2, topWebcast3};
        loadTopWebcastsData();
    }


    //Deze methode vraagt de data op aan de database
    private void loadTopWebcastsData() {
        count = 0; 
        try (Connection connection = DriverManager.getConnection(JDBC_URL, username, password)) {
            //SQL Query om de waardes uit de kolommen titel, naamspreker en views te pakken uit de bovenste 3 records, georderd op views.
            String sql = "SELECT TOP 3 titel, naamSpreker, views FROM Webcast ORDER BY Views DESC";

            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                    
                //Zet de waardes uit de kolommen in strings om
                while (resultSet.next() && count < topWebcastTexts.length) {
                    String titel = resultSet.getString("titel");
                    String speaker = resultSet.getString("naamSpreker");
                    String duration = resultSet.getString("views");
                    setTextForTopWebcast(topWebcastTexts[count], titel, speaker, duration);
                    count++;
                }
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //Plaatst de tekst op het scherm met de verkregen data
    private void setTextForTopWebcast(Text webcastText, String titel, String speaker, String duration) {
        webcastText.setText(String.format("%d.    %s by %s (%s minutes)", 
        count + 1, titel, speaker, duration));
    }
    

    @FXML 
    // Laad de scene mainMenu.
    private void goToMainMenuKnop(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
            Parent root = loader.load();
            ((Node) event.getSource()).getScene().setRoot(root);}
            catch (IOException e) {
            e.printStackTrace();
        }
    }
}
