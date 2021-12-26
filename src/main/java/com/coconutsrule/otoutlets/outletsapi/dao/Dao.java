package com.coconutsrule.otoutlets.outletsapi.dao;

import java.util.List;

/**
 * Data Access Object for basic CRUD operations
 */
public interface Dao<T, I> {
    T create(T entity);
    T update(T entity);
    T find(T entity);
    T findById(I id);
    List<T> findAll();
    void delete(T entity);
    void deleteById(I id);
}
