package org.example.repositories.student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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

    @Override
    public Student findByNationalAndPass(String nationalCode, String password) {
        TypedQuery<Student> query = entityManager.createQuery(
                "SELECT u FROM Student u WHERE u.nationalCode = :nationalCode AND u.password = :password", getEntityClass());
        query.setParameter("nationalCode", nationalCode);
        query.setParameter("password", password);
        return query.getResultStream().findFirst().orElse(null);
    }
}
