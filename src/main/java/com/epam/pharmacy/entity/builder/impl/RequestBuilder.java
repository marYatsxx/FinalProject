package com.epam.pharmacy.entity.builder.impl;

import com.epam.pharmacy.entity.Request;
import com.epam.pharmacy.entity.RequestStatus;
import com.epam.pharmacy.entity.builder.Builder;
import com.epam.pharmacy.exception.BuilderException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestBuilder implements Builder<Request> {
    @Override
    public Request build(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt(Request.ID);
        Integer prescriptionId = resultSet.getInt(Request.PRESCRIPTION_ID);
        RequestStatus status = RequestStatus.valueOf(resultSet.getString(Request.STATUS).toUpperCase());
        return new Request(id, prescriptionId, status);
    }
}
