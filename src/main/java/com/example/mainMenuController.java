package com.example;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Node;

public class mainMenuController {
//Dit is de Main Menu, hieronder staan alle knoppen om de bijbehorende FXML-paginas te openen.


    @FXML 
    private void goToCursussenKnop(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/cursussen"));
            Parent root = loader.load();
            ((Node) event.getSource()).getScene().setRoot(root);
        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}