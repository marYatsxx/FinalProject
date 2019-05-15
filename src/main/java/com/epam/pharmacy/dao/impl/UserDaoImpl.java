package com.epam.pharmacy.dao.impl;

import com.epam.pharmacy.entity.builder.Builder;
import com.epam.pharmacy.dao.AbstractDao;
import com.epam.pharmacy.dao.UserDao;
import com.epam.pharmacy.entity.User;
import com.epam.pharmacy.exception.DaoException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

    private static final String FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM user WHERE login = ? AND password = ?;";
    private static final String FIND_USER_BY_ID = "SELECT * FROM user WHERE user_id = ?;";
    private static final String FIND_ALL_USERS = "SELECT * FROM user;";
    private static final String REMOVE_USER_BY_ID = "DELETE FROM user WHERE user_id = ?;";
    private static final String CREATE_USER = "INSERT INTO user VALUES(default, ?, ?, ?, ?, ?);";
    private static final String UPDATE_USER_INFO = "UPDATE user SET login = ?, password = ?, name = ?, surname = ? WHERE user_id = ?;";
    private static final String FIND_USER_BY_LOGIN= "SELECT * FROM user WHERE login = ?;";


    public UserDaoImpl(Connection connection, Builder<User> builder) {
        super(connection, builder);
    }


    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        return executeQueryForSingleResult(FIND_USER_BY_LOGIN_AND_PASSWORD, login, password);
    }

    @Override
    public Optional<User> findById(int id) throws DaoException {
        return executeQueryForSingleResult(FIND_USER_BY_ID, id);
    }

    @Override
    public Optional<User> findByLogin(String login) throws DaoException {
        return executeQueryForSingleResult(FIND_USER_BY_LOGIN, login);
    }

    @Override
    public List<User> findAll() throws DaoException {
        return executeQuery(FIND_ALL_USERS);
    }

    @Override
    public boolean removeById(int id) throws DaoException {
        if(!isPresent(id)){
            LOGGER.info("Can not remove: User with id " + id + " is not found");
            return false;
        }
        executeUpdate(REMOVE_USER_BY_ID, id);
        LOGGER.info("User has been removed successfully");
        return true;
    }

    @Override
    public boolean remove(User user) throws DaoException {
        int id = user.getId();
        removeById(id);
        LOGGER.info("User has been removed successfully");
        return true;
    }

    @Override
    public boolean create(User user) throws DaoException {
        Optional<Integer> id = Optional.ofNullable(user.getId());
        String login = user.getLogin();
        String password = user.getPassword();
        String name = user.getName();
        String surname = user.getSurname();
        int user_role_id = user.getUserRoleId();
        if(id.isPresent()){
            executeUpdate(UPDATE_USER_INFO, login, password, name, surname, id.get());
            LOGGER.info("Update has been finished successfully");
        } else {
            executeUpdate(CREATE_USER, login, password, name, surname, user_role_id);
            LOGGER.info("New user has been created successfully");
        }
        return true;
    }
}
