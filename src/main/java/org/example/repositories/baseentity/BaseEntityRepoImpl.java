package org.example.repositories.baseentity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.entities.BaseEntity;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public abstract class BaseEntityRepoImpl<T extends BaseEntity<ID>, ID extends Serializable> implements BaseEntityRepo<T, ID> {
    public final EntityManager entityManager;

    public BaseEntityRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public T save(T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        //   System.out.println("Generated ID: " + entity.getId());
        return entity;

    }

    @Override
    public T update(T entity) {
        //todo problem
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public boolean delete(T entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
        return false;
    }

    @Override
    public boolean deleteById(ID id) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(getEntityClass(), id));
        entityManager.getTransaction().commit();
        return false;
    }

    @Override
    public T findById(ID id) {
        entityManager.getTransaction().begin();
        T founded;

        try {
            // Find the entity by its ID
            founded = entityManager.find(getEntityClass(), id);
        } finally {
            entityManager.close();
        }
        entityManager.getTransaction().commit();
        return founded;

    }

    @Override
    public List<T> findAll() {

        entityManager.getTransaction().begin();
        // Create a JPQL query to select all entities of type T
        System.out.println("xxxxx" + getEntityClass().getName());
        TypedQuery<T> query = entityManager.createQuery("SELECT e FROM " + getEntityClass().getName() + " e", getEntityClass());

        return query.getResultList();
    }

    public abstract Class<T> getEntityClass();
}
