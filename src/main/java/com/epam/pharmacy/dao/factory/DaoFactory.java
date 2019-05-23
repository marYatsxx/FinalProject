package com.epam.pharmacy.dao.factory;

import com.epam.pharmacy.dao.*;
import com.epam.pharmacy.dao.pool.ConnectionPool;
import com.epam.pharmacy.dao.impl.*;
import com.epam.pharmacy.entity.*;
import com.epam.pharmacy.entity.builder.Builder;
import com.epam.pharmacy.entity.builder.BuilderFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;

public class DaoFactory {
    private static final Logger LOGGER = LogManager.getLogger(DaoFactory.class);
    private Connection connection;
    private final BuilderFactory builderFactory = new BuilderFactory();

    public DaoFactory(){
        connection = ConnectionPool.getInstance().getConnection();
    }

    public UserDao getUserDao() {
        Builder<User> userBuilder = builderFactory.getUserBuilder();
        return new UserDaoImpl(connection, userBuilder);
    }

    public ClientDao getClientDao() {
        Builder<ClientAccount> clientAccountBuilder = builderFactory.getClientAccountBuilder();
        return new ClientDaoImpl(connection, clientAccountBuilder);
    }

    public MedicineDao getMedicineDao() {
        Builder<Medicine> medicineBuilder = builderFactory.getMedicineBuilder();
        return new MedicineDaoImpl(connection, medicineBuilder);
    }

    public PrescriptionDao getPrescriptionDao() {
        Builder<Prescription> prescriptionBuilder = builderFactory.getPrescriptionBuilder();
        return new PrescriptionDaoImpl(connection, prescriptionBuilder);
    }

    public OrderDao getOrderDao() {
        Builder<Order> orderBuilder = builderFactory.getOrderBuilder();
        return new OrderDaoImpl(connection, orderBuilder);
    }

    public RequestDao getRequestDao(){
        Builder<Request> requestBuilder = builderFactory.getRequestBuilder();
        return new RequestDaoImpl(connection, requestBuilder);
    }

    public Connection getConnection() {
        return connection;
    }
}

