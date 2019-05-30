package com.epam.pharmacy.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class Prescription implements Identifiable {

    public static final String PRESCRIPTION = "prescription";
    public static final String ID = "prescription_id";
    public static final String CLIENT_ID = "client_id";
    public static final String VALIDITY = "validity";
    public static final String MEDICINE_ID = "medicine_id";
    public static final String DOCTOR_ID = "doctor_id";

    private Integer id;
    private Integer clientId;
    private LocalDate validity;
    private Integer doctorId;
    private Integer medicineId;

    public Prescription(Integer id, Integer clientId, LocalDate validity, Integer medicineId, Integer doctorId) {
        this.id = id;
        this.clientId = clientId;
        this.doctorId = doctorId;
        this.validity = validity;
        this.medicineId = medicineId;
    }

    public Prescription(Integer clientId, LocalDate validity, Integer medicineId, Integer doctorId) {
        this.clientId = clientId;
        this.doctorId = doctorId;
        this.validity = validity;
        this.medicineId = medicineId;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public LocalDate getValidity() {
        return validity;
    }

    public Integer getMedicineId() {
        return medicineId;
    }

    public void setValidity(LocalDate validity){
        this.validity = validity;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public void setMedicineId(Integer medicineId) {
        this.medicineId = medicineId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prescription that = (Prescription) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(clientId, that.clientId) &&
                Objects.equals(doctorId, that.doctorId) &&
                Objects.equals(validity, that.validity) &&
                Objects.equals(medicineId, that.medicineId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId, doctorId, validity, medicineId);
    }

    @Override
    public String toString() {
        return "PrescriptionDaoImpl{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", doctorId=" + doctorId +
                ", validity=" + validity +
                ", medicine=" + medicineId +
                '}';
    }
}
