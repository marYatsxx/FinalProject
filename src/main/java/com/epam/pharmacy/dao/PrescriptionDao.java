package com.epam.pharmacy.dao;

import com.epam.pharmacy.entity.Prescription;
import com.epam.pharmacy.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface PrescriptionDao extends Dao<Prescription> {
    List<Prescription> findPrescriptionByClientId(int id) throws DaoException;
    List<Prescription> findPrescriptionByDoctorId(int id) throws DaoException;
    List<Prescription> findValidPrescriptions() throws DaoException;
    List<Prescription> findInvalidPrescriptions() throws DaoException;
}
