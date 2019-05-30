package com.epam.pharmacy.service;

import com.epam.pharmacy.dao.PrescriptionDao;
import com.epam.pharmacy.dao.RequestDao;
import com.epam.pharmacy.dao.factory.DaoFactory;
import com.epam.pharmacy.entity.Prescription;
import com.epam.pharmacy.entity.Request;
import com.epam.pharmacy.entity.RequestStatus;
import com.epam.pharmacy.exception.DaoException;
import com.epam.pharmacy.exception.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class RequestService {
    private static final Logger LOGGER = LogManager.getLogger(RequestService.class);
    private static final String ACCEPT = "accept";

    public boolean create(Request request) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            RequestDao requestDao = factory.getRequestDao();
            requestDao.create(request);
            return true;
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Request> findAll() throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            RequestDao requestDao = factory.getRequestDao();
            return requestDao.findAll();
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<Request> findById(int id) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            RequestDao requestDao = factory.getRequestDao();
            return requestDao.findById(id);
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Request> findConsideredRequests() throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            RequestDao requestDao = factory.getRequestDao();
            return requestDao.findConsideredRequests();
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean processRequest(int requestId, String decision) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()){
            RequestDao requestDao = factory.getRequestDao();
            Request renewRequest = requestDao.findById(requestId).get();
            if (ACCEPT.equals(decision.toLowerCase())) {
                int prescriptionId = renewRequest.getPrescriptionId();
                PrescriptionDao prescriptionDao = factory.getPrescriptionDao();
                Prescription prescription = prescriptionDao.findById(prescriptionId).get();
                LocalDate newValidity = LocalDate.now().plusMonths(2);
                prescription.setValidity(newValidity);
                prescriptionDao.create(prescription);
                renewRequest.setStatus(RequestStatus.ACCEPTED);
            } else {
                renewRequest.setStatus(RequestStatus.REJECTED);
            }
            return create(renewRequest);
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }
}
