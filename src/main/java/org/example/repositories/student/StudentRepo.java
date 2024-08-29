package org.example.repositories.student;

import org.example.entities.Student;
import org.example.repositories.baseentity.BaseEntityRepo;

public interface StudentRepo extends BaseEntityRepo<Student,Long> {
    Student findByNationalAndPass(String nationalCode, String password);
    Student findStudentByNationalCode(String nationalCode);

}
