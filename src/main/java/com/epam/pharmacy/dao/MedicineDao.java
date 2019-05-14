package com.epam.pharmacy.dao;

import com.epam.pharmacy.entity.Medicine;
import com.epam.pharmacy.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface MedicineDao extends Dao<Medicine> {
    Optional<Medicine> findMedicineByName(String name) throws DaoException;
    Optional<Medicine> findMedicineByNameAndDosage(String name, String dosage) throws DaoException;
    List<Medicine> findMedicineWithPrescription() throws DaoException;
}
