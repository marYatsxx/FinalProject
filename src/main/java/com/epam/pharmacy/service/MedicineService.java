package com.epam.pharmacy.service;

import com.epam.pharmacy.dao.MedicineDao;
import com.epam.pharmacy.dao.OrderDao;
import com.epam.pharmacy.dao.PrescriptionDao;
import com.epam.pharmacy.dao.RequestDao;
import com.epam.pharmacy.dao.factory.DaoFactory;
import com.epam.pharmacy.entity.Medicine;
import com.epam.pharmacy.entity.Order;
import com.epam.pharmacy.entity.Prescription;
import com.epam.pharmacy.entity.Request;
import com.epam.pharmacy.exception.DaoException;
import com.epam.pharmacy.exception.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class MedicineService {
    private static final Logger LOGGER = LogManager.getLogger(MedicineService.class);

    public boolean create(Medicine medicine) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
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
        try (DaoFactory factory = new DaoFactory()) {
            MedicineDao medicineDao = factory.getMedicineDao();
            return medicineDao.findAll();
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<Medicine> findById(int id) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            MedicineDao medicineDao = factory.getMedicineDao();
            return medicineDao.findById(id);
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Medicine> findMedicineWithPrescription() throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            MedicineDao medicineDao = factory.getMedicineDao();
            return medicineDao.findMedicineWithPrescription();
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean removeById(int id) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            factory.startTransaction();
            MedicineDao medicineDao = factory.getMedicineDao();
            if(!medicineDao.removeById(id)){
                factory.rollback();
                return false;
            }
            PrescriptionDao prescriptionDao = factory.getPrescriptionDao();
            List<Prescription> prescriptions = prescriptionDao.findPrescriptionByMedicineId(id);
            RequestDao requestDao = factory.getRequestDao();
            for(Prescription prescription : prescriptions){
                if(!prescriptionDao.remove(prescription)){
                    factory.rollback();
                    return false;
                }
                List<Request> requests = requestDao.findByPrescriptionId(prescription.getId());
                for(Request request: requests){
                    if(!requestDao.remove(request)){
                        factory.rollback();
                        return false;
                    }
                }
            }
            OrderDao orderDao = factory.getOrderDao();
            List<Order> orders = orderDao.findOrdersByMedicineId(id);
            for(Order order : orders){
                if(!orderDao.remove(order)){
                    factory.rollback();
                    return false;
                }
            }
            factory.commit();
            return true;
        } catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }
}
