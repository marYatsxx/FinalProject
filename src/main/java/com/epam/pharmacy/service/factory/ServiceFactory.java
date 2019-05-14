package com.epam.pharmacy.service.factory;

import com.epam.pharmacy.dao.connectionpool.ConnectionPool;
import com.epam.pharmacy.dao.factory.DaoFactory;
import com.epam.pharmacy.exception.ConnectionPoolException;
import com.epam.pharmacy.exception.DaoException;
import com.epam.pharmacy.exception.ServiceException;
import com.epam.pharmacy.service.ClientService;
import com.epam.pharmacy.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class ServiceFactory implements AutoCloseable {
    private static final Logger LOGGER = LogManager.getLogger(ServiceFactory.class);
    private Connection connection;

    public ServiceFactory(){
        DaoFactory factory = new DaoFactory();
        connection = factory.getConnection();
    }

    public UserService getUserService(){
        return new UserService();
    }

    public ClientService getClientService(){
        return new ClientService();
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

    public void commit()throws DaoException{
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    public void rollback() throws DaoException{
        try{
            connection.rollback();
        } catch (SQLException e){
            throw new DaoException(e.getMessage());
        }
    }
    @Override
    public void close() {
        ConnectionPool.getInstance().releaseConnection(connection);
    }
}
