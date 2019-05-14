package com.epam.pharmacy.service;

import com.epam.pharmacy.dao.ClientDao;
import com.epam.pharmacy.dao.connectionpool.ConnectionPool;
import com.epam.pharmacy.dao.factory.DaoFactory;
import com.epam.pharmacy.entity.ClientAccount;
import com.epam.pharmacy.exception.ConnectionPoolException;
import com.epam.pharmacy.exception.DaoException;
import com.epam.pharmacy.exception.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.Optional;

public class ClientService {
    private static final Logger LOGGER = LogManager.getLogger(ClientService.class);
    private Connection connection;

    public Optional<ClientAccount> findById(int id) throws ServiceException {
        try {
            DaoFactory factory = new DaoFactory();
            ClientDao clientDao = factory.getClientDao();
            return clientDao.findById(id);
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean create(ClientAccount account) throws ServiceException {
        try {
            DaoFactory factory = new DaoFactory();
            ClientDao clientDao = factory.getClientDao();
            return clientDao.create(account);
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }
}
