package org.example.services.loan.studyloan;

import org.example.entities.BaseEntity;
import org.example.entities.Student;
import org.example.entities.loan.StudyLoan;
import org.example.repositories.loan.study.StudyLoanRepo;
import org.example.services.baseentity.BaseEntityService;
import org.example.services.baseentity.BaseEntityServiceImp;
import org.example.services.loan.LoanService;

public interface StudyLoanService extends LoanService {

    boolean registerStudyLoan(StudyLoan studyLoan);
    boolean canTakeLoanInThisSemester(Student student);
}
