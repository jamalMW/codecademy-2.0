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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class cursistController {

    @FXML
    private TextField adres;
    @FXML
    private TableColumn<cursist, String> adresColumn;
    @FXML
    private DatePicker datum;
    @FXML
    private TextField emailadres;
    @FXML
    private TableColumn<cursist, String> emailadresColumn;
    @FXML
    private TableColumn<cursist, LocalDate> geboorteDatumColumn;
    @FXML
    private ChoiceBox<String> geslacht;
    @FXML
    private TableColumn<cursist, String> geslachtColumn;
    @FXML
    private TextField huisnummer;
    @FXML
    private TableColumn<cursist, Integer> huisnummerColumn;
    @FXML
    private TextField land;
    @FXML
    private TableColumn<cursist, String> landColumn;
    @FXML
    private TextField naam;
    @FXML
    private TableColumn<cursist, String> naamColumn;
    @FXML
    private TextField postcode;
    @FXML
    private TableColumn<cursist, String> postcodeColumn;
    @FXML
    private TableView<cursist> table;
    @FXML
    private TextField woonplaats;
    @FXML
    private TableColumn<cursist, String> woonplaatsColumn;

    private static final String JDBC_URL = "jdbc:sqlserver://aei-sql2.avans.nl:1443;databaseName=CodeCademyGroepB3;encrypt=false;trustServerCertificate=true;";
    private static final String USERNAME = "LiWaAlBa";
    private static final String PASSWORD = "Sout(wacht);";

    private static final Alert.AlertType ERROR_ALERT = Alert.AlertType.ERROR;
    private static final Alert.AlertType INFO_ALERT = Alert.AlertType.INFORMATION;

    private static final String emailValidatie = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern pattern = Pattern.compile(emailValidatie);
    
    @SuppressWarnings("exports")
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    @FXML
    public void initialize() {
        geslacht.getItems().addAll("Man", "Vrouw");
        initTable();
        refreshTable();
    }

    private void initTable() {
        naamColumn.setCellValueFactory(new PropertyValueFactory<>("naam"));
        geboorteDatumColumn.setCellValueFactory(new PropertyValueFactory<>("geboorteDatum"));
        geslachtColumn.setCellValueFactory(new PropertyValueFactory<>("geslacht"));
        adresColumn.setCellValueFactory(new PropertyValueFactory<>("adres"));
        woonplaatsColumn.setCellValueFactory(new PropertyValueFactory<>("woonplaats"));
        landColumn.setCellValueFactory(new PropertyValueFactory<>("land"));
        emailadresColumn.setCellValueFactory(new PropertyValueFactory<>("emailadres"));
        postcodeColumn.setCellValueFactory(new PropertyValueFactory<>("postcode"));
        huisnummerColumn.setCellValueFactory(new PropertyValueFactory<>("huisnummer"));
    }

    private void refreshTable() {
        table.getItems().clear();
        table.getItems().addAll(getCursistList());
    }

    private List<cursist> getCursistList() {
        List<cursist> cursists = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM cursist");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                cursist c = new cursist(
                    resultSet.getString("naam"),
                    resultSet.getDate("geboortedatum").toLocalDate(),
                    resultSet.getString("geslacht"),
                    resultSet.getString("adres"),
                    resultSet.getString("woonplaats"),
                    resultSet.getString("land"),
                    resultSet.getString("emailadres"),
                    resultSet.getString("postcode"),
                    resultSet.getInt("huisnummer")
                );
                cursists.add(c);
            }
        } catch (SQLException e) {
            showAlert(ERROR_ALERT, "Database Error", e.getMessage());
        }
        return cursists;
    }

    @FXML
    void addButton(ActionEvent event) {
        if (!validateFields()) {
            return;
        }
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO cursist (naam, geboortedatum, geslacht, adres, woonplaats, land, emailadres, postcode, huisnummer ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            statement.setString(1, naam.getText());
            statement.setDate(2, java.sql.Date.valueOf(datum.getValue()));
            statement.setString(3, geslacht.getValue());
            statement.setString(4, adres.getText());
            statement.setString(5, woonplaats.getText());
            statement.setString(6, land.getText());
            statement.setString(7, emailadres.getText());
            statement.setString(8, postcode.getText());
            statement.setInt(9, Integer.parseInt(huisnummer.getText()));

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                showAlert(INFO_ALERT, "Success", "Cursist toegevoegd!");
                refreshTable();
            }
        } catch (SQLException e) {
            showAlert(ERROR_ALERT, "Database Error", e.getMessage());
        }
    }


    @FXML
    void updateButton(ActionEvent event) {
        cursist selectedCursist = table.getSelectionModel().getSelectedItem();
        if (selectedCursist == null) {
            showAlert(ERROR_ALERT, "Selecteer een cursist", "Selecteer een cursist om te bewerken.");
            return;
        }
        if (!validateFields()) {
            return;
        }
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE cursist SET naam=?, geboortedatum=?, geslacht=?, adres=?, woonplaats=?, land=?, emailadres=?, postcode=?, huisnummer=? WHERE emailadres=?")) {
            statement.setString(1, naam.getText());
            statement.setDate(2, java.sql.Date.valueOf(datum.getValue()));
            statement.setString(3, geslacht.getValue());
            statement.setString(4, adres.getText());
            statement.setString(5, woonplaats.getText());
            statement.setString(6, land.getText());
            statement.setString(7, emailadres.getText());
            statement.setString(8, postcode.getText());
            statement.setInt(9, Integer.parseInt(huisnummer.getText())); // Convert huisnummer to int
            statement.setString(10, selectedCursist.getEmailadres());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                showAlert(INFO_ALERT, "Success", "Cursist bijgewerkt!");
                refreshTable();
            }
        } catch (SQLException e) {
            showAlert(ERROR_ALERT, "Database Error", e.getMessage());
        }
    }

    @FXML
    void deleteButton(ActionEvent event) {
        cursist selectedCursist = table.getSelectionModel().getSelectedItem();
        if (selectedCursist == null) {
            showAlert(ERROR_ALERT, "Selecteer een cursist", "Selecteer een cursist om te verwijderen.");
            return;
        }
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM cursist WHERE emailadres=?")) {
            statement.setString(1, selectedCursist.getEmailadres());

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                showAlert(INFO_ALERT, "Success", "Cursist verwijderd!");
                refreshTable();
            }
        } catch (SQLException e) {
            showAlert(ERROR_ALERT, "Database Error", e.getMessage());
        }
    }

    private boolean validateFields() {
        if (naam.getText().isEmpty() || datum.getValue() == null || geslacht.getValue() == null
                || adres.getText().isEmpty() || woonplaats.getText().isEmpty()
                || land.getText().isEmpty() || emailadres.getText().isEmpty() || postcode.getText().isEmpty() || huisnummer.getText().isEmpty()) {
            showAlert(ERROR_ALERT, "Validation Error", "Alle velden moeten worden ingevuld!");
            return false;
        }

        // Validate email format
        String email = emailadres.getText();
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            showAlert(ERROR_ALERT, "Validation Error", "Ongeldig e-mailadres!");
            return false;
        }

        return true;
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void selectButton(ActionEvent event) {
        cursist selectedCursist = table.getSelectionModel().getSelectedItem();
        if (selectedCursist == null) {
            showAlert(ERROR_ALERT, "Selecteer een cursist", "Selecteer een cursist om te bewerken.");
            return;
        }
        
        // Populate input fields with selected cursist's data
        naam.setText(selectedCursist.getNaam());
        datum.setValue(selectedCursist.getGeboorteDatum());
        geslacht.setValue(selectedCursist.getGeslacht());
        adres.setText(selectedCursist.getAdres());
        woonplaats.setText(selectedCursist.getWoonplaats());
        land.setText(selectedCursist.getLand());
        emailadres.setText(selectedCursist.getEmailadres());
        postcode.setText(selectedCursist.getPostcode());
        huisnummer.setText(String.valueOf(selectedCursist.getHuisnummer()));
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
