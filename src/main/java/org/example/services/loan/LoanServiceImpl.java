package org.example.services.loan;

import org.example.entities.Student;
import org.example.entities.loan.Loan;
import org.example.entities.loan.LoanInstallment;
import org.example.repositories.loan.LoanRepo;
import org.example.services.baseentity.BaseEntityServiceImp;

import java.util.List;

public class LoanServiceImpl extends BaseEntityServiceImp<Loan,Long, LoanRepo> implements LoanService {
   LoanRepo loanRepo;
    public LoanServiceImpl(LoanRepo baseRepository) {
        super(baseRepository);
        this.loanRepo = baseRepository;
    }

    @Override
    public List<Loan> getAllLoans(Student student) {
        return loanRepo.getAllLoans(student);
    }

    @Override
    public List<Loan> getUnpaiedLoans(Student student) {
        return loanRepo.getUnPaiedLoans(student);
    }

    @Override
    public List<LoanInstallment> getUnpaiedInstallments(Student student) {
        return loanRepo.getUnpaiedInstallments(student);
    }

    @Override
    public List<Loan> getPaidLoans(Student student) {
        return loanRepo.getPaiedLoans(student);
    }
}
