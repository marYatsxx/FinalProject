package com.epam.pharmacy.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class ClientAccount implements Identifiable, Serializable {
    private static final long serialVersionUID = 12345L;

    public static final String CLIENT = "client";
    public static final String ID = "user_id";
    public static final String BALANCE = "balance";

    private Integer userId;
    private BigDecimal balance;

    public ClientAccount(BigDecimal balance){
        this.balance = balance;
    }

    public ClientAccount(Integer userId, BigDecimal balance) {
        this.userId = userId;
        this.balance = balance;
    }

    @Override
    public Integer getId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientAccount that = (ClientAccount) o;
        return userId == that.userId &&
                balance.compareTo(that.balance) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, balance);
    }

    @Override
    public String toString() {
        return "ClientAccount{" +
                "userId=" + userId +
                ", balance=" + balance +
                '}';
    }
}