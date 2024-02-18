package com.myicc;

import java.util.ArrayList;

/**
 * La classe Adherent représente un adhérent d'une bibliothèque.
 * Un adhérent a un prénom, un nom et une liste de livres qu'il a empruntés.
 */
public class Adherent {

    private String prenom;
    private String nom;
    private ArrayList<Livre> emprunts;

    /**
     * Crée un nouvel adhérent avec le prénom et le nom spécifiés.
     *
     * @param prenom Le prénom de l'adhérent.
     * @param nom    Le nom de l'adhérent.
     */
    public Adherent(String prenom, String nom) {
        this.prenom = prenom;
        this.nom = nom;
        this.emprunts = new ArrayList<>();
    }

    /**
     * Renvoie le prénom de l'adhérent.
     *
     * @return Le prénom de l'adhérent.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Définit le prénom de l'adhérent.
     *
     * @param prenom Le prénom de l'adhérent.
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Renvoie le nom de l'adhérent.
     *
     * @return Le nom de l'adhérent.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom de l'adhérent.
     *
     * @param nom Le nom de l'adhérent.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Renvoie une représentation sous forme de chaîne de l'adhérent, indiquant le
     * prénom et le nom.
     *
     * @return Une chaîne représentant l'adhérent.
     */
    @Override
    public String toString() {
        return "Adherent [prenom=" + prenom + ", nom=" + nom.toUpperCase() + "]";
    }

    /**
     * Crée un nouvel adhérent avec le prénom et le nom spécifiés.
     *
     * @param prenom Le prénom de l'adhérent.
     * @param nom    Le nom de l'adhérent.
     * @return Un nouvel adhérent avec le prénom et le nom spécifiés.
     */
    public static Adherent of(String prenom, String nom) {
        return new Adherent(prenom, nom);
    }

    /**
     * Crée un nouvel adhérent à partir d'une chaîne de caractères.
     *
     * @param fullname Le prénom et le nom de l'adhérent, séparés par une virgule et
     *                 un espace.
     * @return Un nouvel adhérent avec le prénom et le nom spécifiés.
     */
    public static Adherent fromString(String fullname) {
        String[] parts = fullname.split(", ");
        return new Adherent(parts[0], parts[1]);
    }

    /**
     * Permet à l'adhérent d'emprunter un livre.
     * Si le livre est déjà emprunté ou si l'adhérent a déjà emprunté 10 livres, une
     * exception est levée.
     *
     * @param livre Le livre à emprunter.
     * @throws IllegalStateException Si le livre est déjà emprunté ou si l'adhérent
     *                               a déjà emprunté 10 livres.
     */
    public void emprunter(Livre livre) {
        if (livre.getEmprunteur() != null) {
            throw new IllegalStateException("Ce livre est déjà emprunté");
        }
        if (emprunts.size() >= 10) {
            throw new IllegalStateException("Cet adhérent a déjà emprunté 10 livres");
        }
        livre.setEmprunteur(this);
        emprunts.add(livre);
    }

    /**
     * Renvoie la liste des livres empruntés par l'adhérent.
     *
     * @return La liste des livres empruntés par l'adhérent, c'est une ArrayList.
     */
    public ArrayList<Livre> getEmprunts() {
        return emprunts;
    }

    /**
     * Permet à l'adhérent de rendre un livre.
     * Si le livre n'est pas emprunté par cet adhérent, une exception est levée.
     *
     * @param livre Le livre à rendre.
     * @throws IllegalStateException Si le livre n'est pas emprunté par cet
     *                               adhérent.
     */
    public void rendre(Livre livre) {
        if (livre.getEmprunteur() != this) {
            throw new IllegalStateException("Ce livre n'est pas emprunté par cet adhérent");
        }
        livre.setEmprunteur(null);
        livre.setDateEmprunt(null);
        emprunts.remove(livre);
    }
}