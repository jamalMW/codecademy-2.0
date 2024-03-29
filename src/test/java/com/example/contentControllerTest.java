package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ContentControllerTest {

    private ContentController contentController;

    @BeforeEach
    void setUp() {
        contentController = new ContentController();
    }

    @Test
    void testPopulateWebcastTable() throws SQLException {
        // Mock the Connection, PreparedStatement, and ResultSet
        Connection connection = Mockito.mock(Connection.class);
        PreparedStatement statement = Mockito.mock(PreparedStatement.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);

        // Define sample SQL query and expected column names
        String sql = "SELECT * FROM Webcast";
        String[] expectedColumnNames = {"Column1", "Column2"}; // Change as per your table structure

        // Mock behavior for the connection, statement, and result set
        Mockito.when(connection.prepareStatement(sql)).thenReturn(statement);
        Mockito.when(statement.executeQuery()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getMetaData().getColumnCount()).thenReturn(expectedColumnNames.length);
        Mockito.when(resultSet.getMetaData().getColumnName(Mockito.anyInt())).thenAnswer(invocation -> {
            int index = invocation.getArgument(0);
            return expectedColumnNames[index - 1];
        });
        Mockito.when(resultSet.getString(Mockito.anyInt())).thenReturn("SampleValue");

        // Call the method to be tested
        contentController.populateWebcastTable(connection);

        // Assert the behavior
        // You may need to further customize these assertions based on your specific requirements
        assertEquals(expectedColumnNames.length, contentController.webcastTableView.getColumns().size());
        assertEquals(1, contentController.webcastTableView.getItems().size());
    }

    // Similar test method for populateModuleTable() can be written
}
