package org.example.services;

import org.example.entities.BaseEntity;

import java.io.Serializable;
import java.util.List;

public interface BaseEntityservice <T extends BaseEntity<ID>, ID extends Serializable> {
    T save(T entity);
    T update(T entity);
    boolean delete(T entity);
    boolean deleteById(ID id);
    T findById(ID id);
    List<T> findAll();

}
