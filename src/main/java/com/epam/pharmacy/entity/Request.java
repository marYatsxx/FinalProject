package com.epam.pharmacy.entity;

import java.util.Objects;

public class Request implements Identifiable {

    public static final String REQUEST = "request";
    public static final String ID = "request_id";
    public static final String PRESCRIPTION_ID = "prescription_id";
    public static final String STATUS = "status";

    private Integer id;
    private Integer prescriptionId;
    private RequestStatus status;

    public Request(Integer prescriptionId, RequestStatus status) {
        this.prescriptionId = prescriptionId;
        this.status = status;
    }

    public Request(Integer id, Integer prescriptionId, RequestStatus status) {
        this.id = id;
        this.prescriptionId = prescriptionId;
        this.status = status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Integer prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(id, request.id) &&
                Objects.equals(prescriptionId, request.prescriptionId) &&
                status == request.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, prescriptionId, status);
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", prescriptionId=" + prescriptionId +
                ", status=" + status +
                '}';
    }
}
