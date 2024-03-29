package com.example;

import java.time.LocalDate;

public class cursist {
    private String naam;
    private String geslacht;
    private String adres;
    private String woonplaats;
    private String land;
    private String emailadres;
    private LocalDate geboorteDatum;
    private String postcode;
    private Integer huisnummer;

    public cursist(String naam, LocalDate geboorteDatum, String geslacht, String adres, String woonplaats, String land, String emailadres, String postcode, Integer huisnummer) {

        this.naam = naam;
        this.geboorteDatum = geboorteDatum;
        this.geslacht = geslacht;
        this.adres = adres;
        this.woonplaats = woonplaats;
        this.land = land;
        this.emailadres = emailadres;
        this.postcode = postcode;
        this.huisnummer = huisnummer;
    }

    

    public String getNaam() {
        return naam;
    }

    public String getGeslacht() {
        return geslacht;
    }

    public String getAdres() {
        return adres;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public String getLand() {
        return land;
    }

    public String getEmailadres() {
        return emailadres;
    }

    public LocalDate getGeboorteDatum() {
        return geboorteDatum;
    }

    public String getPostcode() {
        return postcode;
    }

    public Integer getHuisnummer() {
        return huisnummer;
    }
}
