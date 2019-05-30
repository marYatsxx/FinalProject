package com.epam.pharmacy.dao.pool;

import com.epam.pharmacy.exception.ConnectionPoolException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static ConnectionPool instance = null;
    private static BlockingQueue<Connection> pool;
    private static AtomicBoolean init = new AtomicBoolean(false);
    private static Lock locker = new ReentrantLock();

    private String driverName;
    private String url;
    private String user;
    private String password;
    private int poolSize;

    private ConnectionPool(){
        DBResourceManager dbManager = new DBResourceManager();
        driverName = dbManager.getValue(DBParameter.DB_DRIVER);
        url = dbManager.getValue(DBParameter.DB_URL);
        user = dbManager.getValue(DBParameter.DB_USER);
        password = dbManager.getValue(DBParameter.DB_PASSWORD);
        poolSize = getPoolSize(dbManager);
    }

    private int getPoolSize(DBResourceManager dbManager){
        int size = 0;
        try {
            size = Integer.parseInt(dbManager.getValue(DBParameter.DB_POOL_SIZE));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return size;
    }

    public static ConnectionPool getInstance() {
        if (!init.get()) {
            locker.lock();
            if (instance==null){
                instance = new ConnectionPool();
                try{
                    instance.createPool();
                } catch (ConnectionPoolException e){
                    e.printStackTrace();
                    LOGGER.error("Can not create connection pool");
                }
            }
            locker.unlock();
        }
        return instance;
    }

    private void createPool() throws ConnectionPoolException{
        pool = new ArrayBlockingQueue<>(poolSize, true);
        try {
            Class.forName(driverName);
            for (int i = 0; i < poolSize; i++) {
                pool.offer(DriverManager.getConnection(url, user, password));
            }
            LOGGER.trace("Connection pool was created successfully");
        } catch (ClassNotFoundException | SQLException e) {
            throw new ConnectionPoolException(e.getMessage());
        }
    }

    public Connection getConnection() throws ConnectionPoolException {
        Connection connection;
        try {
            connection = pool.take();
            LOGGER.trace("getConnection");
        } catch (InterruptedException e) {
           throw new ConnectionPoolException(e.getMessage());
        }
        return connection;
    }

    public void releaseConnection(Connection connection) throws ConnectionPoolException{
        if (connection != null) {
            try {
                pool.put(connection);
                LOGGER.trace("releaseConnection");
            } catch (InterruptedException e) {
                throw new ConnectionPoolException(e.getMessage());
            }
        }
    }

    public void closeAll() throws ConnectionPoolException{
        for(Connection connection: pool){
            try {
                connection.close();
            } catch (SQLException e) {
                throw new ConnectionPoolException(e.getMessage());
            }
        }
    }
}
