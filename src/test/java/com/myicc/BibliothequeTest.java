package com.myicc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

public class BibliothequeTest {

    private Bibliotheque bibliotheque;

    @BeforeEach
    public void setUp() {
        bibliotheque = new Bibliotheque();
    }

    @Test
    public void testCapaciteBibliotheque() {

        for (int i = 0; i < 3000; i++) {
            bibliotheque.ajouterAdherent(new Adherent("John", "Adherent " + i));
        }

        try {
            bibliotheque.ajouterAdherent(new Adherent("John", "Adherent 3001"));
            fail("Une IllegalStateException aurait dû être lancée.");
        } catch (IllegalStateException e) {
            assertEquals("La limite maximale d'adhérents a été atteinte.", e.getMessage());
        }

        for (int i = 0; i < 500; i++) {
            bibliotheque.ajouterDocument(new Document("Document " + i));
        }

        try {
            bibliotheque.ajouterDocument(new Document("Document 501"));
            fail("Une IllegalStateException aurait dû être lancée.");
        } catch (IllegalStateException e) {
            assertEquals("La limite maximale de documents a été atteinte.", e.getMessage());
        }
    }

    @Test
    public void testAjouterAdherent() {
        Adherent adherent = new Adherent("John", "King");
        bibliotheque.ajouterAdherent(adherent);
        assertTrue(bibliotheque.adherents.contains(adherent));
    }

    @Test
    public void testAjouterDocument() {
        Document document = new Document("Document 1");
        bibliotheque.ajouterDocument(document);
        assertTrue(bibliotheque.documents.contains(document));
    }

    @Test
    public void testAjouterDeuxJournauxMemeDate() {
        bibliotheque.ajouterDocument(new Journal("Journal 1", LocalDate.of(2024, 1, 1)));
        try {
            bibliotheque.ajouterDocument(new Journal("Journal 2", LocalDate.of(2024, 1, 1)));
            fail("Une IllegalStateException aurait dû être lancée.");
        } catch (IllegalStateException e) {
            assertEquals("Un journal avec la même date existe déjà.", e.getMessage());
        }
    }
}
