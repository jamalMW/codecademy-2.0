package com.example;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class contentController {

    @FXML
    private TableView<ObservableList<String>> webcastTableView;

    @FXML
    private TableView<ObservableList<String>> moduleTableView;

    // JDBC URL and credentials
    private static final String JDBC_URL = "jdbc:sqlserver://aei-sql2.avans.nl:1443;databaseName=CodeCademyGroepB3;encrypt=false;trustServerCertificate=true;";
    private static final String USERNAME = "LiWaAlBa";
    private static final String PASSWORD = "Sout(wacht);";

    // Method to initialize the controller
    public void initialize() {
        // Populate the TableViews
        populateWebcastTable();
        populateModuleTable();
    }

    // Method to populate the Webcast TableView
    private void populateWebcastTable() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String sql = "SELECT * FROM Webcast";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
    
            // Clear existing columns to avoid duplication
            webcastTableView.getColumns().clear();
    
            int numColumns = resultSet.getMetaData().getColumnCount();
            for (int i = 1; i <= numColumns; i++) {
                final int j = i - 1;
                TableColumn<ObservableList<String>, String> column = new TableColumn<>(resultSet.getMetaData().getColumnName(i));
                column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(j)));
                webcastTableView.getColumns().add(column);
            }
    
            // Populate the table with data
            while (resultSet.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= numColumns; i++) {
                    row.add(resultSet.getString(i));
                }
                webcastTableView.getItems().add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    private void populateModuleTable() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String sql = "SELECT * FROM Module";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
    
            // Clear existing columns to avoid duplication
            moduleTableView.getColumns().clear();
    
            int numColumns = resultSet.getMetaData().getColumnCount();
            for (int i = 1; i <= numColumns; i++) {
                final int j = i - 1;
                TableColumn<ObservableList<String>, String> column = new TableColumn<>(resultSet.getMetaData().getColumnName(i));
                column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(j)));
                moduleTableView.getColumns().add(column);
            }
    
            // Populate the table with data
            while (resultSet.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= numColumns; i++) {
                    row.add(resultSet.getString(i));
                }
                moduleTableView.getItems().add(row);
            }
        } catch (SQLException e) {
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
        secondStage.setResizable(false);
        secondStage.show();
    } 
    catch (IOException e) {
        e.printStackTrace();
    }
    }
}
