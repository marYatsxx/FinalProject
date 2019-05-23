package com.epam.pharmacy.service;

import com.epam.pharmacy.dao.OrderDao;
import com.epam.pharmacy.dao.factory.DaoFactory;
import com.epam.pharmacy.entity.Order;
import com.epam.pharmacy.exception.DaoException;
import com.epam.pharmacy.exception.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class OrderService {
    private static final Logger LOGGER = LogManager.getLogger(OrderService.class);
    private DaoFactory factory;

    public OrderService(DaoFactory factory){
        this.factory = factory;
    }

    public boolean create(Order order) throws ServiceException {
        try {
            OrderDao orderDao = factory.getOrderDao();
            orderDao.create(order);
            return true;
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<Order> findById(int id) throws ServiceException {
        try{
            OrderDao orderDao = factory.getOrderDao();
            return orderDao.findById(id);
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Order> findOrderByClientId(int id) throws ServiceException {
        try{
            OrderDao orderDao = factory.getOrderDao();
            return orderDao.findOrderByClientId(id);
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<Order> findNotPaidOrderByClientId(int id) throws ServiceException{
        try{
            OrderDao orderDao = factory.getOrderDao();
            return orderDao.findNotPaidOrderByClientId(id);
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean updateOrderStatusById(boolean paid, int id) throws ServiceException{
        try{
            OrderDao orderDao = factory.getOrderDao();
            Optional<Order> order = findById(id);
            boolean status = order.get().isPaid();
            if(status==paid){
                return false;
            } else {
                orderDao.updateOrderStatusById(paid, id);
                return true;
            }
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }
}
