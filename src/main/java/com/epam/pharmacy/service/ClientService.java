package com.epam.pharmacy.service;

import com.epam.pharmacy.dao.ClientDao;
import com.epam.pharmacy.dao.factory.DaoFactory;
import com.epam.pharmacy.entity.ClientAccount;
import com.epam.pharmacy.exception.DaoException;
import com.epam.pharmacy.exception.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Optional;

public class ClientService {
    private static final Logger LOGGER = LogManager.getLogger(ClientService.class);
    private DaoFactory factory;

    public ClientService(DaoFactory factory){
        this.factory = factory;
    }

    public Optional<ClientAccount> findById(int id) throws ServiceException {
        try {
            ClientDao clientDao = factory.getClientDao();
            return clientDao.findById(id);
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean create(ClientAccount account) throws ServiceException {
        try {
            ClientDao clientDao = factory.getClientDao();
            return clientDao.create(account);
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }
}
