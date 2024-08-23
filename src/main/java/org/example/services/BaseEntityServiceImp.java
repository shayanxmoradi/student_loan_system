package org.example.services;

import org.example.entities.BaseEntity;
import org.example.repositories.baseentity.BaseEntityRepo;
import org.yaml.snakeyaml.events.Event;

import java.io.Serializable;
import java.util.List;

public class BaseEntityServiceImp<T extends BaseEntity<ID>, ID extends Serializable, R extends BaseEntityRepo<T, ID>> implements BaseEntityRepo<T, ID> {
    private final R baseRepository;

    public BaseEntityServiceImp(R baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    public T save(T entity) {
        return baseRepository.save(entity);
    }

    @Override
    public T update(T entity) {
        return baseRepository.update(entity);
    }

    @Override
    public boolean delete(T entity) {
        return baseRepository.delete(entity);
    }

    @Override
    public boolean deleteById(ID id) {
        return baseRepository.deleteById(id);
    }

    @Override
    public T findById(ID id) {
        return baseRepository.findById(id);
    }

    @Override
    public List<T> findAll() {
        return baseRepository.findAll();
    }

}
