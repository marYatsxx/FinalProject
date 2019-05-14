package com.epam.pharmacy.entity.builder.impl;

import com.epam.pharmacy.entity.builder.Builder;
import com.epam.pharmacy.entity.Order;
import com.epam.pharmacy.exception.BuilderException;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OrderBuilder implements Builder<Order> {
    @Override
    public Order build(ResultSet resultSet) throws SQLException, BuilderException {
        Integer id = resultSet.getInt(Order.ID);
        double price = resultSet.getDouble(Order.PRICE);
        Date date = resultSet.getDate(Order.DATE);
        LocalDate orderDate = date.toLocalDate();
        Integer clientId = resultSet.getInt(Order.CLIENT_ID);
        return new Order(id, price, orderDate, clientId);
    }
}
