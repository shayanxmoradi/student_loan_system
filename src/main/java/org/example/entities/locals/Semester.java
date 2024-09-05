package org.example.entities.locals;

import jakarta.persistence.ManyToOne;
import org.example.entities.loan.StudyLoan;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
@Deprecated
public class Semester {

    private int year;
    private int semesterNumber;


    @Deprecated
    public Semester() {
        // Convert Date to LocalDate
        LocalDate localDate = LocalDate.now();

        // Assign year
        this.year = localDate.getYear();

        // Determine semester number based on month
        int month = localDate.getMonthValue(); // January = 1, December = 12
        if (month >= 1 && month <= 6) {
            this.semesterNumber = 1; // Winter Semester
        } else {
            this.semesterNumber = 2; // Summer Semester
        }
    }


}


