package com.epam.pharmacy.dao;

import com.epam.pharmacy.entity.User;
import com.epam.pharmacy.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao extends Dao<User> {
    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;
    Optional<User> findUserByLogin(String login) throws DaoException;
    List<User> findAllClients() throws DaoException;
    List<User> findAllDoctors() throws DaoException;
    List<User> findAllPharmacists() throws DaoException;
    List<User> findAllUnblockedUsers() throws DaoException;
    void changeBlockingStatus(boolean status, int id) throws DaoException;
}
