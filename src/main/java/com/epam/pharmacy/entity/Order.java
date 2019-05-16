package com.epam.pharmacy.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Order implements Identifiable {

    public static final String ORDER = "order";
    public static final String ID = "order_id";
    public static final String DATE = "date";
    public static final String CLIENT_ID = "client_id";
    public static final String MEDICINE_ID = "medicine_id";
    public static final String PAID = "paid";

    private Integer id;
    private LocalDate date;
    private Integer clientId;
    private Integer medicineId;
    private boolean paid;

    public Order(Integer id, LocalDate date, Integer clientId, Integer medicineId, boolean paid) {
        this.id = id;
        this.date = date;
        this.clientId = clientId;
        this.medicineId = medicineId;
        this.paid = paid;
    }

    public Order(LocalDate date, Integer clientId, Integer medicineId, boolean paid) {
        this.date = date;
        this.clientId = clientId;
        this.medicineId = medicineId;
        this.paid = paid;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getClientId() {
        return clientId;
    }

    public Integer getMedicineId() {
        return medicineId;
    }

    public boolean isPaid() {
        return paid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return paid == order.paid &&
                Objects.equals(id, order.id) &&
                Objects.equals(date, order.date) &&
                Objects.equals(clientId, order.clientId) &&
                Objects.equals(medicineId, order.medicineId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, clientId, medicineId, paid);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", clientId=" + clientId +
                ", medicineId=" + medicineId +
                ", paid=" + paid +
                '}';
    }
}
