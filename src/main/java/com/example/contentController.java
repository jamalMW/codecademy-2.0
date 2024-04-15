package com.example;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.*;

public class contentController {

    TableView<ObservableList<String>> webcastTableView;
    private TableView<ObservableList<String>> moduleTableView;

    // JDBC URL and credentials
    private static final String JDBC_URL = "jdbc:sqlserver://aei-sql2.avans.nl:1443;databaseName=CodeCademyGroepB3;encrypt=false;trustServerCertificate=true;";
    private static final String USERNAME = "LiWaAlBa";
    private static final String PASSWORD = "Sout(wacht);";

    public contentController(TableView<ObservableList<String>> webcastTableView, TableView<ObservableList<String>> moduleTableView) {
        this.webcastTableView = webcastTableView;
        this.moduleTableView = moduleTableView;
    }

    public void initialize() {
        populateWebcastTable();
        populateModuleTable();
    }

    public void populateWebcastTable() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String sql = "SELECT * FROM Webcast";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            webcastTableView.getColumns().clear();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int numColumns = metaData.getColumnCount();
            for (int i = 1; i <= numColumns; i++) {
                final int j = i - 1;
                TableColumn<ObservableList<String>, String> column = new TableColumn<>(metaData.getColumnName(i));
                column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(j)));
                webcastTableView.getColumns().add(column);
            }

            ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
            while (resultSet.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= numColumns; i++) {
                    row.add(resultSet.getString(i));
                }
                data.add(row);
            }
            webcastTableView.setItems(data);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void populateModuleTable() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String sql = "SELECT * FROM Module";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            moduleTableView.getColumns().clear();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int numColumns = metaData.getColumnCount();
            for (int i = 1; i <= numColumns; i++) {
                final int j = i - 1;
                TableColumn<ObservableList<String>, String> column = new TableColumn<>(metaData.getColumnName(i));
                column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(j)));
                moduleTableView.getColumns().add(column);
            }

            ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
            while (resultSet.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= numColumns; i++) {
                    row.add(resultSet.getString(i));
                }
                data.add(row);
            }
            moduleTableView.setItems(data);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Getter for webcastTableView (if needed)
    public TableView<ObservableList<String>> getWebcastTableView() {
        return webcastTableView;
    }

    // Getter for moduleTableView (if needed)
    public TableView<ObservableList<String>> getModuleTableView() {
        return moduleTableView;
    }

}
