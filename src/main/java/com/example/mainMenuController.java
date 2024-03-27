package com.example;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenuController {

    @FXML //laat een welkomsttekst zien voor nieuwe gebruikers
    private Text welcomeText;

    public void setWelcomeMessage(int newUser, String username) {
        if (newUser == 1) {
            welcomeText.setText("Welcome New User " + username);
        } else {
            welcomeText.setText("Welcome, " + username);
        }
    }

    @FXML //sceneloader cursussen
    private void goToCursussenKnop(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("onderdelen/cursussen.fxml"));
            Parent root = loader.load();

            Scene scene= new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            
            Stage currenStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currenStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML //sceneloader voortgang
    private void goToVoortgangKnop(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("onderdelen/voortgang.fxml"));
            Parent root = loader.load();

            Scene scene= new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            
            Stage currenStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currenStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML //sceneloader geslacht
    private void goToInschrijvingenKnop(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("onderdelen/inschrijvingen.fxml"));
            Parent root = loader.load();

            Scene scene= new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            
            Stage currenStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currenStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML //sceneloader cursisten
    private void goToCursistenKnop(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("onderdelen/cursist.fxml"));
            Parent root = loader.load();

            Scene scene= new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            
            Stage currenStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currenStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML //sceneloader webcast
    private void goToContentKnop(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("onderdelen/content.fxml"));
            Parent root = loader.load();

            Scene scene= new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            
            Stage currenStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currenStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML //sceneloader top webcasts
    private void goToTopWebcastsKnop(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("onderdelen/topwebcasts.fxml"));
            Parent root = loader.load();

            Scene scene= new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            
            Stage currenStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currenStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

