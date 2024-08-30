package org.example.repositories.loan;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.entities.BaseEntity;
import org.example.entities.Student;
import org.example.entities.loan.Loan;
import org.example.entities.loan.LoanInstallment;
import org.example.repositories.baseentity.BaseEntityRepoImpl;
import org.example.repositories.student.StudentRepo;
import org.example.util.Utilties;

import java.util.List;

public class LoanRepoImpl extends BaseEntityRepoImpl<Loan,Long> implements LoanRepo{

    public LoanRepoImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Loan> getEntityClass() {
        return Loan.class;
    }

    @Override
    public List<Loan> getAllLoans(Student student) {

        TypedQuery<Loan> query = entityManager.createQuery(
                "SELECT l FROM Loan l WHERE l.student.id= :student_id ", Loan.class);
        query.setParameter("student_id", student.getId());

      return query.getResultStream().toList();

    }

//    @Override
//    public List<Loan> getUnPaiedLoans(Student student) {
//        TypedQuery<Loan> query = entityManager.createQuery(
//                "SELECT l FROM Loan l WHERE l.student.id= :student_id and l.isPaiedOff= :paied_of", Loan.class);
//        query.setParameter("student_id", student.getId());
//        query.setParameter("paied_of", false);
//        return query.getResultStream().toList();
//    }

//    @Override
//    public List<Loan> getUnPaiedLoans(Student student) {
//        TypedQuery<Loan> query = entityManager.createQuery(
//                "SELECT l FROM Loan l JOIN l.loanInstallments i " +
//                "WHERE l.student = :student AND EXISTS (" +
//                "  SELECT 1 FROM LoanInstallment i2 " +
//                "  WHERE i2.loan = l AND i2.isPaiedOff = false" +
//                ")", Loan.class);
//        query.setParameter("student", student);
//        return query.getResultList();
//    }
    @Override
    public List<Loan> getUnPaiedLoans(Student student) {
        TypedQuery<Loan> query = entityManager.createQuery(
                "SELECT l FROM Loan l JOIN l.loanInstallments i " +
                "WHERE l.student = :student AND i.isPaiedOff = false", Loan.class);
        query.setParameter("student", student);
        return query.getResultList();
    }
//    @Override
//    public List<Loan> getUn(Student student) {
//        TypedQuery<Loan> query = entityManager.createQuery(
//                "SELECT l FROM Loan l JOIN l.loanInstallments i " +
//                "WHERE l.student = :student AND i.isPaiedOff = false", Loan.class);
//        query.setParameter("student", student);
//        return query.getResultList();
//    }



    @Override
    public List<Loan> getPaiedLoans(Student student) {
        TypedQuery<Loan> query = entityManager.createQuery(
                "SELECT l FROM Loan l WHERE l.student.id= :student_id and l.isPaiedOff= :paied_of ", Loan.class);
        query.setParameter("student_id", student.getId());
        query.setParameter("paied_of", true);
        return query.getResultStream().toList();
    }

//    @Override
//    public List<LoanInstallment> getUnpaiedInstallments(Student student) {
//
//
//
//            TypedQuery<LoanInstallment> query = entityManager.createQuery(
//                  //  "SELECT l FROM Loan l JOIN l.loanInstallments i " +
//                    "SELECT i FROM LoanInstallment i JOIN .loanInstallments i " +
//                    "WHERE l.student = :student AND i.isPaiedOff = false", LoanInstallment.class);
//            query.setParameter("student", student);
//            return query.getResultList();
//        }




}
