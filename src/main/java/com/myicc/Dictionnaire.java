package com.myicc;

public class Dictionnaire extends Volume {

    public Dictionnaire(String titre, String auteur) {
        super(titre, auteur);
    }

    @Override
    public String toString() {
        return "Dictionnaire [getAuteur()=" + getAuteur() + ", getTitre()=" + getTitre() + "]";
    }

}
