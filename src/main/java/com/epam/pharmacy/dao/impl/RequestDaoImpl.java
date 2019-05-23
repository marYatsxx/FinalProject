package com.epam.pharmacy.dao.impl;

import com.epam.pharmacy.dao.AbstractDao;
import com.epam.pharmacy.dao.RequestDao;
import com.epam.pharmacy.entity.Request;
import com.epam.pharmacy.entity.builder.Builder;
import com.epam.pharmacy.exception.DaoException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class RequestDaoImpl extends AbstractDao<Request> implements RequestDao {
    private static final Logger LOGGER = LogManager.getLogger(RequestDaoImpl.class);

    private static final String UPDATE_REQUEST = "UPDATE request SET status = ? WHERE request_id = ?;";
    private static final String FIND_ALL_REQUESTS = "SELECT * FROM request;";
    private static final String CREATE_REQUEST = "INSERT INTO request VALUES(default, ?, ?);";
    private static final String REMOVE_REQUEST_BY_ID = "DELETE FROM request WHERE request_id = ?;";
    private static final String FIND_REQUEST_BY_ID = "SELECT * FROM request WHERE request_id = ?;";
    private static final String FIND_ACCEPTED_REQUESTS = "SELECT * FROM request WHERE status=\"accepted\";";
    private static final String FIND_REJECTED_REQUESTS = "SELECT * FROM request WHERE status=\"rejected\";";
    private static final String FIND_CONSIDERED_REQUESTS = "SELECT * FROM request WHERE status=\"under_consideration\";";

    public RequestDaoImpl(Connection connection, Builder<Request> builder) {
        super(connection, builder);
    }

    @Override
    public Optional<Request> findById(int id) throws DaoException {
        return executeQueryForSingleResult(FIND_REQUEST_BY_ID, id);
    }

    @Override
    public List<Request> findAll() throws DaoException {
        return executeQuery(FIND_ALL_REQUESTS);
    }

    @Override
    public boolean removeById(int id) throws DaoException {
        if(!isPresent(id)){
            LOGGER.info("Can not remove: Request with id " + id + " is not found");
            return false;
        }
        executeUpdate(REMOVE_REQUEST_BY_ID, id);
        LOGGER.info("Request has been removed successfully");
        return true;
    }

    @Override
    public boolean remove(Request item) throws DaoException {
        int id = item.getId();
        removeById(id);
        return true;
    }

    @Override
    public void create(Request item) throws DaoException {
        Optional<Integer> id = Optional.ofNullable(item.getId());
        int prescriptionId = item.getPrescriptionId();
        String status = item.getStatus().name().toLowerCase();
        if(id.isPresent()){
            executeUpdate(UPDATE_REQUEST, status, id.get());
            LOGGER.info("Request № " + id + ". Update has been executed successfully");
        } else {
            executeUpdate(CREATE_REQUEST, prescriptionId, status);
            LOGGER.info("Request has been created successfully");
        }
    }

    @Override
    public List<Request> findRejectedRequests() throws DaoException {
        return executeQuery(FIND_REJECTED_REQUESTS);
    }

    @Override
    public List<Request> findAcceptedRequests() throws DaoException {
        return executeQuery(FIND_ACCEPTED_REQUESTS);
    }

    @Override
    public List<Request> findConsideredRequests() throws DaoException {
        return executeQuery(FIND_CONSIDERED_REQUESTS);
    }
}
