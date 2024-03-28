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
    private void goToContentKnop(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("content.fxml"));
            Parent root = loader.load();
            ((Node) event.getSource()).getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToCursistKnop(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cursist.fxml"));
            Parent root = loader.load();
            ((Node) event.getSource()).getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML

    private void goToCursussenKnop(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cursussen.fxml"));
            Parent root = loader.load();
            ((Node) event.getSource()).getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToInschrijvingenKnop(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("inschrijvingen.fxml"));
            Parent root = loader.load();
            ((Node) event.getSource()).getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToTopWebcastsKnop(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("topwebcasts.fxml"));
            Parent root = loader.load();
            ((Node) event.getSource()).getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToVoortgangKnop(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("voortgang.fxml"));
            Parent root = loader.load();
            ((Node) event.getSource()).getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}