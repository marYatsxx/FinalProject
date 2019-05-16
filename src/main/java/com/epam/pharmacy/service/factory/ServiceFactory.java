package com.epam.pharmacy.service.factory;

import com.epam.pharmacy.dao.pool.ConnectionPool;
import com.epam.pharmacy.dao.factory.DaoFactory;
import com.epam.pharmacy.exception.ConnectionPoolException;
import com.epam.pharmacy.exception.DaoException;
import com.epam.pharmacy.exception.ServiceException;
import com.epam.pharmacy.service.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class ServiceFactory implements AutoCloseable {
    private static final Logger LOGGER = LogManager.getLogger(ServiceFactory.class);
    private Connection connection;
    DaoFactory factory = new DaoFactory();

    public ServiceFactory(){
        connection = factory.getConnection();
    }

    public UserService getUserService(){
        return new UserService(factory);
    }

    public ClientService getClientService(){
        return new ClientService(factory);
    }

    public MedicineService getMedicineService(){
        return new MedicineService(factory);
    }

    public OrderService getOrderService(){
        return new OrderService(factory);
    }

    public PrescriptionService getPrescriptionService(){
        return new PrescriptionService(factory);
    }

    public Connection getConnection() {
        return connection;
    }

    public void releaseConnection() {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.releaseConnection(connection);
        } catch (ConnectionPoolException e) {
            LOGGER.error("Can not release connection", e);
            e.printStackTrace();
        }
    }

    public void startTransaction() throws ServiceException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void commit()throws ServiceException{
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void rollback() throws ServiceException{
        try{
            connection.rollback();
        } catch (SQLException e){
            throw new ServiceException(e.getMessage());
        }
    }
    @Override
    public void close() {
        ConnectionPool.getInstance().releaseConnection(connection);
    }
}
