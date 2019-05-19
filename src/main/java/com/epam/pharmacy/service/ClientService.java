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
            Optional<Integer> id = Optional.ofNullable(account.getId());
            if(id.isPresent()){
                clientDao.create(account);
                return true;
            } else {
                LOGGER.info("User not found. Can not create client account without user account.");
                return false;
            }

        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean update(ClientAccount account) throws ServiceException {
        try {
            ClientDao clientDao = factory.getClientDao();
            Optional<Integer> id = Optional.ofNullable(account.getId());
            if(id.isPresent()){
                clientDao.update(account);
                return true;
            } else {
                LOGGER.info("Client account not found.");
                return false;
            }
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }
}
