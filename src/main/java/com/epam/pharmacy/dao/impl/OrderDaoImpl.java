package com.epam.pharmacy.dao.impl;

import com.epam.pharmacy.entity.builder.Builder;
import com.epam.pharmacy.dao.AbstractDao;
import com.epam.pharmacy.dao.OrderDao;
import com.epam.pharmacy.entity.Order;
import com.epam.pharmacy.exception.DaoException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {
    private static final Logger LOGGER = LogManager.getLogger(OrderDaoImpl.class);

    private static final String UPDATE_ORDER = "UPDATE `order` SET price = ?, date = ?, client_id = ? WHERE order_id = ?;";
    private static final String FIND_ALL_ORDERS = "SELECT * FROM `order`;";
    private static final String CREATE_ORDER = "INSERT INTO `order` VALUES(default, ?, ?, ?);";
    private static final String REMOVE_ORDER_BY_ID = "DELETE FROM `order` WHERE order_id = ?;";
    private static final String FIND_ORDER_BY_ID = "SELECT * FROM `order` WHERE order_id = ?;";
    private static final String FIND_ORDER_BY_CLIENT_ID = "SELECT * FROM `order` WHERE client_id = ?;";

    public OrderDaoImpl(Connection connection, Builder<Order> builder){
        super(connection, builder);
    }

    @Override
    public List<Order> findOrderByClientId(int id) throws DaoException {
        return executeQuery(FIND_ORDER_BY_CLIENT_ID, id);
    }

    @Override
    public Optional<Order> findById(int id) throws DaoException {
        return executeQueryForSingleResult(FIND_ORDER_BY_ID, id);
    }

    @Override
    public List<Order> findAll() throws DaoException {
        return executeQuery(FIND_ALL_ORDERS);
    }

    @Override
    public boolean removeById(int id) throws DaoException {
        if(!isPresent(id)){
            LOGGER.info("Can not remove: Order with id " + id + " is not found");
            return false;
        }
        executeUpdate(REMOVE_ORDER_BY_ID, id);
        LOGGER.info("Order has been removed successfully");
        return true;
    }

    @Override
    public boolean remove(Order item) throws DaoException {
        int id = item.getId();
        removeById(id);
        return true;
    }

    @Override
    public boolean create(Order item) throws DaoException {
        double price = item.getPrice();
        LocalDate date = item.getDate();
        int clientId = item.getClientId();
        executeUpdate(CREATE_ORDER, price, date, clientId);
        LOGGER.info("Order has been created successfully");
        return true;
    }

    @Override
    public boolean update(Order item) throws DaoException {
        int id = item.getId();
        double price = item.getPrice();
        LocalDate date = item.getDate();
        int clientId = item.getClientId();
        executeUpdate(UPDATE_ORDER, price, date, clientId, id);
        LOGGER.info("Order update has been executed successfully");
        return false;
    }
}
