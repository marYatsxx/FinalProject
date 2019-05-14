package com.epam.pharmacy.entity.builder.impl;

import com.epam.pharmacy.entity.builder.Builder;
import com.epam.pharmacy.entity.Medicine;
import com.epam.pharmacy.exception.BuilderException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicineBuilder implements Builder<Medicine> {
    @Override
    public Medicine build(ResultSet resultSet) throws SQLException, BuilderException {
        Integer id = resultSet.getInt(Medicine.ID);
        String name = resultSet.getString(Medicine.NAME);
        String dosage = resultSet.getString(Medicine.DOSAGE);
        double price = resultSet.getDouble(Medicine.PRICE);
        boolean needsPrescription = resultSet.getBoolean(Medicine.NEEDS_PRESCRIPTION);
        return new Medicine(id, name, dosage, price, needsPrescription);
    }
}
