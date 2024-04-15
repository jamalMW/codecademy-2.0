package com.example;

import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class cursistControllerTest {

    private TextField naam;
    private DatePicker datum;
    private ChoiceBox<String> geslacht;

    private cursistController controller;

    @BeforeEach
    public void setUp() {
        // Create real instances of JavaFX controls
        naam = new TextField();
        datum = new DatePicker();
        geslacht = new ChoiceBox<>(FXCollections.observableArrayList("Man", "Vrouw"));

        // Create a new instance of the controller
        controller = new cursistController();

        // Set the JavaFX controls on the controller
        controller.naam = naam;
        controller.datum = datum;
        controller.geslacht = geslacht;
    }

    @Test
    public void testValidateFields_AllFieldsFilledCorrectly() {
        // Set up the conditions for the test (all fields filled correctly)
        naam.setText("John Doe");
        datum.setValue(LocalDate.now());
        geslacht.setValue("Man");

        // Now test the validateFields method
        assertTrue(controller.validateFields());
    }

    @Test
    public void testValidateFields_MissingFields() {
        // Set up the conditions for the test (name field is empty)
        naam.setText(""); // Simulate an empty name field
        datum.setValue(LocalDate.now());
        geslacht.setValue("Man");

        assertFalse(controller.validateFields());
    }

    @Test
    public void testValidateFields_InvalidEmail() {
        // Set up the conditions for the test (invalid email address)
        naam.setText("John Doe");
        datum.setValue(LocalDate.now());
        geslacht.setValue("Man");

        assertFalse(controller.validateFields()); // Expecting validation failure due to invalid email format
    }

    @Test
    public void testValidateFields_EmptyFields() {
        // Set up the conditions for the test (all fields are empty)
        naam.setText("");
        datum.setValue(null);
        geslacht.setValue(null);

        assertFalse(controller.validateFields());
    }
}
