package ru.chernov.tennisscoreboard.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    Optional<T> findById(Long id);
    List<T> findAll();
    void delete(T t);
    T save(T t);
    void update(T t);
}
