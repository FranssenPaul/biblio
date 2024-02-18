package com.myicc;

import java.time.LocalDate;

public class Journal extends Document {
    LocalDate dateDeParution;

    public Journal(String titre, LocalDate dateDeParution) {
        super(titre);
        this.dateDeParution = dateDeParution;
    }

    public LocalDate getDateDeParution() {
        return dateDeParution;
    }
}