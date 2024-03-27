package com.example;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class cursussenController implements Initializable {

    @FXML
    private ImageView logoImageView;

    @FXML
    private Text titleLabel;

    @FXML
    private Button homeButton;

    @FXML
    private TableView<Cursus> cursussenTableView;

    @FXML
    private TableColumn<Cursus, String> cursusColumn;

    @FXML
    private TableColumn<Cursus, String> onderwerpColumn;

    @FXML
    private TableColumn<Cursus, String> introColumn;

    @FXML
    private TableColumn<Cursus, String> niveauColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cursusColumn.setCellValueFactory(new PropertyValueFactory<>("cursus"));
        onderwerpColumn.setCellValueFactory(new PropertyValueFactory<>("onderwerp"));
        introColumn.setCellValueFactory(new PropertyValueFactory<>("intro"));
        niveauColumn.setCellValueFactory(new PropertyValueFactory<>("niveau"));


        fetchDataFromDatabase();
    }

    private void fetchDataFromDatabase() {
        final String JDBC_URL = "jdbc:sqlserver://LAPTOP-I0I2L5OV:1433;databaseName=Codecademy;user=sa;password=031803;encrypt=false";

        try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
      
            String sql = "SELECT * FROM dbo.Cursus";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

             
                cursussenTableView.getItems().clear();

               
                while (resultSet.next()) {
                    Cursus cursus = new Cursus(
                            resultSet.getString("NaamCursus"),
                            resultSet.getString("Onderwerp"),
                            resultSet.getString("IntroTekst"),
                            resultSet.getString("Niveau")
                    );
                    cursussenTableView.getItems().add(cursus);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML // Scene loader MainMenu
    private void goToMainMenuKnop(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/codecademy/MainMenu.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
