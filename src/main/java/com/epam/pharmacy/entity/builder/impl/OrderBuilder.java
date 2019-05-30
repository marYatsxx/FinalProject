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
    public Order build(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt(Order.ID);
        Date date = resultSet.getDate(Order.DATE);
        LocalDate orderDate = date.toLocalDate();
        Integer clientId = resultSet.getInt(Order.CLIENT_ID);
        Integer medicineId = resultSet.getInt(Order.MEDICINE_ID);
        boolean paid = resultSet.getBoolean(Order.PAID);
        return new Order(id, orderDate, clientId, medicineId, paid);
    }
}
