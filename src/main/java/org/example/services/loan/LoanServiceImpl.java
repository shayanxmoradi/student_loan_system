package org.example.services.loan;

import org.example.entities.loan.Loan;
import org.example.repositories.loan.LoanRepo;
import org.example.services.baseentity.BaseEntityServiceImp;

public class LoanServiceImpl extends BaseEntityServiceImp<Loan,Long, LoanRepo> implements LoanService {
    public LoanServiceImpl(LoanRepo baseRepository) {
        super(baseRepository);
    }
}
