package com.codesignet.consalto.feedback.pojo;

import java.io.Serializable;

/**
 * Created by Aya on 30/05/2018.
 */

public class Review implements Serializable {
    private int reviewId;
    private int medicalTypeId;
    private int serviceId;
    private String description;
    private String date;
    private String type;
    private int employeeEmployeeId;
    private float reviewRate;

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public void setMedicalTypeId(int medicalTypeId) {
        this.medicalTypeId = medicalTypeId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEmployeeEmployeeId(int employeeEmployeeId) {
        this.employeeEmployeeId = employeeEmployeeId;
    }

    public void setReviewRate(float reviewRate) {
        this.reviewRate = reviewRate;
    }

    public int getReviewId() {
        return reviewId;
    }

    public int getMedicalTypeId() {
        return medicalTypeId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public int getEmployeeEmployeeId() {
        return employeeEmployeeId;
    }

    public float getReviewRate() {
        return reviewRate;
    }

}
