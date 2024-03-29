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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class cursistController {

    @FXML
    private TableView<cursist> table;
    @FXML
    private TableColumn<cursist, String> naamColumn;
    @FXML
    private TableColumn<cursist, String> geboortedatumColumn;
    @FXML
    private TableColumn<cursist, String> geslachtColumn;
    @FXML
    private TableColumn<cursist, String> adresColumn;
    @FXML
    private TableColumn<cursist, String> woonplaatsColumn;
    @FXML
    private TableColumn<cursist, String> landColumn;
    @FXML
    private TextField emailadres;
    @FXML
    private TextField naam;
    @FXML
    private TextField geboortedag;
    @FXML
    private TextField geboortemaand;
    @FXML
    private TextField geboortejaar;
    @FXML
    private ChoiceBox<String> geslacht;
    @FXML
    private TextField adres;
    @FXML
    private TextField huisnummer;
    @FXML
    private TextField postcode;
    @FXML
    private TextField woonplaats;
    @FXML
    private TextField land;

    // JDBC URL and credentials
    private static final String JDBC_URL = "jdbc:sqlserver://aei-sql2.avans.nl:1443;databaseName=CodeCademyGroepB3;encrypt=false;trustServerCertificate=true;";
    private static final String USERNAME = "LiWaAlBa";
    private static final String PASSWORD = "Sout(wacht);";

    public void initialize() {
        // Populate the TableView
        populateCursistTable();
        // Set gender choice options
        geslacht.getItems().addAll("Male", "Female");
    }

    // Method to populate the Cursist TableView
    private void populateCursistTable() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String sql = "SELECT emailadres, naam, CONCAT(geboortedag, '-', geboortemaand, '-', geboortejaar) AS geboortedatum, geslacht, adres, woonplaats, land FROM Cursist";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                cursist cursist = new cursist(
                        resultSet.getString("emailadres"),
                        resultSet.getString("naam"),
                        resultSet.getString("geboortedatum"),
                        resultSet.getString("geslacht"),
                        resultSet.getString("adres"),
                        resultSet.getString("woonplaats"),
                        resultSet.getString("land")
                );
                table.getItems().add(cursist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
private void updateButton(ActionEvent event) {
    // Get the selected cursist from the table
    cursist selectedCursist = table.getSelectionModel().getSelectedItem();

    if (selectedCursist == null) {
        showAlert("No Cursist Selected", "Please select a cursist from the table.");
        return;
    }

    if (validateInput()) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String sql = "UPDATE Cursist SET emailadres=?, naam=?, geboortedag=?, geboortemaand=?, geboortejaar=?, geslacht=?, adres=?, huisnummer=?, postcode=?, woonplaats=?, land=? WHERE emailadres=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, emailadres.getText());
            statement.setString(2, naam.getText());
            statement.setString(3, geboortedag.getText());
            statement.setString(4, geboortemaand.getText());
            statement.setString(5, geboortejaar.getText());
            statement.setString(6, geslacht.getValue());
            statement.setString(7, adres.getText());
            statement.setString(8, huisnummer.getText());
            statement.setString(9, postcode.getText());
            statement.setString(10, woonplaats.getText());
            statement.setString(11, land.getText());
            statement.setString(12, selectedCursist.getEmail()); // Use the emailadres of the selected cursist to identify the record to update
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                showAlert("Update Successful", "Cursist information updated successfully.");
                clearFields();
                populateCursistTable(); // Refresh the table to reflect the changes
            } else {
                showAlert("Update Failed", "Failed to update cursist information.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Database Error", "An error occurred while updating cursist information.");
        }
    }
}


    // Method to add a new cursist
    @FXML
    private void addButton(ActionEvent event) {
        if (validateInput()) {
            insertData();
            clearFields();
            populateCursistTable();
        }
    }

    // Method to validate input data
    private boolean validateInput() {
        if (emailadres.getText().isEmpty() || naam.getText().isEmpty() || geboortedag.getText().isEmpty()
                || geboortemaand.getText().isEmpty() || geboortejaar.getText().isEmpty()
                || geslacht.getValue() == null || adres.getText().isEmpty() || huisnummer.getText().isEmpty()
                || postcode.getText().isEmpty() || woonplaats.getText().isEmpty() || land.getText().isEmpty()) {
            showAlert("Invalid Input", "All fields are required.");
            return false;
        }
        // Additional validation logic for email format, date format, etc. can be added here
        return true;
    }

    // Method to insert data into the database
    private void insertData() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO Cursist (emailadres, naam, geboortedag, geboortemaand, geboortejaar, geslacht, adres, huisnummer, postcode, woonplaats, land) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, emailadres.getText());
            statement.setString(2, naam.getText());
            statement.setString(3, geboortedag.getText());
            statement.setString(4, geboortemaand.getText());
            statement.setString(5, geboortejaar.getText());
            statement.setString(6, geslacht.getValue());
            statement.setString(7, adres.getText());
            statement.setString(8, huisnummer.getText());
            statement.setString(9, postcode.getText());
            statement.setString(10, woonplaats.getText());
            statement.setString(11, land.getText());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to clear input fields
    private void clearFields() {
        emailadres.clear();
        naam.clear();
        geboortedag.clear();
        geboortemaand.clear();
        geboortejaar.clear();
        geslacht.setValue(null);
        adres.clear();
        huisnummer.clear();
        postcode.clear();
        woonplaats.clear();
        land.clear();
    }

    // Method to show an alert dialog
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML // Close the current stage and go back to the main menu
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
