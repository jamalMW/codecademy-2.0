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
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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

    //setconnection en gettopwebcaststexts is voor de unittests
    public void setConnection(@SuppressWarnings("exports") Connection connection) {
    }

    @SuppressWarnings("exports")
    public Text[] getTopWebcastTexts() {
        return topWebcastTexts;
    }
    //Deze methode vraagt de data op aan de database
    void loadTopWebcastsData() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, username, password)) {
            //SQL Query om de waardes uit de kolommen titel, naamspreker en views te pakken uit de bovenste 3 records, georderd op views.
            String sql = "SELECT TOP 3 titel, naamSpreker, views FROM Webcast ORDER BY Views DESC";

            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                    
                //Zet de waardes uit de kolommen in strings om
                while (resultSet.next() && count < topWebcastTexts.length) {
                    String titel = resultSet.getString("titel");
                    String speaker = resultSet.getString("naamSpreker");
                    String views = resultSet.getString("views");
                    setTextForTopWebcast(topWebcastTexts[count], titel, speaker, views);
                    count++;
                }
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //Plaatst de tekst op het scherm met de verkregen data en zet de nummering
    private void setTextForTopWebcast(Text webcastText, String titel, String speaker, String views) {
        webcastText.setText(String.format("%d.    %s by %s (%s views)", 
        count + 1, titel, speaker, views));
    }
    

    @FXML //Sluit de huidige stage en gaat terug naar de main menu
    private void goToMainMenuKnop(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage secondStage = new Stage();
        secondStage.setTitle("Jamal Mitwally-2221071 Colin Valster-2174591");
        secondStage.setScene(scene);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        secondStage.setResizable(false);
        secondStage.show();
    } 
    catch (IOException e) {
        e.printStackTrace();
    }
    }
}
