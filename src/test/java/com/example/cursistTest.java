package com.example;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class cursistTest {

    @Test
    public void testCursistConstructor() {
        // Voorbeeld invoer met Lorem Ipsum-gegevens
        String naam = "Lorem Ipsum";
        LocalDate geboorteDatum = LocalDate.of(1990, 1, 1);
        String geslacht = "Man";
        String adres = "Lorem Street 123";
        String woonplaats = "Lorem City";
        String land = "Loremland";
        String emailadres = "lorem.ipsum@example.com";
        String postcode = "1234 AB";
        Integer huisnummer = 42;

        // Maak een nieuwe cursist met de gegeven invoer
        cursist testCursist = new cursist(naam, geboorteDatum, geslacht, adres, woonplaats, land, emailadres, postcode, huisnummer);

        // Verifieer dat de attributen correct zijn ingesteld
        assertEquals(naam, testCursist.getNaam());
        assertEquals(geboorteDatum, testCursist.getGeboorteDatum());
        assertEquals(geslacht, testCursist.getGeslacht());
        assertEquals(adres, testCursist.getAdres());
        assertEquals(woonplaats, testCursist.getWoonplaats());
        assertEquals(land, testCursist.getLand());
        assertEquals(emailadres, testCursist.getEmailadres());
        assertEquals(postcode, testCursist.getPostcode());
        assertEquals(huisnummer, testCursist.getHuisnummer());
    }
}
