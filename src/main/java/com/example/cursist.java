package com.example;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;

public class cursist {
    private StringProperty naam;
    private ObjectProperty<LocalDate> geboorteDatum;
    private StringProperty geslacht;
    private StringProperty adres;
    private StringProperty woonplaats;
    private StringProperty land;
    private StringProperty emailadres;

    public cursist(String naam, LocalDate geboorteDatum, String geslacht, String adres, String woonplaats, String land, String emailadres) {
        this.naam = new SimpleStringProperty(naam);
        this.geboorteDatum = new SimpleObjectProperty<>(geboorteDatum);
        this.geslacht = new SimpleStringProperty(geslacht);
        this.adres = new SimpleStringProperty(adres);
        this.woonplaats = new SimpleStringProperty(woonplaats);
        this.land = new SimpleStringProperty(land);
        this.emailadres = new SimpleStringProperty(emailadres);
        
    }

    public StringProperty naamProperty() {
        return naam;
    }

    public ObjectProperty<LocalDate> geboorteDatumProperty() {
        return geboorteDatum;
    }

    public StringProperty geslachtProperty() {
        return geslacht;
    }

    public StringProperty adresProperty() {
        return adres;
    }

    public StringProperty woonplaatsProperty() {
        return woonplaats;
    }

    public StringProperty landProperty() {
        return land;
    }

      public StringProperty emailadresProperty() {
        return emailadres;
    }

    @FXML
    public String getNaam() {
        return naam.get();
    }
}
