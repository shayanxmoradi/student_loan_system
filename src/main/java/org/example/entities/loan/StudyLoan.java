package org.example.entities.loan;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entities.enums.DegreeType;
import org.example.entities.locals.SemesterDeprecated;

import java.time.LocalDate;
import java.time.ZoneId;

@Table(name = "study_loan")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyLoan extends Loan {
    public static final String TABLE_NAME = "study_loan";

    @Enumerated(EnumType.STRING)
    private DegreeType degreeType;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "semester_id")
//    private SemesterDeprecated semesterDeprecated;

    private int LoanYear;
    private int LoanSemster;

    public void  setUpStudyLoan() {

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
