package com.epam.pharmacy.entity.builder.impl;

import com.epam.pharmacy.entity.builder.Builder;
import com.epam.pharmacy.entity.Medicine;
import com.epam.pharmacy.exception.BuilderException;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class MedicineBuilder implements Builder<Medicine> {
    @Override
    public Medicine build(ResultSet resultSet) throws SQLException{
        Integer id = resultSet.getInt(Medicine.ID);
        String name = resultSet.getString(Medicine.NAME);
        String dosage = resultSet.getString(Medicine.DOSAGE);
        BigDecimal price = resultSet.getBigDecimal(Medicine.PRICE).setScale(2, BigDecimal.ROUND_DOWN);
        boolean needsPrescription = resultSet.getBoolean(Medicine.NEEDS_PRESCRIPTION);
        return new Medicine(id, name, dosage, price, needsPrescription);
    }
}
