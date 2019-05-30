package com.epam.pharmacy.service.factory;

import com.epam.pharmacy.dao.pool.ConnectionPool;
import com.epam.pharmacy.dao.factory.DaoFactory;
import com.epam.pharmacy.exception.ConnectionPoolException;
import com.epam.pharmacy.exception.DaoException;
import com.epam.pharmacy.exception.ServiceException;
import com.epam.pharmacy.service.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class ServiceFactory {

    public UserService getUserService(){
        return new UserService();
    }

    public ClientService getClientService(){
        return new ClientService();
    }

    public MedicineService getMedicineService(){
        return new MedicineService();
    }

    public OrderService getOrderService(){
        return new OrderService();
    }

    public PrescriptionService getPrescriptionService(){
        return new PrescriptionService();
    }

    public RequestService getRequestService(){
        return new RequestService();
    }

}
