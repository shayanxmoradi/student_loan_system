package org.example.services.loan.studyloan;

import org.example.entities.Student;
import org.example.entities.loan.StudyLoan;
import org.example.repositories.loan.study.StudyLoanRepo;
import org.example.services.loan.LoanServiceImpl;

public class StudyLoanServiceImpl extends LoanServiceImpl implements StudyLoanService {
   StudyLoanRepo studyLoanRepo;
    public StudyLoanServiceImpl(StudyLoanRepo baseRepository) {
        super(baseRepository);
        this.studyLoanRepo = baseRepository;
    }

    @Override
    public boolean registerStudyLoan(StudyLoan studyLoan) {
      return   studyLoanRepo.registerStudyLoan(studyLoan);

    }

    @Override
    public boolean canTakeLoanInThisSemester(Student student) {
        return studyLoanRepo.canTakeLoanInThisSemsster(student);
    }
}
