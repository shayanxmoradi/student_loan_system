package org.example.repositories.loan.tuition;

import org.example.entities.Student;
import org.example.repositories.loan.LoanRepo;

public interface TuitionLoanRepo extends LoanRepo {
    boolean canTakeLoanInThisSemster(Student student);
}
