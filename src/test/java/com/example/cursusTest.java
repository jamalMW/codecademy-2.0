package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class cursusTest {

    private final cursus testCursus = new cursus("Programmeren 1", "Java", "Testintro", "expert");

    @Test
    public void testGetCursus() {
        assertEquals("Programmeren 1", testCursus.getCursus());
    }

    @Test
    public void testGetOnderwerp() {
        assertEquals("Java", testCursus.getOnderwerp());
    }

    @Test
    public void testGetIntro() {
        assertEquals("Testintro", testCursus.getIntro());
    }

    @Test
    public void testGetNiveau() {
        assertEquals("exper", testCursus.getNiveau());
    }
}
