package com.epam.pharmacy.service;

import com.epam.pharmacy.dao.ClientDao;
import com.epam.pharmacy.dao.factory.DaoFactory;
import com.epam.pharmacy.entity.ClientAccount;
import com.epam.pharmacy.exception.DaoException;
import com.epam.pharmacy.exception.ServiceException;
import com.epam.pharmacy.util.Validator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.Optional;

public class ClientService {
    private static final Logger LOGGER = LogManager.getLogger(ClientService.class);
    private static final BigDecimal MAX_AMOUNT = new BigDecimal(1000.0).setScale(2, BigDecimal.ROUND_DOWN);

    public boolean rechargeBalance(int id, String balance) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            ClientDao clientDao = factory.getClientDao();
            if(!Validator.checkAmount(balance)){
                return false;
            }
            BigDecimal addedAmount = new BigDecimal(balance).setScale(2, BigDecimal.ROUND_DOWN);
            if(addedAmount.compareTo(MAX_AMOUNT)==1){
                return false;
            }
            ClientAccount clientAccount = clientDao.findById(id).get();
            BigDecimal currentBalance = clientAccount.getBalance();
            BigDecimal result = currentBalance.add(addedAmount);
            clientAccount.setBalance(result);
            return clientDao.update(clientAccount);
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<ClientAccount> findById(int id) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            ClientDao clientDao = factory.getClientDao();
            return clientDao.findById(id);
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean create(ClientAccount account) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
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
        try (DaoFactory factory = new DaoFactory()) {
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
