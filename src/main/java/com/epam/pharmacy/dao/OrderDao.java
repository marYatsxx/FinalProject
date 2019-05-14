package com.epam.pharmacy.dao;

import com.epam.pharmacy.entity.Order;
import com.epam.pharmacy.exception.DaoException;

import java.util.List;

public interface OrderDao extends Dao<Order> {
    List<Order> findOrderByClientId(int id) throws DaoException;
}
