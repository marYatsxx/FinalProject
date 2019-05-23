package com.epam.pharmacy.entity.builder.impl;

import com.epam.pharmacy.entity.builder.Builder;
import com.epam.pharmacy.entity.User;
import com.epam.pharmacy.entity.UserRole;
import com.epam.pharmacy.exception.BuilderException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder implements Builder<User> {
    @Override
    public User build(ResultSet resultSet) throws SQLException, BuilderException {
        Integer id = resultSet.getInt(User.ID);
        String name = resultSet.getString(User.NAME);
        String surname = resultSet.getString(User.SURNAME);
        String login = resultSet.getString(User.LOGIN);
        String password = resultSet.getString(User.PASSWORD);
        int userRoleId = resultSet.getInt(User.USER_ROLE_ID);
        User user;
        switch (userRoleId) {
            case 1:
                user = new User(id, login, password, name, surname, UserRole.ADMIN);
                break;
            case 2:
                user = new User(id, login, password, name, surname, UserRole.DOCTOR);
                break;
            case 3:
                user = new User(id, login, password, name, surname, UserRole.CLIENT);
                break;
            case 4:
                user = new User(id, login, password, name, surname, UserRole.PHARMACIST);
                break;
            default:
                throw new BuilderException();
        }
        return user;
    }
}
