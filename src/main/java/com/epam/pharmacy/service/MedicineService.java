package com.epam.pharmacy.service;

import com.epam.pharmacy.dao.MedicineDao;
import com.epam.pharmacy.dao.factory.DaoFactory;
import com.epam.pharmacy.entity.Medicine;
import com.epam.pharmacy.exception.DaoException;
import com.epam.pharmacy.exception.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class MedicineService {
    private static final Logger LOGGER = LogManager.getLogger(MedicineService.class);
    private DaoFactory factory;

    public MedicineService(DaoFactory factory){
        this.factory = factory;
    }

    public boolean create(Medicine medicine) throws ServiceException {
        try {
            MedicineDao medicineDao = factory.getMedicineDao();
            if(medicine.getId()==null){
                String name = medicine.getName();
                String dosage = medicine.getDosage();
                Optional<Medicine> optionalMedicine = medicineDao.findMedicineByNameAndDosage(name, dosage);
                if(optionalMedicine.isPresent()){
                    LOGGER.info("Medicine with name " + name + " and dosage " + dosage + " already exists. Creation will be skipped");
                    return false;
                }
            }
            medicineDao.create(medicine);
            return true;
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Medicine> findAll() throws ServiceException {
        try {
            MedicineDao medicineDao = factory.getMedicineDao();
            return medicineDao.findAll();
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<Medicine> findById(int id) throws ServiceException {
        try {
            MedicineDao medicineDao = factory.getMedicineDao();
            return medicineDao.findById(id);
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Medicine> findMedicineWithPrescription() throws ServiceException {
        try {
            MedicineDao medicineDao = factory.getMedicineDao();
            return medicineDao.findMedicineWithPrescription();
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }
}
