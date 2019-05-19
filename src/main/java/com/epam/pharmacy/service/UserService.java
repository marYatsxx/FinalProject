package com.epam.pharmacy.service;

import com.epam.pharmacy.dao.UserDao;
import com.epam.pharmacy.dao.factory.DaoFactory;
import com.epam.pharmacy.entity.User;
import com.epam.pharmacy.exception.DaoException;
import com.epam.pharmacy.exception.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Optional;

public class UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);
    private DaoFactory factory;

    public UserService(DaoFactory factory){
        this.factory = factory;
    }

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
            Optional<Integer> id = Optional.ofNullable(user.getId());
            String login = user.getLogin();
            if(id.isPresent()){
                if(checkLoginForUpdate(user)){
                    LOGGER.info("User with login " + login + " already exists.Creation will be skipped.");
                    return false;
                }
            } else {
                Optional<User> optionalUser = findUserByLogin(login);
                if(optionalUser.isPresent()) {
                    LOGGER.info("User with login " + login + " already exists. Creation will be skipped");
                    return false;
                }
            }
            userDao.create(user);
            return true;
        } catch (DaoException e){
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    private boolean checkLoginForUpdate(User user) throws ServiceException {
        //check if updated login already exists in database
        try {
            UserDao userDao = factory.getUserDao();
            Optional<Integer> id = Optional.ofNullable(user.getId());
            String login = user.getLogin();
            String thisUserLogin = userDao.findById(id.get()).get().getLogin();
            Optional<User> otherUser = findUserByLogin(login);
            if(otherUser.isPresent()){
                String otherUserLogin = otherUser.get().getLogin();
                return login.equals(otherUserLogin) && !login.equals(thisUserLogin);
            }
            return false;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<User> findUserByLogin(String login) throws ServiceException {
        try {
            UserDao userDao = factory.getUserDao();
            return userDao.findUserByLogin(login);
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }
}
