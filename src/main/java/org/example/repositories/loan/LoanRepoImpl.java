package org.example.repositories.loan;

import jakarta.persistence.EntityManager;
import org.example.entities.BaseEntity;
import org.example.entities.Student;
import org.example.entities.loan.Loan;
import org.example.repositories.baseentity.BaseEntityRepoImpl;
import org.example.repositories.student.StudentRepo;

public class LoanRepoImpl extends BaseEntityRepoImpl<Loan,Long> implements LoanRepo{

    public LoanRepoImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Loan> getEntityClass() {
        return Loan.class;
    }
}
