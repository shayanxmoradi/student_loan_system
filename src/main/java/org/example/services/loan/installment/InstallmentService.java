package org.example.services.loan.installment;

import org.example.entities.Student;
import org.example.entities.loan.Loan;
import org.example.entities.loan.LoanInstallment;
import org.example.services.baseentity.BaseEntityService;

import java.util.List;

public interface InstallmentService extends BaseEntityService<LoanInstallment,Long> {
    List<LoanInstallment> getInstallments(Student student, Boolean isPaied);

}
