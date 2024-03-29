package com.example;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class mainMenuController {
//Dit is de Main Menu, hieronder staan alle knoppen om de bijbehorende FXML-paginas te openen.

    @FXML //Opent de nieuwe stage Content en sluit de mainmenu stage
    private void goToContentKnop(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("content.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage secondStage = new Stage();
        secondStage.setTitle("Jamal Mitwally-2221071 Colin Valster-2174591");
        secondStage.setScene(scene);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        secondStage.show();
    } 
    catch (IOException e) {
        e.printStackTrace();
    }
    }

    @FXML //Opent de nieuwe stage Cursist en sluit de mainmenu stage
    private void goToCursistKnop(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cursist.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage secondStage = new Stage();
        secondStage.setTitle("Jamal Mitwally-2221071 Colin Valster-2174591");
        secondStage.setScene(scene);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        secondStage.show();
    } 
    catch (IOException e) {
        e.printStackTrace();
    }
    }

    @FXML //Opent de nieuwe stage Cursussen en sluit de mainmenu stage
    private void goToCursussenKnop(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cursussen.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage secondStage = new Stage();
        secondStage.setTitle("Jamal Mitwally-2221071 Colin Valster-2174591");
        secondStage.setScene(scene);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        secondStage.show();
    } 
    catch (IOException e) {
        e.printStackTrace();
    }
    }

    @FXML //Opent de nieuwe stage Inschrijvingen en sluit de mainmenu stage
    private void goToInschrijvingenKnop(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("inschrijvingen.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage secondStage = new Stage();
        secondStage.setTitle("Jamal Mitwally-2221071 Colin Valster-2174591");
        secondStage.setScene(scene);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        secondStage.show();
    } 
    catch (IOException e) {
        e.printStackTrace();
    }
    }

    @FXML //Opent de nieuwe stage Webcasts en sluit de mainmenu stage
    private void goToTopWebcastsKnop(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("topwebcasts.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage secondStage = new Stage();
        secondStage.setTitle("Jamal Mitwally-2221071 Colin Valster-2174591");
        secondStage.setScene(scene);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        secondStage.show();
    } 
    catch (IOException e) {
        e.printStackTrace();
    }
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
        secondStage.show();
    } 
    catch (IOException e) {
        e.printStackTrace();
    }
    }

}