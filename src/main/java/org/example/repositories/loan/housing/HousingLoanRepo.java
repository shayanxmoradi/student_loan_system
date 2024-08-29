package org.example.repositories.loan.housing;

import org.example.entities.Student;
import org.example.repositories.loan.LoanRepo;

public interface HousingLoanRepo extends LoanRepo {
    boolean CanTakeLoan(Student student,Long partnerId);
}
