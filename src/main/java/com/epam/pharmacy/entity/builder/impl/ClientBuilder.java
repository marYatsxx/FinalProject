package com.epam.pharmacy.entity.builder.impl;

import com.epam.pharmacy.entity.builder.Builder;
import com.epam.pharmacy.entity.ClientAccount;
import com.epam.pharmacy.exception.BuilderException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientBuilder implements Builder<ClientAccount> {
    @Override
    public ClientAccount build(ResultSet resultSet) throws SQLException, BuilderException {
        Integer id = resultSet.getInt(ClientAccount.ID);
        double balance = resultSet.getDouble(ClientAccount.BALANCE);
        return new ClientAccount(id, balance);
    }
}
