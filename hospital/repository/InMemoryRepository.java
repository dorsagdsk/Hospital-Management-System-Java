package com.hospital.repository;

import com.hospital.model.Appointment;
import com.hospital.model.Doctor;

import java.util.*;

public class InMemoryRepository<T extends Identifiable> implements Repository<T>{

    private final Map<Integer, T> entityMap = new HashMap<>();
    @Override
    public void save(T entity) {
        entityMap.put(entity.getId(),entity);

        
    }

    @Override
    public Optional<T> findById(int id) {
        return Optional.ofNullable(entityMap.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(entityMap.values());
    }

    @Override
    public void deleteById(int id) {
     entityMap.remove(id);
    }

    @Override
    public boolean existsById(int id) {
        return entityMap.containsKey(id);
    }

    public  void update(T entity){
        if(entityMap.containsKey(entity.getId())){
            entityMap.put(entity.getId(),entity);
        }
    }
}
