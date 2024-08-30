package org.example.services.loan.installment;

import org.example.entities.Student;
import org.example.entities.loan.Loan;
import org.example.entities.loan.LoanInstallment;
import org.example.repositories.loan.LoanRepo;
import org.example.repositories.loan.installments.InstallmentRepo;
import org.example.services.baseentity.BaseEntityServiceImp;
import org.example.services.loan.LoanService;

import java.util.List;

public class InstallmentServiceImpl extends BaseEntityServiceImp<LoanInstallment,Long, InstallmentRepo> implements InstallmentService {

InstallmentRepo installmentRepo;
    public InstallmentServiceImpl(InstallmentRepo baseRepository) {
        super(baseRepository);
        this.installmentRepo = baseRepository;
    }

    @Override
    public List<LoanInstallment> getInstallments(Student student, Boolean isPaied) {
        return installmentRepo.getInstallments(student,isPaied);
    }
}
