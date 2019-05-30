package com.epam.pharmacy.service;

import com.epam.pharmacy.dao.*;
import com.epam.pharmacy.dao.factory.DaoFactory;
import com.epam.pharmacy.entity.*;
import com.epam.pharmacy.exception.DaoException;
import com.epam.pharmacy.exception.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    public Optional<User> login(String login, String password) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            UserDao userDao = factory.getUserDao();
            return userDao.findUserByLoginAndPassword(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean register(User user) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            factory.startTransaction();
            if (!create(user)) {
                return false;
            }
            UserDao userDao = factory.getUserDao();
            if (user.getUserRoleId() == User.CLIENT_ID) {
                Optional<User> createdUser = userDao.findUserByLogin(user.getLogin());
                user.setId(createdUser.get().getId());
                BigDecimal initialBalance = new BigDecimal(0.0).setScale(2, BigDecimal.ROUND_DOWN);
                ClientAccount clientAccount = new ClientAccount(user.getId(), initialBalance);
                ClientDao clientDao = factory.getClientDao();
                clientDao.create(clientAccount);
            }
            factory.commit();
            return true;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<User> findById(int id) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            UserDao userDao = factory.getUserDao();
            return userDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean create(User user) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            UserDao userDao = factory.getUserDao();
            Optional<Integer> id = Optional.ofNullable(user.getId());
            String login = user.getLogin();
            if (id.isPresent()) {
                if (checkLoginForUpdate(user)) {
                    LOGGER.info("User with login " + login + " already exists.Creation will be skipped.");
                    return false;
                }
            } else {
                Optional<User> optionalUser = findUserByLogin(login);
                if (optionalUser.isPresent()) {
                    LOGGER.info("User with login " + login + " already exists. Creation will be skipped");
                    return false;
                }
            }
            userDao.create(user);
            return true;
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean removeById(int id) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            factory.startTransaction();
            UserDao userDao = factory.getUserDao();
            PrescriptionDao prescriptionDao = factory.getPrescriptionDao();
            RequestDao requestDao = factory.getRequestDao();
            Optional<User> user = userDao.findById(id);
            switch (user.get().getRole()) {
                case ADMIN:
                case PHARMACIST:
                    break;
                case CLIENT:
                    List<Prescription> prescriptions = prescriptionDao.findPrescriptionByClientId(id);
                    for (Prescription prescription : prescriptions) {
                        List<Request> requests = requestDao.findByPrescriptionId(prescription.getId());
                        for (Request request : requests) {
                            if(!requestDao.remove(request)){
                                factory.rollback();
                                return false;
                            }
                        }
                        prescriptionDao.remove(prescription);
                    }
                    OrderDao orderDao = factory.getOrderDao();
                    List<Order> orders = orderDao.findOrderByClientId(id);
                    for (Order order : orders) {
                        if(!orderDao.remove(order)){
                            factory.rollback();
                            return false;
                        }
                    }
                    break;
                case DOCTOR:
                    List<Prescription> prescriptionByDoctorId = prescriptionDao.findPrescriptionByDoctorId(id);
                    for (Prescription prescription : prescriptionByDoctorId) {
                        List<Request> requests = requestDao.findByPrescriptionId(prescription.getId());
                        for (Request request : requests) {
                            if(!requestDao.remove(request)){
                                factory.rollback();
                                return false;
                            }
                        }
                    }
                    break;
                default:
                    throw new NoSuchElementException("Role not found");
            }
            factory.commit();
            return userDao.removeById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    private boolean checkLoginForUpdate(User user) throws ServiceException {
        //check if updated login already exists in database
        try (DaoFactory factory = new DaoFactory()) {
            UserDao userDao = factory.getUserDao();
            Optional<Integer> id = Optional.ofNullable(user.getId());
            String login = user.getLogin();
            String thisUserLogin = userDao.findById(id.get()).get().getLogin();
            Optional<User> otherUser = findUserByLogin(login);
            if (otherUser.isPresent()) {
                String otherUserLogin = otherUser.get().getLogin();
                return login.equals(otherUserLogin) && !login.equals(thisUserLogin);
            }
            return false;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<User> findUserByLogin(String login) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            UserDao userDao = factory.getUserDao();
            return userDao.findUserByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<User> findAllClients() throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            UserDao userDao = factory.getUserDao();
            return userDao.findAllClients();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<User> findAllDoctors() throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            UserDao userDao = factory.getUserDao();
            return userDao.findAllDoctors();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<User> findAllPharmacists() throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            UserDao userDao = factory.getUserDao();
            return userDao.findAllPharmacists();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void changeBlockingStatus(boolean status, int id) throws ServiceException {
        try (DaoFactory factory = new DaoFactory()) {
            UserDao userDao = factory.getUserDao();
            userDao.changeBlockingStatus(status, id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
