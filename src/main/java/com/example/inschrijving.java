package com.example;

import java.time.LocalDate;

public class inschrijving {
    private String emailadres;
    private String naamCursus;
    private LocalDate datum;

    public inschrijving(String emailadres, String naamCursus, LocalDate datum) {
        this.emailadres = emailadres;
        this.naamCursus = naamCursus;
        this.datum = datum;
    }

    public String getEmailadres() {
        return emailadres;
    }

    public String getNaamCursus() {
        return naamCursus;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setEmailadres(String emailadres) {
        this.emailadres = emailadres;
    }

    public void setNaamCursus(String naamCursus) {
        this.naamCursus = naamCursus;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }
}