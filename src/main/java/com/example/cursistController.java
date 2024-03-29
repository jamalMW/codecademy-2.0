package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class cursistController {

    @FXML
    private TextField naamTextField;
    @FXML
    private DatePicker geboorteDatumPicker;
    @FXML
    private ChoiceBox<String> geslachtChoiceBox;
    @FXML
    private TextField adresTextField;
    @FXML
    private TextField woonplaatsTextField;
    @FXML
    private TextField landTextField;
    @FXML
    private TextField emailadresTextField;
    @FXML
    private TableView<cursist> table;
    @FXML
    private TableColumn<cursist, String> naamColumn;
    @FXML
    private TableColumn<cursist, LocalDate> geboorteDatumColumn;
    @FXML
    private TableColumn<cursist, String> geslachtColumn;
    @FXML
    private TableColumn<cursist, String> adresColumn;
    @FXML
    private TableColumn<cursist, String> woonplaatsColumn;
    @FXML
    private TableColumn<cursist, String> landColumn;
    @FXML
    private TableColumn<cursist, String> emailadresColumn;
    @FXML
    private Text warning;

    private static final String JDBC_URL = "jdbc:sqlserver://LAPTOP-I0I2L5OV:1433;databaseName=Codecademy;user=sa;password=031803;encrypt=false";

    @FXML //sceneloader MainMenu
    private void goToMainMenuKnop(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/codecademy/MainMenu.fxml"));
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

    @FXML
    private void initialize() {
        geslachtChoiceBox.getItems().addAll("Man", "Vrouw", "Anders");
        geslachtChoiceBox.setValue("Man");
        naamColumn.setCellValueFactory(cellData -> cellData.getValue().naamProperty());
        geboorteDatumColumn.setCellValueFactory(cellData -> cellData.getValue().geboorteDatumProperty());
        geslachtColumn.setCellValueFactory(cellData -> cellData.getValue().geslachtProperty());
        adresColumn.setCellValueFactory(cellData -> cellData.getValue().adresProperty());
        woonplaatsColumn.setCellValueFactory(cellData -> cellData.getValue().woonplaatsProperty());
        landColumn.setCellValueFactory(cellData -> cellData.getValue().landProperty());
        emailadresColumn.setCellValueFactory(cellData -> cellData.getValue().emailadresProperty());

        loadData();
    }

    @FXML
    private void loadData() {
        table.getItems().clear(); 
        try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
            String query = "SELECT * FROM Codecademy.dbo.Cursisten"; 
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    cursist newCursist = new cursist(
                            resultSet.getString("naam"),
                            resultSet.getDate("geboortedatum").toLocalDate(),
                            resultSet.getString("geslacht"),
                            resultSet.getString("adres"),
                            resultSet.getString("woonplaats"),
                            resultSet.getString("land"),
                            resultSet.getString("emailadres")
                    );
                    table.getItems().add(newCursist);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addButton() {
        String naam = naamTextField.getText();
        LocalDate geboorteDatum = geboorteDatumPicker.getValue();
        String geslacht = geslachtChoiceBox.getValue();
        String adres = adresTextField.getText();
        String woonplaats = woonplaatsTextField.getText();
        String land = landTextField.getText();
        String emailadres = emailadresTextField.getText();

        String emailValidatie = "^[A-Za-z]+@[A-Za-z]+\\.[A-Za-z]+$";
        if (!emailadres.matches(emailValidatie)) {
            warning.setText("Please enter a valid e-mailadress");
            return;
        }
        if (naam == null || naam.trim().isEmpty() || geboorteDatum == null || geslacht == null || geslacht.trim().isEmpty() || adres == null || adres.trim().isEmpty() || woonplaats == null || woonplaats.trim().isEmpty() || land == null || land.trim().isEmpty() || emailadres == null || emailadres.trim().isEmpty()) {
            warning.setText("Please fill in all fields.");
            return;
        }

        try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
            String insertQuery = "INSERT INTO Codecademy.dbo.Cursisten (naam, geboortedatum, geslacht, adres, woonplaats, land, emailadres) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, naam);
                preparedStatement.setDate(2, Date.valueOf(geboorteDatum));
                preparedStatement.setString(3, geslacht);
                preparedStatement.setString(4, adres);
                preparedStatement.setString(5, woonplaats);
                preparedStatement.setString(6, land);
                preparedStatement.setString(7, emailadres);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        clearFields();
        loadData();
        warning.setText("");
    }

    @FXML
    public void updateButton() {
        cursist selectedCursist = table.getSelectionModel().getSelectedItem();
        if (selectedCursist != null) {
            String updatedNaam = naamTextField.getText();
            LocalDate updatedGeboorteDatum = geboorteDatumPicker.getValue();
            String updatedGeslacht = geslachtChoiceBox.getValue();
            String updatedAdres = adresTextField.getText();
            String updatedWoonplaats = woonplaatsTextField.getText();
            String updatedLand = landTextField.getText();
            String updatedEmailadres = emailadresTextField.getText();

        String emailValidatie = "^[A-Za-z]+@[A-Za-z]+\\.[A-Za-z]+$";
        if (!updatedEmailadres.matches(emailValidatie)) {
            warning.setText("Please enter a valid e-mailadress");
            return;
        }

        if (updatedNaam == null || updatedNaam.trim().isEmpty() || updatedGeboorteDatum == null || updatedGeslacht == null || updatedGeslacht.trim().isEmpty() || updatedAdres == null || updatedAdres.trim().isEmpty() || updatedWoonplaats == null || updatedWoonplaats.trim().isEmpty() || updatedLand == null || updatedLand.trim().isEmpty() || updatedEmailadres == null || updatedEmailadres.trim().isEmpty()) {
            warning.setText("Please fill in all fields.");
            return;
        }

            try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
                String updateQuery = "UPDATE Codecademy.dbo.Cursisten SET naam=?, geboortedatum=?, geslacht=?, adres=?, woonplaats=?, land=?, emailadres=? WHERE naam=?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                    preparedStatement.setString(1, updatedNaam);
                    preparedStatement.setDate(2, Date.valueOf(updatedGeboorteDatum));
                    preparedStatement.setString(3, updatedGeslacht);
                    preparedStatement.setString(4, updatedAdres);
                    preparedStatement.setString(5, updatedWoonplaats);
                    preparedStatement.setString(6, updatedLand);
                    preparedStatement.setString(7, updatedEmailadres);
                    preparedStatement.setString(8, selectedCursist.getNaam());
                    preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            warning.setText("Please fill in all fields");
        }

        clearFields();
        loadData();
        warning.setText("");
    }
}


    @FXML
    public void deleteButton() {
        cursist selectedCursist = table.getSelectionModel().getSelectedItem();
        if (selectedCursist != null) {
            try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
                String deleteQuery = "DELETE FROM Codecademy.dbo.Cursisten WHERE naam=?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                    preparedStatement.setString(1, selectedCursist.getNaam());
                    preparedStatement.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            loadData();
        }
    }

    private void clearFields() {
        naamTextField.clear();
        geboorteDatumPicker.setValue(null);
        geslachtChoiceBox.setValue("Man");
        adresTextField.clear();
        woonplaatsTextField.clear();
        landTextField.clear();
        emailadresTextField.clear();
    }
}
