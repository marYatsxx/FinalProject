package com.epam.pharmacy.dao.impl;

import com.epam.pharmacy.entity.builder.Builder;
import com.epam.pharmacy.dao.AbstractDao;
import com.epam.pharmacy.dao.MedicineDao;
import com.epam.pharmacy.entity.Medicine;
import com.epam.pharmacy.exception.DaoException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class MedicineDaoImpl extends AbstractDao<Medicine> implements MedicineDao {
    private static final Logger LOGGER = LogManager.getLogger(MedicineDaoImpl.class);

    private static final String UPDATE_MEDICINE = "UPDATE medicine SET name = ?, dosage = ?, price = ?, " +
            "needs_prescription = ? WHERE medicine_id = ?;";
    private static final String FIND_ALL_MEDICINE = "SELECT * FROM medicine;";
    private static final String CREATE_MEDICINE = "INSERT INTO medicine VALUES(default, ?, ?, ?, ?);";
    private static final String REMOVE_MEDICINE_BY_ID = "DELETE FROM medicine WHERE medicine_id = ?;";
    private static final String FIND_MEDICINE_BY_ID = "SELECT * FROM medicine WHERE medicine_id = ?;";
    private static final String FIND_MEDICINE_BY_NAME = "SELECT * FROM medicine WHERE name = ?;";
    private static final String FIND_MEDICINE_WITH_PRESCRIPTION = "SELECT * FROM medicine WHERE needs_prescription=true;";
    private static final String FIND_MEDICINE_BY_NAME_AND_DOSAGE = "SELECT * FROM medicine WHERE name = ? AND dosage = ?;";

    public MedicineDaoImpl(Connection connection, Builder<Medicine> builder) {
        super(connection, builder);
    }

    @Override
    public Optional<Medicine> findMedicineByName(String name) throws DaoException {
        return executeQueryForSingleResult(FIND_MEDICINE_BY_NAME, name);
    }

    @Override
    public Optional<Medicine> findMedicineByNameAndDosage(String name, String dosage) throws DaoException {
        return executeQueryForSingleResult(FIND_MEDICINE_BY_NAME_AND_DOSAGE, name, dosage);
    }

    @Override
    public List<Medicine> findMedicineWithPrescription() throws DaoException {
        return executeQuery(FIND_MEDICINE_WITH_PRESCRIPTION);
    }

    @Override
    public Optional<Medicine> findById(int id) throws DaoException {
        return executeQueryForSingleResult(FIND_MEDICINE_BY_ID, id);
    }

    @Override
    public List<Medicine> findAll() throws DaoException {
        return executeQuery(FIND_ALL_MEDICINE);
    }

    @Override
    public boolean removeById(int id) throws DaoException {
        if(!isPresent(id)){
            LOGGER.info("Can not remove: Medicine with id " + id + " is not found");
            return false;
        }
        executeUpdate(REMOVE_MEDICINE_BY_ID, id);
        LOGGER.info("Medicine has been removed successfully");
        return true;
    }

    @Override
    public boolean remove(Medicine item) throws DaoException {
        int id = item.getId();
        removeById(id);
        return true;
    }

    @Override
    public boolean create(Medicine item) throws DaoException {
        Optional<Integer> id = Optional.ofNullable(item.getId());
        String name = item.getName();
        String dosage = item.getDosage();
        double price = item.getPrice();
        boolean needs_prescription = item.isNeedsPrescription();
        if(id.isPresent()){
            executeUpdate(UPDATE_MEDICINE, name, dosage, price, needs_prescription, id.get());
            LOGGER.info("Update has been executed successfully");
        } else {
            executeUpdate(CREATE_MEDICINE, name, dosage, price, needs_prescription);
            LOGGER.info("Medicine has been created successfully");
        }
        return true;
    }
}
