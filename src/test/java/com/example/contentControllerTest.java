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
        Connection connection = Mockito.mock(Connection.class);
        PreparedStatement statement = Mockito.mock(PreparedStatement.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        String sql = "SELECT * FROM Webcast";
        String[] expectedColumnNames = {"Column1", "Column2"}; 

        Mockito.when(connection.prepareStatement(sql)).thenReturn(statement);
        Mockito.when(statement.executeQuery()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getMetaData().getColumnCount()).thenReturn(expectedColumnNames.length);
        Mockito.when(resultSet.getMetaData().getColumnName(Mockito.anyInt())).thenAnswer(invocation -> {
            int index = invocation.getArgument(0);
            return expectedColumnNames[index - 1];
        });
        Mockito.when(resultSet.getString(Mockito.anyInt())).thenReturn("SampleValue");

        contentController.populateWebcastTable(connection);


        assertEquals(expectedColumnNames.length, contentController.webcastTableView.getColumns().size());
        assertEquals(1, contentController.webcastTableView.getItems().size());
    }


}
