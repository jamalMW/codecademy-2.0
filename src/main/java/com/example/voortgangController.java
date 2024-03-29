package com.example;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class voortgangController {
    


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
