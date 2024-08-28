package org.example.repositories.loan.study;

import org.example.entities.Student;
import org.example.entities.loan.StudyLoan;
import org.example.repositories.loan.LoanRepo;

public interface StudyLoanRepo extends LoanRepo {

    boolean registerStudyLoan(StudyLoan studyLoan);

    boolean canTakeLoanInThisSemsster(Student student);
}
