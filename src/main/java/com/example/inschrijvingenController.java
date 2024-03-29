package com.example;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class inschrijvingenController {

    @FXML
    private TextField emailadres;

    @FXML
    private ChoiceBox<String> cursus;

    @FXML
    private DatePicker datum;

    @FXML
    private TableColumn<inschrijving, String> emailadresColumn;

    @FXML
    private TableColumn<inschrijving, String> cursusColumn;

    @FXML
    private TableColumn<inschrijving, LocalDate> datumColumn;

    @FXML
    private TableView<inschrijving> table;

    private static final String JDBC_URL = "jdbc:sqlserver://aei-sql2.avans.nl:1443;databaseName=CodeCademyGroepB3;encrypt=false;trustServerCertificate=true;";
    private static final String USERNAME = "LiWaAlBa";
    private static final String PASSWORD = "Sout(wacht);";

    private static final Alert.AlertType ERROR_ALERT = Alert.AlertType.ERROR;
    private static final Alert.AlertType INFO_ALERT = Alert.AlertType.INFORMATION;

    @SuppressWarnings("exports")
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    @FXML
    public void initialize() {
        cursus.getItems().addAll("Duurzame Ontwikkeling", "Programmeren 2", "Relationele Databases 1"); // Add your courses here
        initTable();
        refreshTable();
    }

    private void initTable() {
        emailadresColumn.setCellValueFactory(new PropertyValueFactory<>("emailadres"));
        cursusColumn.setCellValueFactory(new PropertyValueFactory<>("naamCursus")); // Update this line
        datumColumn.setCellValueFactory(new PropertyValueFactory<>("datum"));
    }

    private void refreshTable() {
        table.getItems().clear();
        table.getItems().addAll(getInschrijvingList());
    }

    private List<inschrijving> getInschrijvingList() {
        List<inschrijving> inschrijvingen = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM inschrijving");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                inschrijving i = new inschrijving(
                    resultSet.getString("emailadres"),
                    resultSet.getString("naamCursus"),
                    resultSet.getDate("datum").toLocalDate()
                );
                inschrijvingen.add(i);
            }
        } catch (SQLException e) {
            showAlert(ERROR_ALERT, "Database Error", e.getMessage());
        }
        return inschrijvingen;
    }

    @FXML
    void addButton(ActionEvent event) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO inschrijving (emailadres, naamCursus, datum) VALUES (?, ?, ?)")) {
            statement.setString(1, emailadres.getText());
            statement.setString(2, cursus.getValue());
            statement.setDate(3, java.sql.Date.valueOf(datum.getValue()));

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                showAlert(INFO_ALERT, "Success", "Inschrijving toegevoegd!");
                refreshTable();
            }
        } catch (SQLException e) {
            showAlert(ERROR_ALERT, "Database Error", e.getMessage());
        }
    }

    @FXML
    void deleteButton(ActionEvent event) {
        inschrijving selectedInschrijving = table.getSelectionModel().getSelectedItem();
        if (selectedInschrijving == null) {
            showAlert(ERROR_ALERT, "Selecteer een inschrijving", "Selecteer een inschrijving om te verwijderen.");
            return;
        }
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM inschrijving WHERE emailadres=? AND naamCursus=? AND datum=?")) {
            statement.setString(1, selectedInschrijving.getEmailadres());
            statement.setString(2, selectedInschrijving.getNaamCursus());
            statement.setDate(3, java.sql.Date.valueOf(selectedInschrijving.getDatum()));

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                showAlert(INFO_ALERT, "Success", "Inschrijving verwijderd!");
                refreshTable();
            }
        } catch (SQLException e) {
            showAlert(ERROR_ALERT, "Database Error", e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    @FXML
    void updateButton(ActionEvent event) {
    inschrijving selectedInschrijving = table.getSelectionModel().getSelectedItem();
    if (selectedInschrijving == null) {
        showAlert(ERROR_ALERT, "Selecteer een inschrijving", "Selecteer een inschrijving om te bijwerken.");
        return;
    }
    
    // Get the new values from the input fields
    String newEmail = emailadres.getText();
    String newCursus = cursus.getValue();
    LocalDate newDatum = datum.getValue();
    
    // Update the selected item with the new values
    try (Connection connection = getConnection();
         PreparedStatement statement = connection.prepareStatement("UPDATE inschrijving SET emailadres=?, naamCursus=?, datum=? WHERE emailadres=? AND naamCursus=? AND datum=?")) {
        statement.setString(1, newEmail);
        statement.setString(2, newCursus);
        statement.setDate(3, java.sql.Date.valueOf(newDatum));
        statement.setString(4, selectedInschrijving.getEmailadres());
        statement.setString(5, selectedInschrijving.getNaamCursus());
        statement.setDate(6, java.sql.Date.valueOf(selectedInschrijving.getDatum()));

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            showAlert(INFO_ALERT, "Success", "Inschrijving bijgewerkt!");
            refreshTable();
        }
    } catch (SQLException e) {
        showAlert(ERROR_ALERT, "Database Error", e.getMessage());
    }
    }


    @FXML
    private void selectButton(ActionEvent event) {
    inschrijving selectedInschrijving = table.getSelectionModel().getSelectedItem();
    if (selectedInschrijving == null) {
        showAlert(ERROR_ALERT, "Selecteer een inschrijving", "Selecteer een inschrijving om te bewerken.");
        return;
    }
    emailadres.setText(selectedInschrijving.getEmailadres());
    cursus.setValue(selectedInschrijving.getNaamCursus());
    datum.setValue(selectedInschrijving.getDatum());
    }


    @FXML // Sluit de huidige stage en gaat terug naar het hoofdmenu
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
