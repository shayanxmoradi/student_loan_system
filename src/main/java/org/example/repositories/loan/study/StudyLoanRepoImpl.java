package org.example.repositories.loan.study;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.entities.Student;
import org.example.entities.loan.Loan;
import org.example.entities.loan.StudyLoan;
import org.example.repositories.loan.LoanRepoImpl;
import org.example.util.Utilties;

import java.util.List;

public class StudyLoanRepoImpl extends LoanRepoImpl implements StudyLoanRepo {
    public StudyLoanRepoImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public boolean registerStudyLoan(StudyLoan studyLoan) {
        //todo save study loan


        return false;
    }

    @Override
    public boolean canTakeLoanInThisSemsster(Student student) {

        //todo query wich  in this query we check loan(studyloan) table with studentId
        //if found must check current semester with that semester if equal return false

        TypedQuery<Loan> query = entityManager.createQuery(
                "SELECT l FROM StudyLoan l WHERE l.student.id= :student_id and l.LoanSemster=:loan_semester and l.LoanYear=:loan_year", Loan.class);
        //todo finding paramter here ??
        query.setParameter("student_id", student.getId());
        query.setParameter("loan_semester", Utilties.getCurrentSemester());
        query.setParameter("loan_year", Utilties.getCurrentYear());
        List<Loan> loanList = query.getResultStream().toList();


        System.out.println(loanList.size());


        return loanList.size() == 0;
    }

//    @Override
//    public Class<Loan> getEntityClass() {
//        return StudyLoan.class;
//    }
    //todo wtf is this
}
