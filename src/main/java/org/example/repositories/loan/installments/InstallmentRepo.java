package org.example.repositories.loan.installments;

import org.example.entities.Student;
import org.example.entities.loan.LoanInstallment;
import org.example.repositories.baseentity.BaseEntityRepo;
import org.example.services.baseentity.BaseEntityService;

import java.util.List;

public interface InstallmentRepo extends BaseEntityRepo<LoanInstallment,Long> {
    List<LoanInstallment> getInstallments(Student student, Boolean isPaied);

}
