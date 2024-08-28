package org.example.services.student;

import org.example.entities.Student;
import org.example.repositories.student.StudentRepo;
import org.example.services.baseentity.BaseEntityServiceImp;

public class StudentServicempl extends BaseEntityServiceImp<Student, Long, StudentRepo > implements StudentService {

StudentRepo studentRepo;
//todo is this ok?
    public StudentServicempl(StudentRepo baseRepository) {
        super(baseRepository);
        studentRepo=baseRepository;
    }


    @Override
    public Student login(String nationalCode, String password) {
        return studentRepo.findByNationalAndPass(nationalCode, password);
    }
}
