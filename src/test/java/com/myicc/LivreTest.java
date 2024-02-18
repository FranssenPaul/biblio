package com.myicc;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LivreTest {
    @Test
    public void testLivre() {
        Livre livre = new Livre("Le Petit Prince", "Antoine de Saint-Exupéry");
        assertEquals("Le Petit Prince", livre.getTitre());
        assertEquals("Antoine de Saint-Exupéry", livre.getAuteur());
    }

    @Test
    public void testLivreVolume() {
        Livre livre = new Livre("Le Petit Prince", "Antoine de Saint-Exupéry");
        assertTrue(livre instanceof Volume);
    }

    @Test
    public void testToString() {
        Livre livre = new Livre("Le Petit Prince", "Antoine de Saint-Exupéry");
        String expected = "Livre [Auteur=Antoine de Saint-Exupéry, Titre=Le Petit Prince]";
        assertEquals(expected, livre.toString());
    }
}
