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
    private TableView<cursus> cursussenTableView;

    @FXML
    private TableColumn<cursus, String> cursusColumn;

    @FXML
    private TableColumn<cursus, String> onderwerpColumn;

    @FXML
    private TableColumn<cursus, String> introColumn;

    @FXML
    private TableColumn<cursus, String> niveauColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cursusColumn.setCellValueFactory(new PropertyValueFactory<>("cursus"));
        onderwerpColumn.setCellValueFactory(new PropertyValueFactory<>("onderwerp"));
        introColumn.setCellValueFactory(new PropertyValueFactory<>("intro"));
        niveauColumn.setCellValueFactory(new PropertyValueFactory<>("niveau"));
        fetchDataFromDatabase();
    }

    private void fetchDataFromDatabase() {
        //hier staan de waardes voor de connectie met de db
        final String JDBC_URL = "jdbc:sqlserver://aei-sql2.avans.nl:1443;databaseName=CodeCademyGroepB3;encrypt=false;trustServerCertificate=true;";
        final String username = "LiWaAlBa";
        final String password = "Sout(wacht);";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, username, password)) {
            String sql = "SELECT * FROM dbo.Cursus";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                cursussenTableView.getItems().clear();

                while (resultSet.next()) {
                    cursus cursus = new cursus(
                            resultSet.getString("Naamcursus"),
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
        secondStage.show();
    } 
    catch (IOException e) {
        e.printStackTrace();
    }
    }
}
