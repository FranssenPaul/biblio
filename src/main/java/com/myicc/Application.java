package com.myicc;

import java.time.LocalDate;

public class Application {
    public static void main(String[] args) {

        Bibliotheque bibliotheque = new Bibliotheque();

        Adherent adherent1 = new Adherent("Elvis", "Presley");
        Adherent adherent2 = new Adherent("John", "Lennon");
        bibliotheque.ajouterAdherent(adherent1);
        bibliotheque.ajouterAdherent(adherent2);

        Journal journal = new Journal("Journal 1", LocalDate.now());
        Dictionnaire dictionnaire = new Dictionnaire("Larousse", "Bellemaire");
        BD bd = new BD("Les Piafs", "Uderzo", "Goscinny");
        Livre livre = new Livre("Oubli", "Sartre");
        bibliotheque.ajouterDocument(journal);
        bibliotheque.ajouterDocument(dictionnaire);
        bibliotheque.ajouterDocument(bd);
        bibliotheque.ajouterDocument(livre);

        adherent1.emprunter(livre);

    }
}