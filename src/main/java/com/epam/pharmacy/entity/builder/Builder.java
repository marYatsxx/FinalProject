package com.epam.pharmacy.entity.builder;

import com.epam.pharmacy.entity.Identifiable;
import com.epam.pharmacy.exception.BuilderException;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Builder <T extends Identifiable> {
    T build(ResultSet resultSet) throws SQLException;
}
