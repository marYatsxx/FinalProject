package com.epam.pharmacy.dao;

import com.epam.pharmacy.entity.User;
import com.epam.pharmacy.exception.DaoException;

import java.util.Optional;

public interface UserDao extends Dao<User> {
    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;
    Optional<User> findByLogin(String login) throws DaoException;
}