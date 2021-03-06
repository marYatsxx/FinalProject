package com.epam.pharmacy.dao.impl;

import com.epam.pharmacy.dao.ClientDao;
import com.epam.pharmacy.entity.builder.Builder;
import com.epam.pharmacy.dao.AbstractDao;
import com.epam.pharmacy.entity.ClientAccount;
import com.epam.pharmacy.exception.DaoException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class ClientDaoImpl extends AbstractDao<ClientAccount> implements ClientDao {
    private static final Logger LOGGER = LogManager.getLogger(ClientDaoImpl.class);

    private static final String UPDATE_ACCOUNT = "UPDATE client_account SET balance = ? WHERE user_id = ?;";
    private static final String FIND_ACCOUNT_BY_ID = "SELECT * FROM client_account WHERE user_id = ?;";
    private static final String FIND_ALL_ACCOUNT_INFO =
            "SELECT * FROM user JOIN client_account ON user.user_id = client_account.user_id;";
    private static final String CREATE_ACCOUNT = "INSERT INTO client_account VALUES(?, ?);";
    private static final String REMOVE_ACCOUNT_BY_ID = "DELETE FROM client_account WHERE user_id = ?;";

    public ClientDaoImpl(Connection connection, Builder<ClientAccount> builder) {
        super(connection, builder);
    }

    @Override
    public Optional<ClientAccount> findById(int id) throws DaoException {
        return executeQueryForSingleResult(FIND_ACCOUNT_BY_ID, id);
    }

    @Override
    public List<ClientAccount> findAll() throws DaoException {
        return executeQuery(FIND_ALL_ACCOUNT_INFO);
    }

    @Override
    public boolean removeById(int id) throws DaoException {
        if (!isPresent(id)) {
            LOGGER.info("Can not remove: Client account with id " + id + " is not found");
            return false;
        }
        executeUpdate(REMOVE_ACCOUNT_BY_ID, id);
        LOGGER.info("Client account has been removed successfully");
        return true;
    }

    @Override
    public boolean remove(ClientAccount account) throws DaoException {
        int id = account.getId();
        removeById(id);
        return true;
    }

    @Override
    public void create(ClientAccount account) throws DaoException {
        Optional<Integer> id = Optional.ofNullable(account.getId());
        BigDecimal balance = account.getBalance().setScale(2, BigDecimal.ROUND_DOWN);
        executeUpdate(CREATE_ACCOUNT, id.get(), balance);
        LOGGER.info("Client account has been created successfully");
    }

    public boolean update(ClientAccount account) throws DaoException {
        Optional<Integer> id = Optional.ofNullable(account.getId());
        BigDecimal balance = account.getBalance().setScale(2, BigDecimal.ROUND_DOWN);
        executeUpdate(UPDATE_ACCOUNT, balance, id.get());
        LOGGER.info("Client account № " + id + ". Update has been executed successfully");
        return true;
    }
}
