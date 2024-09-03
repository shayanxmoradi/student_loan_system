package org.example.repositories.loan.tuition;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.entities.Student;
import org.example.entities.loan.Loan;
import org.example.repositories.loan.LoanRepoImpl;
import org.example.util.Utilties;

import java.util.List;

public class TuitionLoanRepoImpl extends LoanRepoImpl implements TuitionLoanRepo {
    public TuitionLoanRepoImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public boolean canTakeLoanInThisSemster(Student student) {
        TypedQuery<Loan> query = entityManager.createQuery(
                "SELECT l FROM TuitionLaon l WHERE l.student.id= :student_id and l.LoanSemster=:loan_semester and l.LoanYear=:loan_year", Loan.class);
        query.setParameter("student_id", student.getId());
        query.setParameter("loan_semester", Utilties.getCurrentSemester());
        query.setParameter("loan_year", Utilties.getCurrentYear());
        List<Loan> loanList = query.getResultStream().toList();
        return loanList.isEmpty();
    }
}
