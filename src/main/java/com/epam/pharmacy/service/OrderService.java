package com.epam.pharmacy.service;

import com.epam.pharmacy.dao.MedicineDao;
import com.epam.pharmacy.dao.OrderDao;
import com.epam.pharmacy.dao.factory.DaoFactory;
import com.epam.pharmacy.entity.Medicine;
import com.epam.pharmacy.entity.Order;
import com.epam.pharmacy.exception.DaoException;
import com.epam.pharmacy.exception.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class OrderService {
    private static final Logger LOGGER = LogManager.getLogger(OrderService.class);

    private static final String FAILURE = "failure";

    public String payForOrders(String[] chosen, BigDecimal balance) throws ServiceException{
        try (DaoFactory factory = new DaoFactory()) {
            factory.startTransaction();
            OrderDao orderDao = factory.getOrderDao();
            MedicineDao medicineDao = factory.getMedicineDao();
            for (String strId : chosen) {
                int orderId = Integer.parseInt(strId);
                Order order = orderDao.findById(orderId).get();
                int medicine_id = order.getMedicineId();
                Medicine medicine = medicineDao.findById(medicine_id).get();
                BigDecimal price = medicine.getPrice();
                if (balance.compareTo(price) == 1) {
                    balance = balance.subtract(price);
                    orderDao.updateOrderStatusById(true, order.getId());
                } else {
                    factory.rollback();
                    return FAILURE;
                }
            }
            factory.commit();
            return balance.toString();
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean create(Order order) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            OrderDao orderDao = factory.getOrderDao();
            orderDao.create(order);
            return true;
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<Order> findById(int id) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            OrderDao orderDao = factory.getOrderDao();
            return orderDao.findById(id);
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Order> findOrderByClientId(int id) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            OrderDao orderDao = factory.getOrderDao();
            return orderDao.findOrderByClientId(id);
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Order> findNotPaidOrderByClientId(int id) throws ServiceException{
        try (DaoFactory factory = new DaoFactory()) {
            OrderDao orderDao = factory.getOrderDao();
            return orderDao.findNotPaidOrderByClientId(id);
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Order> findAllNotPaidOrders() throws ServiceException{
        try (DaoFactory factory = new DaoFactory()) {
            OrderDao orderDao = factory.getOrderDao();
            return orderDao.findAllNotPaidOrders();
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean updateOrderStatusById(boolean paid, int id) throws ServiceException{
        try (DaoFactory factory = new DaoFactory()) {
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

    public List<Order> findOrdersByMedicineId(int id) throws ServiceException{
        try (DaoFactory factory = new DaoFactory()) {
            OrderDao orderDao = factory.getOrderDao();
            return orderDao.findOrdersByMedicineId(id);
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }
}
