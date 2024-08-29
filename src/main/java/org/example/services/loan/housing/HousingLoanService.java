package org.example.services.loan.housing;

import org.example.entities.Student;
import org.example.services.loan.LoanService;

public interface HousingLoanService extends LoanService {
    boolean canTakeLoan(Student student);
    Student partnerStudetnt(Student student);

}
