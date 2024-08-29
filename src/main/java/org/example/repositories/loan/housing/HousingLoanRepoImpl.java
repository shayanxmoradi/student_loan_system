package org.example.repositories.loan.housing;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.entities.Student;
import org.example.entities.loan.Loan;
import org.example.repositories.loan.LoanRepoImpl;

import java.util.List;

public class HousingLoanRepoImpl extends LoanRepoImpl implements HousingLoanRepo {
    public HousingLoanRepoImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public boolean CanTakeLoan(Student student, Long partnerId) {

        TypedQuery<Loan> query = entityManager.createQuery(
                "SELECT l FROM HousingLoan l WHERE l.student.id= :student_id OR l.student.id=:partner_id", Loan.class);
        query.setParameter("student_id", student.getId());
        query.setParameter("partner_id", partnerId);
        List<Loan> loanList = query.getResultStream().toList();

        return loanList.isEmpty();
    }
}
