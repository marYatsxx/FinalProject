package com.epam.pharmacy.service;

import com.epam.pharmacy.dao.PrescriptionDao;
import com.epam.pharmacy.dao.factory.DaoFactory;
import com.epam.pharmacy.entity.Prescription;
import com.epam.pharmacy.exception.DaoException;
import com.epam.pharmacy.exception.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class PrescriptionService {
    private static final Logger LOGGER = LogManager.getLogger(PrescriptionService.class);

    public boolean create(Prescription prescription) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            PrescriptionDao prescriptionDao = factory.getPrescriptionDao();
            prescriptionDao.create(prescription);
            return true;
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<Prescription> findById(int id) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            PrescriptionDao prescriptionDao = factory.getPrescriptionDao();
            return prescriptionDao.findById(id);
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Prescription> findPrescriptionByClientId(int id) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            PrescriptionDao prescriptionDao = factory.getPrescriptionDao();
            return prescriptionDao.findPrescriptionByClientId(id);
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }


    public Optional<Prescription> findPrescriptionByClientAndMedicine(int clientId, int medicineId) throws ServiceException{
        try (DaoFactory factory = new DaoFactory()) {
            PrescriptionDao prescriptionDao = factory.getPrescriptionDao();
            return prescriptionDao.findPrescriptionByClientAndMedicine(clientId, medicineId);
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Prescription> findPrescriptionByDoctorId(int id) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            PrescriptionDao prescriptionDao = factory.getPrescriptionDao();
            return prescriptionDao.findPrescriptionByDoctorId(id);
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }
}
