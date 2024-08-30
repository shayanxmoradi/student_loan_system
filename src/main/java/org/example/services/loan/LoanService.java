package org.example.services.loan;

import org.example.entities.Student;
import org.example.entities.loan.Loan;
import org.example.entities.loan.LoanInstallment;
import org.example.services.baseentity.BaseEntityService;

import java.util.List;

public interface LoanService extends BaseEntityService<Loan,Long> {
    List<Loan> getAllLoans(Student student);
    List<Loan> getUnpaiedLoans(Student student);
    List<LoanInstallment> getUnpaiedInstallments(Student student);
    List<Loan> getPaidLoans(Student student);
}
