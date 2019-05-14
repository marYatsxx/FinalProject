package com.epam.pharmacy.entity.builder;

import com.epam.pharmacy.entity.*;
import com.epam.pharmacy.entity.builder.Builder;
import com.epam.pharmacy.entity.builder.impl.*;

public class BuilderFactory {
    private final Builder<User> userBuilder = new UserBuilder();
    private final Builder<ClientAccount> clientAccountBuilder = new ClientBuilder();
    private final Builder<Medicine> medicineBuilder = new MedicineBuilder();
    private final Builder<Prescription> prescriptionBuilder = new PrescriptionBuilder();
    private final Builder<Order> orderBuilder = new OrderBuilder();

    public Builder<User> getUserBuilder(){
        return userBuilder;
    }

    public Builder<ClientAccount> getClientAccountBuilder() {
        return clientAccountBuilder;
    }

    public Builder<Medicine> getMedicineBuilder() {
        return medicineBuilder;
    }

    public Builder<Prescription> getPrescriptionBuilder() {
        return prescriptionBuilder;
    }

    public Builder<Order> getOrderBuilder() {
        return orderBuilder;
    }
}
