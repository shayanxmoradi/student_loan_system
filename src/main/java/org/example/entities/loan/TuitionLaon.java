package org.example.entities.loan;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entities.enums.DegreeType;

import java.time.LocalDate;
import java.time.ZoneId;

@Table(name = "tutation_loan")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TuitionLaon extends Loan{

    public static final String TABLE_NAME = "tutation_loan";

    @Enumerated(EnumType.STRING)
    private DegreeType degreeType;



    private int LoanYear;
    private int LoanSemster;


    public void  setUpTutationLoan() {

        // Assign year
        setLoanYear(LocalDate.now().getYear());

        // Determine semester number based on month
        int month = LocalDate.now().getMonthValue();
        if (month >= 1 && month <= 6) {
            setLoanSemster(1); // Winter Semester
        } else {
            setLoanSemster(2); // Summer Semester
        }
    }

}
