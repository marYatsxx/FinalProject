package com.epam.pharmacy.service;

import com.epam.pharmacy.dao.RequestDao;
import com.epam.pharmacy.dao.factory.DaoFactory;
import com.epam.pharmacy.entity.Request;
import com.epam.pharmacy.exception.DaoException;
import com.epam.pharmacy.exception.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class RequestService {
    private static final Logger LOGGER = LogManager.getLogger(RequestService.class);
    private DaoFactory factory;

    public RequestService(DaoFactory factory){
        this.factory = factory;
    }

    public boolean create(Request request) throws ServiceException {
        try {
            RequestDao requestDao = factory.getRequestDao();
            requestDao.create(request);
            return true;
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Request> findAll() throws ServiceException {
        try {
            RequestDao requestDao = factory.getRequestDao();
            return requestDao.findAll();
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<Request> findById(int id) throws ServiceException {
        try {
            RequestDao requestDao = factory.getRequestDao();
            return requestDao.findById(id);
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Request> findConsideredRequests() throws ServiceException {
        try {
            RequestDao requestDao = factory.getRequestDao();
            return requestDao.findConsideredRequests();
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }
}
