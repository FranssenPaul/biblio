package com.myicc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * La classe Bibliotheque représente une bibliothèque avec une capacité maximale
 * d'adhérents et de documents.
 * Elle fournit des méthodes pour ajouter des adhérents et des documents à la
 * bibliothèque.
 */
public class Bibliotheque {

    private static final int MAX_ADHERENTS = 3000;
    private static final int MAX_DOCUMENTS = 500;

    List<Adherent> adherents;
    List<Document> documents;

    /**
     * Construit une nouvelle bibliothèque vide.
     * 
     * Au départ, la bibliothèque ne contient aucun adhérent ni aucun document.
     */
    public Bibliotheque() {
        this.adherents = new ArrayList<>();
        this.documents = new ArrayList<>();
    }

    /**
     * Ajoute un adhérent à la bibliothèque.
     * 
     * @param adherent L'adhérent à ajouter.
     * @throws IllegalStateException si la capacité maximale d'adhérents a été
     *                               atteinte.
     */
    public void ajouterAdherent(Adherent adherent) {
        if (this.adherents.size() < MAX_ADHERENTS) {
            this.adherents.add(adherent);
        } else {
            throw new IllegalStateException("La limite maximale d'adhérents a été atteinte.");
        }
    }

    /**
     * Ajoute un document à la bibliothèque.
     * 
     * @param document Le document à ajouter.
     * @throws IllegalStateException si la capacité maximale de documents a été
     *                               atteinte.
     */
    public void ajouterDocument(Document document) {
        if (this.documents.size() >= MAX_DOCUMENTS) {
            throw new IllegalStateException("La limite maximale de documents a été atteinte.");
        }
        if (document instanceof Journal) {
            LocalDate dateDeParution = ((Journal) document).getDateDeParution();
            boolean existeDeja = this.documents.stream()
                    .filter(j -> j instanceof Journal)
                    .map(j -> (Journal) j)
                    .anyMatch(j -> j.getDateDeParution().equals(dateDeParution));
            if (existeDeja) {
                throw new IllegalStateException("Un journal avec la même date existe déjà.");
            }
        }
        this.documents.add(document);
    }

    /**
     * Renvoie une représentation sous forme de chaîne de la bibliothèque, indiquant
     * le nombre d'adhérents et de documents.
     * 
     * @return Une chaîne représentant la bibliothèque.
     */
    @Override
    public String toString() {
        int nbAdherents = this.adherents.size();
        int nbDocuments = this.documents.size();
        int nbLivres = (int) this.documents.stream().filter(d -> d instanceof Livre).count();

        double pourcentageAdherents = (nbAdherents * 100.0) / MAX_ADHERENTS;
        double pourcentageDocuments = (nbDocuments * 100.0) / MAX_DOCUMENTS;
        double pourcentageLivres = (nbLivres * 100.0) / MAX_DOCUMENTS;

        return String.format("%d adhérents (%.1f%%) - %d documents (%.1f%%) dont %d livres (%.1f%%)",
                nbAdherents, pourcentageAdherents, nbDocuments, pourcentageDocuments, nbLivres, pourcentageLivres);
    }

    /**
     * Renvoie la liste des livres de la bibliothèque.
     * 
     * @return La liste des livres de la bibliothèque, c'est une ArrayList.
     */
    public ArrayList<Livre> getLivres() {
        ArrayList<Livre> livres = documents.stream()
                .filter(d -> d instanceof Livre)
                .map(d -> (Livre) d)
                .collect(Collectors.toCollection(ArrayList::new));
        return livres;
    }

    /**
     * Renvoie un livre choisi aléatoirement parmi ceux de la bibliothèque.
     * 
     * @return Un livre choisi aléatoirement.
     * @throws NoSuchElementException si la bibliothèque ne contient aucun livre.
     */
    public Livre randomBook() {
        ArrayList<Livre> livres = getLivres();
        if (livres.isEmpty()) {
            throw new NoSuchElementException("Aucun livre trouvé dans la bibliothèque.");
        }
        int random = (int) (Math.random() * livres.size());
        return livres.get(random);
    }

    /**
     * Permet à un adhérent d'emprunter un livre.
     * 
     * @param adherent L'adhérent qui souhaite emprunter le livre.
     * @param livre    Le livre à emprunter.
     * @throws IllegalStateException Si le livre est déjà emprunté ou si l'adhérent
     *                               a
     *                               déjà emprunté 10 livres.
     */
    public void emprunterLivre(Adherent adherent, Livre livre) {
        if (livre.getEmprunteur() != null) {
            throw new IllegalStateException("Le livre est déjà emprunté.");
        }
        if (adherent.getEmprunts().size() >= 10) {
            throw new IllegalStateException("L'adhérent a déjà emprunté 10 livres.");
        }
        livre.setEmprunteur(adherent);
        adherent.getEmprunts().add(livre);
        livre.setDateEmprunt(LocalDate.now());
    }
}