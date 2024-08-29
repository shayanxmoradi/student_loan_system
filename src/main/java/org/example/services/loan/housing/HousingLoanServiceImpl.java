package org.example.services.loan.housing;

import org.example.entities.Student;
import org.example.repositories.loan.LoanRepo;
import org.example.repositories.loan.housing.HousingLoanRepo;
import org.example.repositories.student.StudentRepo;
import org.example.services.loan.LoanServiceImpl;

public class HousingLoanServiceImpl extends LoanServiceImpl implements HousingLoanService {
    HousingLoanRepo housingLoanRepo;
    StudentRepo studentRepo;
    public HousingLoanServiceImpl(HousingLoanRepo housingLoanRepo,StudentRepo studentRepo) {
        super(housingLoanRepo);
        this.housingLoanRepo = housingLoanRepo;
        this.studentRepo=studentRepo;
    }

    @Override
    public boolean canTakeLoan(Student student) {
        Long partnerId=0l;
        try {

             partnerId = studentRepo.findStudentByNationalCode(student.getPartnerNationalCode()).getId();
        }catch (Exception e){
            System.out.println("partner not student");
        }
        return housingLoanRepo.CanTakeLoan(student,partnerId);
    }

    @Override
    public Student partnerStudetnt(Student student) {

      return studentRepo.findStudentByNationalCode(student.getPartnerNationalCode());

    }
}
