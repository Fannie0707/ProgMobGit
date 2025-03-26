package com.example.tp3exo1;

public class Donnes {
    private final String nom;
    private final String prenom;
    private final String DateNaissance;
    private final String NumTel;
    private final String Email;
    private final String Interet;

    public Donnes(String nom, String prenom, String DateNaissance, String NumTel, String Email, String Interet) {
        this.nom = nom;
        this.prenom = prenom;
        this.DateNaissance =DateNaissance;
        this.NumTel=NumTel;
        this.Email=Email;
        this.Interet=Interet;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDateNaissance() {
        return DateNaissance;
    }

    public String getNumTel() {
        return NumTel;
    }

    public String getEmail() {
        return Email;
    }

    public String getInteret() {return Interet;}
}
