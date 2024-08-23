package org.example.repositories.baseentity;

import org.example.entities.BaseEntity;

import java.io.Serializable;
import java.util.List;

public interface BaseEntityRepo <T extends BaseEntity<ID>, ID extends Serializable> {
    T save(T entity);
    T update(T entity);
    boolean deleteById(ID id);
    boolean delete(T entity);
    T findById(ID id);
    List<T> findAll();


}
