package com.myicc;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdherentTest {

    @Test
    public void testAdherent() {
        Adherent adherent = new Adherent("Jean", "Dupont");
        String expected = "Adherent [prenom=Jean, nom=DUPONT]";
        assertEquals(expected, adherent.toString());
    }
}