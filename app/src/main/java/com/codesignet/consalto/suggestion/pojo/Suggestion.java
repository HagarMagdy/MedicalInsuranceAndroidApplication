package com.codesignet.consalto.suggestion.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Mayada Saleh on 6/1/2018.
 */

public class Suggestion implements Serializable {

    @SerializedName("suggestId")
    private int suggestId;
    @SerializedName("description")
    private String description;
    @SerializedName("medicalType")
    private int medicalType;
    @SerializedName("address")
    private String address;
    @SerializedName("contactPhone")
    private String contactPhone;
    @SerializedName("supervisor")
    private String supervisor;
    @SerializedName("date")
    private String date;
    @SerializedName("medicalServiceNameAr")
    private String medicalServiceNameAr;
    @SerializedName("medicalServiceNameEn")
    private String medicalServiceNameEn;
    @SerializedName("employeeId")
    private int employeeId;
    @SerializedName("latitude")
    private Double latitude;
    @SerializedName("longitude")
    private Double longitude;

    public int getSuggestId() {
        return suggestId;
    }

    public void setSuggestId(int suggestId) {
        this.suggestId = suggestId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMedicalType() {
        return medicalType;
    }

    public void setMedicalType(int medicalType) {
        this.medicalType = medicalType;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMedicalServiceNameAr() {
        return medicalServiceNameAr;
    }

    public void setMedicalServiceNameAr(String medicalServiceNameAr) {
        this.medicalServiceNameAr = medicalServiceNameAr;
    }

    public String getMedicalServiceNameEn() {
        return medicalServiceNameEn;
    }

    public void setMedicalServiceNameEn(String medicalServiceNameEn) {
        this.medicalServiceNameEn = medicalServiceNameEn;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
