package com.example;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class topwebcastsControllerTest {

    private topwebcastsController controller;

    public void setUp() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("topwebcasts.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            System.out.println("Controller: " + loader.getController());
    
            controller = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLoadTopWebcastsData() throws SQLException {
        assertNotNull(controller);

        Connection connection = Mockito.mock(Connection.class);
        PreparedStatement statement = Mockito.mock(PreparedStatement.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);


        when(resultSet.next()).thenReturn(true, true, true, false); 
        when(resultSet.getString("titel")).thenReturn("Title1", "Title2", "Title3");
        when(resultSet.getString("naamSpreker")).thenReturn("Speaker1", "Speaker2", "Speaker3");
        when(resultSet.getString("views")).thenReturn("100", "200", "300");

        when(connection.prepareStatement(Mockito.anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        controller.setConnection(connection);
        controller.initialize(); 
        controller.loadTopWebcastsData();

        assertEquals("1.    Title1 by Speaker1 (100 views)", controller.getTopWebcastTexts()[0].getText());
        assertEquals("2.    Title2 by Speaker2 (200 views)", controller.getTopWebcastTexts()[1].getText());
        assertEquals("3.    Title3 by Speaker3 (300 views)", controller.getTopWebcastTexts()[2].getText());
    }
}
