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
import javafx.scene.text.Text;

public class topwebcastsController {

    @FXML
    private Text topWebcast1;

    @FXML
    private Text topWebcast2;

    @FXML
    private Text topWebcast3;

    private static final String JDBC_URL = "jdbc:sqlserver://LAPTOP-I0I2L5OV:1433;databaseName=Codecademy;user=sa;password=031803;encrypt=false";

    private Text[] topWebcastTexts;
    private int count; 

    public void initialize() {
        topWebcastTexts = new Text[]{topWebcast1, topWebcast2, topWebcast3};

        
        loadTopWebcastsData();
    }

    private void loadTopWebcastsData() {
        count = 0; 
        try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
            
            String sql = "SELECT TOP 3 titel, naamSpreker, views FROM Webcast ORDER BY Views DESC";

            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next() && count < topWebcastTexts.length) {
                    String titel = resultSet.getString("titel");
                    String speaker = resultSet.getString("naamSpreker");
                    String duration = resultSet.getString("views");

                   
                    setTextForTopWebcast(topWebcastTexts[count], titel, speaker, duration);

                    count++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setTextForTopWebcast(Text webcastText, String titel, String speaker, String duration) {
        webcastText.setText(String.format("%d.    %s by %s (%s minutes)", 
                                          count + 1, titel, speaker, duration));
    }
    

    @FXML // Scene loader MainMenu
    private void goToMainMenuKnop(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
            Parent root = loader.load();
            ((Node) event.getSource()).getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
