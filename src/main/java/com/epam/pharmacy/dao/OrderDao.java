package com.epam.pharmacy.dao;

import com.epam.pharmacy.entity.Order;
import com.epam.pharmacy.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface OrderDao extends Dao<Order> {
    List<Order> findOrderByClientId(int id) throws DaoException;
    List<Order> findNotPaidOrderByClientId(int id) throws DaoException;
    List<Order> findAllNotPaidOrders() throws DaoException;
    List<Order> findOrdersByMedicineId(int id) throws DaoException;
    void updateOrderStatusById(boolean paid, int id) throws DaoException;
}
