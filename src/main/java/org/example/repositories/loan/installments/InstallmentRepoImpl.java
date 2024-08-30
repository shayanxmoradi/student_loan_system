package org.example.repositories.loan.installments;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.entities.Student;
import org.example.entities.loan.LoanInstallment;
import org.example.repositories.baseentity.BaseEntityRepoImpl;

import java.util.List;

public class InstallmentRepoImpl extends BaseEntityRepoImpl<LoanInstallment,Long> implements InstallmentRepo {

    public InstallmentRepoImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<LoanInstallment> getEntityClass() {
        return LoanInstallment.class;
    }
    @Override
    public List<LoanInstallment> getInstallments(Student student, Boolean isPaiedOff) {
        TypedQuery<LoanInstallment> query = entityManager.createQuery(
                "SELECT i " +
                "FROM LoanInstallment i " +
                "JOIN i.loan l " +
                "WHERE l.student = :student " +
                "AND i.isPaiedOff ="+isPaiedOff +
                " ORDER BY i.installmentDate ASC", LoanInstallment.class);

        query.setParameter("student", student);
        query.setMaxResults(10);

        return query.getResultList();
    }
}
