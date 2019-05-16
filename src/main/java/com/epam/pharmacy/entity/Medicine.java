package com.epam.pharmacy.entity;

import java.util.Objects;

public class Medicine implements Identifiable {

    public static final String MEDICINE = "medicine";
    public static final String ID = "medicine_id";
    public static final String NAME = "name";
    public static final String DOSAGE = "dosage";
    public static final String PRICE = "price";
    public static final String NEEDS_PRESCRIPTION = "needs_prescription";

    private Integer id;
    private String name;
    private String dosage;
    private double price;
    private boolean needsPrescription;

    public Medicine(Integer id, String name, String dosage, double price, boolean needsPrescription) {
        this.id = id;
        this.name = name;
        this.dosage = dosage;
        this.price = price;
        this.needsPrescription = needsPrescription;
    }

    public Medicine(String name, String dosage, double price, boolean needsPrescription) {
        this.name = name;
        this.dosage = dosage;
        this.price = price;
        this.needsPrescription = needsPrescription;
    }

    public String getName() {
        return name;
    }

    public String getDosage() {
        return dosage;
    }

    public double getPrice() {
        return price;
    }

    public boolean needsPrescription() {
        return needsPrescription;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicine medicine = (Medicine) o;
        return dosage == medicine.dosage &&
                Double.compare(medicine.price, price) == 0 &&
                needsPrescription == medicine.needsPrescription &&
                Objects.equals(id, medicine.id) &&
                Objects.equals(name, medicine.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dosage, price, needsPrescription);
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dosage=" + dosage +
                ", price=" + price +
                ", needsPrescription=" + needsPrescription +
                '}';
    }
}
