package org.example.services.loan.tuitionloan;

import org.example.entities.Student;
import org.example.services.loan.LoanService;

public interface TuitionLoanService extends LoanService {
    boolean canTakeLoanInThisSemester(Student student);

}
