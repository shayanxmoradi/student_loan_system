package org.example.services.student;

import org.example.entities.Student;

public interface StudentService extends org.example.services.baseentity.BaseEntityService<Student,Long> {
    Student login(String nationalCode, String password);

}
