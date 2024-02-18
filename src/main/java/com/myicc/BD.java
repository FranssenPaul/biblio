package com.myicc;

public class BD extends Volume {

    private String dessinateur;

    public BD(String titre, String auteur, String dessinateur) {
        super(titre, auteur);
        this.dessinateur = dessinateur;
    }

    public String getDessinateur() {
        return dessinateur;
    }

    public void setDessinateur(String dessinateur) {
        this.dessinateur = dessinateur;
    }

    @Override
    public String toString() {
        return "BD [dessinateur=" + dessinateur + ", getAuteur()=" + getAuteur() + ", getTitre()=" + getTitre() + "]";
    }
}
