package com.myicc;

import java.time.LocalDate;

/**
 * La classe Livre représente un livre dans une bibliothèque.
 * Chaque livre a un titre, un auteur, une date d'emprunt et peut être emprunté
 * par un adhérent.
 */
public class Livre extends Volume {

    private static final int DUREE_MAX = 30;

    private Adherent emprunteur;

    private LocalDate dateEmprunt;

    /**
     * Construit un nouveau livre avec un titre et un auteur spécifiés.
     * Au départ, le livre n'est emprunté par aucun adhérent.
     *
     * @param titre  Le titre du livre.
     * @param auteur L'auteur du livre.
     */
    public Livre(String titre, String auteur) {
        super(titre, auteur);
        this.emprunteur = null;
        this.dateEmprunt = null;
    }

    /**
     * Renvoie l'adhérent qui a emprunté le livre, ou null si le livre n'est pas
     * emprunté.
     *
     * @return L'adhérent qui a emprunté le livre, ou null si le livre n'est pas
     *         emprunté.
     */
    public Adherent getEmprunteur() {
        return emprunteur;
    }

    /**
     * Définit l'adhérent qui a emprunté le livre.
     * Pour indiquer que le livre n'est emprunté par aucun adhérent, passez null.
     *
     * @param emprunteur L'adhérent qui a emprunté le livre, ou null si le livre
     *                   n'est pas emprunté.
     */
    public void setEmprunteur(Adherent emprunteur) {
        this.emprunteur = emprunteur;
    }

    /**
     * Renvoie une représentation sous forme de chaîne du livre, indiquant l'auteur
     * et le titre.
     *
     * @return Une chaîne représentant le livre.
     */
    @Override
    public String toString() {
        return "Livre [Auteur=" + getAuteur() + ", Titre=" + getTitre() + "]";
    }

    /**
     * La méthode emprunter permet à un adhérent d'emprunter un livre.
     * Si le livre est déjà emprunté ou si l'adhérent a déjà emprunté 10 livres, une
     * exception est levée.
     * Sinon, l'adhérent est enregistré comme l'emprunteur du livre, le livre est
     * ajouté à la liste des emprunts de l'adhérent, et la date d'emprunt est
     * définie sur la date actuelle.
     *
     * @param adherent L'adhérent qui souhaite emprunter le livre.
     * @throws IllegalStateException Si le livre est déjà emprunté ou si l'adhérent
     *                               a déjà emprunté 10 livres.
     */
    public void emprunter(Adherent adherent) {
        if (this.emprunteur != null) {
            throw new IllegalStateException("Le livre est déjà emprunté.");
        }
        if (adherent.getEmprunts().size() >= 10) {
            throw new IllegalStateException("L'adhérent a déjà emprunté 10 livres.");
        }
        this.emprunteur = adherent;
        adherent.getEmprunts().add(this);
        this.dateEmprunt = LocalDate.now();
    }

    /**
     * Retourne la date à laquelle le livre a été emprunté.
     *
     * @return La date d'emprunt du livre.
     */
    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    /**
     * Définit la date à laquelle le livre a été emprunté.
     *
     * @param dateEmprunt La date d'emprunt du livre.
     */
    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    /**
     * Retourne la date à laquelle le livre doit être retourné.
     * Si le livre n'est pas emprunté, une exception est levée.
     *
     * @return La date de retour du livre.
     * @throws IllegalStateException Si le livre n'est pas emprunté.
     */
    public LocalDate getDateRetour() {
        if (dateEmprunt == null) {
            throw new IllegalStateException("Le livre n'est pas emprunté.");
        }
        return dateEmprunt.plusDays(DUREE_MAX);
    }

}