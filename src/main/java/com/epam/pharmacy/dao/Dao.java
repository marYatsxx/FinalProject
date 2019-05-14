package com.epam.pharmacy.dao;

import com.epam.pharmacy.entity.Identifiable;
import com.epam.pharmacy.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface Dao<T extends Identifiable> {
    Optional<T> findById(int id) throws DaoException;
    List<T> findAll() throws DaoException ;
    boolean removeById(int id) throws DaoException ;
    boolean remove(T item) throws DaoException ;
    boolean create(T item) throws DaoException ;
    boolean update(T item) throws DaoException ;
    default boolean isPresent(int id) throws DaoException{
        Optional<T> item = findById(id);
        return item.isPresent();
    }
}
