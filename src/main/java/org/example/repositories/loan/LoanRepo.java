package org.example.repositories.loan;

import org.example.entities.BaseEntity;
import org.example.entities.Student;
import org.example.entities.loan.Loan;
import org.example.entities.loan.LoanInstallment;
import org.example.repositories.baseentity.BaseEntityRepo;

import java.util.List;

public interface LoanRepo extends BaseEntityRepo<Loan,Long> {
    List<Loan> getAllLoans(Student student);

    List<Loan> getUnPaiedLoans(Student student);

    List<Loan> getPaiedLoans(Student student);

    List<LoanInstallment> getUnpaiedInstallments(Student student);
}
