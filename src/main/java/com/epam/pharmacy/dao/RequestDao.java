package com.epam.pharmacy.dao;

import com.epam.pharmacy.entity.Request;
import com.epam.pharmacy.exception.DaoException;

import java.util.List;
public interface RequestDao extends Dao<Request> {
    List<Request> findRejectedRequests() throws DaoException;
    List<Request> findAcceptedRequests() throws DaoException;
    List<Request> findConsideredRequests() throws DaoException;
    List<Request> findByPrescriptionId(int id) throws DaoException;
}
