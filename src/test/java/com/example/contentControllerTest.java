package com.example;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class contentControllerTest {

    private contentController contentController;

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockStatement;

    @Mock
    private ResultSet mockResultSet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        contentController = new contentController(null, null);
    }

    @Test
    void testPopulateWebcastTable() throws Exception {
        // Prepare expected column names
        String[] expectedColumnNames = {"Column1", "Column2"};

        // Mock behavior for connection, statement, and result set
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);

        ResultSetMetaData metaData = mock(ResultSetMetaData.class);
        when(metaData.getColumnCount()).thenReturn(expectedColumnNames.length);
        for (int i = 0; i < expectedColumnNames.length; i++) {
            when(metaData.getColumnName(i + 1)).thenReturn(expectedColumnNames[i]);
        }
        when(mockResultSet.getMetaData()).thenReturn(metaData);

        when(mockResultSet.getString(anyInt())).thenReturn("SampleValue");

        // Set mock connection in contentController
        contentController.setConnection(mockConnection);

        // Call the method under test
        contentController.populateWebcastTable();

        // Assertions
        assertEquals(expectedColumnNames.length, contentController.webcastTableView.getColumns().size());
        assertEquals(1, contentController.webcastTableView.getItems().size());
    }
}
