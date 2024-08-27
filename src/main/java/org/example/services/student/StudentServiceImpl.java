package org.example.services.student;

import jakarta.persistence.EntityManager;
import org.example.entities.Student;
import org.example.repositories.baseentity.BaseEntityRepoImpl;
import org.example.repositories.student.StudentRepo;
import org.example.services.baseentity.BaseEntityServiceImp;

public class StudentServiceImpl extends BaseEntityServiceImp<Student, Long, StudentRepo > implements StudentService {

StudentRepo studentRepo;
    public StudentServiceImpl(StudentRepo baseRepository) {
        super(baseRepository);
        studentRepo=baseRepository;
    }


    @Override
    public Student login(String nationalCode, String password) {
        return studentRepo.findByNationalAndPass(nationalCode, password);
    }
}
