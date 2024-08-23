package org.example.repositories.student;

import jakarta.persistence.EntityManager;
import org.example.entities.Student;
import org.example.repositories.baseentity.BaseEntityRepoImpl;

public class StudentRepoImpl extends BaseEntityRepoImpl<Student, Long> implements StudentRepo {

    public StudentRepoImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Student> getEntityClass() {
        return Student.class;
    }

    @Override
    public Student update(Student entity) {

        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
        return entity;
    }
}
