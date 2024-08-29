package org.example.services.loan.tuitionloan;

import org.example.entities.Student;
import org.example.repositories.loan.LoanRepo;
import org.example.repositories.loan.study.StudyLoanRepo;
import org.example.repositories.loan.tuition.TuitionLoanRepo;
import org.example.services.loan.LoanServiceImpl;

public class TuitionLoanServiceImpl extends LoanServiceImpl implements TuitionLoanService {
    TuitionLoanRepo tuitionLoanRepo;

    public TuitionLoanServiceImpl(TuitionLoanRepo tuitionLoanRepo) {
        super(tuitionLoanRepo);
        this.tuitionLoanRepo = tuitionLoanRepo;
    }


    @Override
    public boolean canTakeLoanInThisSemester(Student student) {
        return tuitionLoanRepo.canTakeLoanInThisSemster(student);
    }
}
