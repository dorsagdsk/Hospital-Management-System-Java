package com.hospital.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T extends Identifiable> {

   void save(T entity);
   Optional<T> findById(int id);
   List<T> findAll();
   void deleteById(int id);
   boolean   existsById(int id);
   void update(T entity);
}
