package com.epam.pharmacy.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Order implements Identifiable {

    public static final String ORDER = "order";
    public static final String ID = "order_id";
    public static final String PRICE = "price";
    public static final String DATE = "date";
    public static final String CLIENT_ID = "client_id";

    private Integer id;
    private double price;
    private LocalDate date;
    private Integer clientId;

    public Order(Integer id, double price, LocalDate date, Integer clientId) {
        this.id = id;
        this.price = price;
        this.date = date;
        this.clientId = clientId;
    }

    public Order(double price, LocalDate date, Integer clientId) {
        this.price = price;
        this.date = date;
        this.clientId = clientId;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getClientId() {
        return clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                Double.compare(order.price, price) == 0 &&
                clientId == order.clientId &&
                Objects.equals(date, order.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, date, clientId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", price=" + price +
                ", date=" + date +
                ", clientId=" + clientId +
                '}';
    }
}
