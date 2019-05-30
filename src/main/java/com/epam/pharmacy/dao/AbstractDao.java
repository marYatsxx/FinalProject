package com.epam.pharmacy.dao;

import com.epam.pharmacy.entity.builder.Builder;
import com.epam.pharmacy.entity.Identifiable;
import com.epam.pharmacy.exception.BuilderException;
import com.epam.pharmacy.exception.DaoException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T extends Identifiable> implements Dao<T> {
    private static final Logger LOGGER = LogManager.getLogger(AbstractDao.class);
    private final Connection connection;
    private final Builder<T> builder;

    public AbstractDao(Connection connection, Builder<T> builder) {
        this.connection = connection;
        this.builder = builder;
    }

    protected List<T> executeQuery(String query, Object... params) throws DaoException {
        List<T> entities = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            prepareStatement(statement, params);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (!resultSet.wasNull()) {
                    T builtObject = builder.build(resultSet);
                    entities.add(builtObject);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return entities;
    }

    protected void executeUpdate(String query, Object... params) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            prepareStatement(statement, params);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    protected void prepareStatement(PreparedStatement statement, Object... params) throws SQLException {
        for (int i = 1; i < params.length + 1; i++) {
            statement.setObject(i, params[i - 1]);
        }
    }

    protected Optional<T> executeQueryForSingleResult(String query, Object... params) throws DaoException {
        List<T> items = executeQuery(query, params);
        if (items.size() == 1) {
            T firstItem = items.get(0);
            return Optional.of(firstItem);
        } else {
            return Optional.empty();
        }
    }

    protected Connection getConnection() {
        return connection;
    }
}