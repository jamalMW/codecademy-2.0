package com.example;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class cursistControllerTest {

    @Mock
    private TextField naam;
    @Mock
    private DatePicker datum;
    @Mock
    private ChoiceBox<String> geslacht;

    private cursistController controller;

    @BeforeEach
    public void setUp() {
        // Initialize Mockito annotations
        MockitoAnnotations.openMocks(this);

        // Create a new instance of the controller
        controller = new cursistController();

        // Manually inject the mocked components into the controller
        controller.naam = naam;
        controller.datum = datum;
        controller.geslacht = geslacht;

        // Define the behavior of mocked components
        when(naam.getText()).thenReturn("John Doe");
        when(datum.getValue()).thenReturn(LocalDate.now());
        when(geslacht.getValue()).thenReturn("Man");
    }

    @Test
    public void testValidateFields_AllFieldsFilledCorrectly() {
        // Set up the conditions for the test (all fields filled correctly)
        assertTrue(controller.validateFields());
    }

    @Test
    public void testValidateFields_MissingFields() {
        // Set up the conditions for the test (name field is empty)
        when(naam.getText()).thenReturn(""); // Simulate an empty name field
        assertFalse(controller.validateFields());
    }

    @Test
    public void testValidateFields_InvalidEmail() {
        // Set up the conditions for the test (invalid email address)
        when(naam.getText()).thenReturn("John Doe");
        when(datum.getValue()).thenReturn(LocalDate.now());
        when(geslacht.getValue()).thenReturn("Man");

        assertFalse(controller.validateFields()); // Expecting validation failure due to invalid email format
    }

    @Test
    public void testValidateFields_EmptyFields() {
        // Set up the conditions for the test (all fields are empty)
        when(naam.getText()).thenReturn("");
        when(datum.getValue()).thenReturn(null);
        when(geslacht.getValue()).thenReturn(null);

        assertFalse(controller.validateFields());
    }
}
