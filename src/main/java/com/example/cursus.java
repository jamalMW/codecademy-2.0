package com.example;
public class cursus {
    private String cursus;
    private String onderwerp;
    private String intro;
    private String niveau;

    public cursus(String cursus, String onderwerp, String intro, String niveau) {
        this.cursus = cursus;
        this.onderwerp = onderwerp;
        this.intro = intro;
        this.niveau = niveau;
    }

    public String getCursus() {
        return cursus;
    }

    public String getOnderwerp() {
        return onderwerp;
    }

    public String getIntro() {
        return intro;
    }

    public String getNiveau() {
        return niveau;
    }
}
