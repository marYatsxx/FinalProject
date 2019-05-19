package com.epam.pharmacy.dao;

import com.epam.pharmacy.entity.ClientAccount;
import com.epam.pharmacy.exception.DaoException;

public interface ClientDao extends Dao<ClientAccount> {
    boolean update(ClientAccount account) throws DaoException;
}
