package com.epam.pharmacy.entity.builder.impl;

import com.epam.pharmacy.entity.builder.Builder;
import com.epam.pharmacy.entity.ClientAccount;
import com.epam.pharmacy.exception.BuilderException;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientBuilder implements Builder<ClientAccount> {
    @Override
    public ClientAccount build(ResultSet resultSet) throws SQLException, BuilderException {
        Integer id = resultSet.getInt(ClientAccount.ID);
        BigDecimal balance = resultSet.getBigDecimal(ClientAccount.BALANCE).setScale(2, BigDecimal.ROUND_DOWN);
        return new ClientAccount(id, balance);
    }
}
