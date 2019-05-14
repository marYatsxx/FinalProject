package com.epam.pharmacy.service;

import com.epam.pharmacy.dao.UserDao;
import com.epam.pharmacy.dao.connectionpool.ConnectionPool;
import com.epam.pharmacy.dao.factory.DaoFactory;
import com.epam.pharmacy.entity.User;
import com.epam.pharmacy.exception.ConnectionPoolException;
import com.epam.pharmacy.exception.DaoException;
import com.epam.pharmacy.exception.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.Optional;

public class UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);
    private Connection connection;
    private DaoFactory factory = new DaoFactory();

    public Optional<User> login(String login, String password) throws ServiceException {
        try {
            UserDao userDao = factory.getUserDao();
            return userDao.findUserByLoginAndPassword(login, password);
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<User> findById(int id) throws ServiceException {
        try {
            UserDao userDao = factory.getUserDao();
            return userDao.findById(id);
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean create(User user) throws ServiceException {
        try {
            UserDao userDao = factory.getUserDao();
            return userDao.create(user);
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<User> findUserByLogin(String login) throws ServiceException {
        try {
            UserDao userDao = factory.getUserDao();
            return userDao.findByLogin(login);
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }
}
