package com.epam.pharmacy.dao.impl;

import com.epam.pharmacy.entity.builder.Builder;
import com.epam.pharmacy.dao.AbstractDao;
import com.epam.pharmacy.dao.PrescriptionDao;
import com.epam.pharmacy.entity.Prescription;
import com.epam.pharmacy.exception.DaoException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class PrescriptionDaoImpl extends AbstractDao<Prescription> implements PrescriptionDao {
    private static final Logger LOGGER = LogManager.getLogger(PrescriptionDaoImpl.class);

    private static final String UPDATE_PRESCRIPTION = "UPDATE prescription SET client_id = ?, validity = ?, " +
            "medicine_id = ?, doctor_id = ? WHERE prescription_id = ?;";
    private static final String FIND_ALL_PRESCRIPTIONS = "SELECT * FROM prescription;";
    private static final String CREATE_PRESCRIPTION = "INSERT INTO prescription VALUES(default, ?, ?, ?, ?);";
    private static final String REMOVE_PRESCRIPTION_BY_ID = "DELETE FROM prescription WHERE prescription_id = ?;";
    private static final String FIND_PRESCRIPTION_BY_ID = "SELECT * FROM prescription WHERE prescription_id = ?;";
    private static final String FIND_PRESCRIPTION_BY_CLIENT_ID = "SELECT * FROM prescription WHERE client_id = ?;";
    private static final String FIND_PRESCRIPTION_BY_DOCTOR_ID = "SELECT * FROM prescription WHERE doctor_id = ?;";

    private static final String FIND_VALID_PRESCRIPTIONS = "SELECT * FROM prescription WHERE validity>CURRENT_DATE;;";
    private static final String FIND_INVALID_PRESCRIPTIONS = "SELECT * FROM prescription WHERE validity<CURRENT_DATE;;";

    public PrescriptionDaoImpl(Connection connection, Builder<Prescription> builder) {
        super(connection, builder);
    }

    @Override
    public List<Prescription> findPrescriptionByClientId(int id) throws DaoException {
        return executeQuery(FIND_PRESCRIPTION_BY_CLIENT_ID, id);
    }

    @Override
    public List<Prescription> findPrescriptionByDoctorId(int id) throws DaoException {
        return executeQuery(FIND_PRESCRIPTION_BY_DOCTOR_ID, id);
    }

    @Override
    public List<Prescription> findValidPrescriptions() throws DaoException {
        return executeQuery(FIND_VALID_PRESCRIPTIONS);
    }

    @Override
    public List<Prescription> findInvalidPrescriptions() throws DaoException {
        return executeQuery(FIND_INVALID_PRESCRIPTIONS);
    }

    @Override
    public Optional<Prescription> findById(int id) throws DaoException {
        return executeQueryForSingleResult(FIND_PRESCRIPTION_BY_ID, id);
    }

    @Override
    public List<Prescription> findAll() throws DaoException {
        return executeQuery(FIND_ALL_PRESCRIPTIONS);
    }

    @Override
    public boolean removeById(int id) throws DaoException {
        if(!isPresent(id)){
            LOGGER.info("Can not remove: Prescription with id " + id + " is not found");
            return false;
        }
        executeUpdate(REMOVE_PRESCRIPTION_BY_ID, id);
        LOGGER.info("Prescription has been removed successfully");
        return true;
    }

    @Override
    public boolean remove(Prescription item) throws DaoException {
        int id = item.getId();
        removeById(id);
        return true;
    }

    @Override
    public boolean create(Prescription item) throws DaoException {
        int clientId = item.getClientId();
        int doctorId = item.getDoctorId();
        LocalDate validity = item.getValidity();
        int medicineId = item.getMedicineId();
        executeUpdate(CREATE_PRESCRIPTION, clientId, validity, medicineId, doctorId);
        LOGGER.info("Prescription has been created successfully");
        return true;
    }

    @Override
    public boolean update(Prescription item) throws DaoException {
        int id = item.getId();
        int clientId = item.getClientId();
        LocalDate validity = item.getValidity();
        int medicineId = item.getMedicineId();
        int doctorId = item.getDoctorId();
        executeUpdate(UPDATE_PRESCRIPTION, clientId, validity, medicineId, doctorId, id);
        LOGGER.info("Update has been executed successfully");
        return true;
    }
}
