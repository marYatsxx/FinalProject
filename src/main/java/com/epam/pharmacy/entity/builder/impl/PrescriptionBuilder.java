package com.epam.pharmacy.entity.builder.impl;

import com.epam.pharmacy.entity.builder.Builder;
import com.epam.pharmacy.entity.Prescription;
import com.epam.pharmacy.exception.BuilderException;


import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class PrescriptionBuilder implements Builder<Prescription> {
    @Override
    public Prescription build(ResultSet resultSet) throws SQLException, BuilderException {
        Integer id = resultSet.getInt(Prescription.ID);
        Integer clientId = resultSet.getInt(Prescription.CLIENT_ID);
        Date date = resultSet.getDate(Prescription.VALIDITY);
        LocalDate validity = date.toLocalDate();
        Integer medicineId = resultSet.getInt(Prescription.MEDICINE_ID);
        Integer doctorId = resultSet.getInt(Prescription.DOCTOR_ID);
        return new Prescription(id, clientId, validity, medicineId, doctorId);
    }
}
