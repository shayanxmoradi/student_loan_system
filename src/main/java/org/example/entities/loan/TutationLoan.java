package org.example.entities.loan;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.example.entities.enums.DegreeType;
import org.example.entities.locals.SemesterDeprecated;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Table(name = "tutation_loan")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TutationLoan extends Loan{

    public static final String TABLE_NAME = "tutation_loan";

    @Enumerated(EnumType.STRING)
    private DegreeType degreeType;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "semester_id")
//    private SemesterDeprecated semesterDeprecated;

    private int LoanYear;
    private int LoanSemster;


    public void setUpTutationLoan() {
        // Convert Date to LocalDate
        LocalDate localDate = getLoanDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        // Assign year
        setLoanYear(localDate.getYear());

        // Determine semester number based on month
        int month = localDate.getMonthValue(); // January = 1, December = 12
        if (month >= 1 && month <= 6) {
           setLoanSemster(1); // Winter Semester
        } else {
         setLoanSemster(2); // Summer Semester
        }
    }


}
