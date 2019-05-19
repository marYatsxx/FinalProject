package com.epam.pharmacy.service;

import com.epam.pharmacy.dao.PrescriptionDao;
import com.epam.pharmacy.dao.factory.DaoFactory;
import com.epam.pharmacy.entity.Prescription;
import com.epam.pharmacy.exception.DaoException;
import com.epam.pharmacy.exception.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.Optional;

public class PrescriptionService {
    private static final Logger LOGGER = LogManager.getLogger(PrescriptionService.class);
    private DaoFactory factory;

    public PrescriptionService(DaoFactory factory){
        this.factory = factory;
    }

    public boolean create(Prescription prescription) throws ServiceException {
        try {
            PrescriptionDao prescriptionDao = factory.getPrescriptionDao();
            prescriptionDao.create(prescription);
            return true;
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<Prescription> findPrescriptionByClientAndMedicine(int clientId, int medicineId) throws ServiceException{
        try{
            PrescriptionDao prescriptionDao = factory.getPrescriptionDao();
            return prescriptionDao.findPrescriptionByClientAndMedicine(clientId, medicineId);
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }
}
