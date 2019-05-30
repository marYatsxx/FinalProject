package com.epam.pharmacy.entity.builder.impl;

import com.epam.pharmacy.entity.builder.Builder;
import com.epam.pharmacy.entity.User;
import com.epam.pharmacy.entity.UserRole;
import com.epam.pharmacy.exception.BuilderException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

public class UserBuilder implements Builder<User> {
    @Override
    public User build(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt(User.ID);
        String name = resultSet.getString(User.NAME);
        String surname = resultSet.getString(User.SURNAME);
        String login = resultSet.getString(User.LOGIN);
        String password = resultSet.getString(User.PASSWORD);
        int userRoleId = resultSet.getInt(User.USER_ROLE_ID);
        boolean blocked = resultSet.getBoolean(User.BLOCKED);
        User user;
        switch (userRoleId) {
            case 1:
                user = new User(id, login, password, name, surname, UserRole.ADMIN, blocked);
                break;
            case 2:
                user = new User(id, login, password, name, surname, UserRole.DOCTOR, blocked);
                break;
            case 3:
                user = new User(id, login, password, name, surname, UserRole.CLIENT, blocked);
                break;
            case 4:
                user = new User(id, login, password, name, surname, UserRole.PHARMACIST, blocked);
                break;
            default:
                throw new NoSuchElementException("Role not found");
        }
        return user;
    }
}
