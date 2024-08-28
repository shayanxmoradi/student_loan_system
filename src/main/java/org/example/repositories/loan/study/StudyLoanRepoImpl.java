package org.example.repositories.loan.study;

import jakarta.persistence.EntityManager;
import org.example.entities.loan.Loan;
import org.example.entities.loan.StudyLoan;
import org.example.repositories.loan.LoanRepoImpl;

public class StudyLoanRepoImpl extends LoanRepoImpl implements StudyLoanRepo {
    public StudyLoanRepoImpl(EntityManager entityManager) {
        super(entityManager);
    }

//    @Override
//    public Class<Loan> getEntityClass() {
//        return StudyLoan.class;
//    }
    //todo wtf is this
}
