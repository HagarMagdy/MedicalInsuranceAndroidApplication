package com.codesignet.consalto.home.Home.Pojos;

import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;

/**
 * Created by hoda.CO on 30/05/2018.
 */

public class ClinicPojo implements Serializable{

    private int id;
    private double longitude;
    private double latitude;
    private String address;
    private String specialization;
    private String endDate;
    private String startDate;
    private String openHour;
    private String closeHour;
    private float rate;
    private String doctorNameEn;
    private String doctorNameAr;
    private int medicalTypeId;
    private ArrayList<String> phones;
    private String image;

    public ClinicPojo() {
    }

    public ClinicPojo(int id, double longitude, double latitude, String address, String specialization, String endDate, String startDate, String openHour, String closeHour, float rate, String doctorNameEn, String doctorNameAr, int medicalTypeId) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.specialization = specialization;
        this.endDate = endDate;
        this.startDate = startDate;
        this.openHour = openHour;
        this.closeHour = closeHour;
        this.rate = rate;
        this.doctorNameEn = doctorNameEn;
        this.doctorNameAr = doctorNameAr;
        this.medicalTypeId = medicalTypeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getOpenHour() {
        return openHour;
    }

    public void setOpenHour(String openHour) {
        this.openHour = openHour;
    }

    public String getCloseHour() {
        return closeHour;
    }

    public void setCloseHour(String closeHour) {
        this.closeHour = closeHour;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getDoctorNameEn() {
        return doctorNameEn;
    }

    public void setDoctorNameEn(String doctorNameEn) {
        this.doctorNameEn = doctorNameEn;
    }

    public String getDoctorNameAr() {
        return doctorNameAr;
    }

    public void setDoctorNameAr(String doctorNameAr) {
        this.doctorNameAr = doctorNameAr;
    }

    public int getMedicalTypeId() {
        return medicalTypeId;
    }

    public void setMedicalTypeId(int medicalTypeId) {
        this.medicalTypeId = medicalTypeId;
    }

    public ArrayList<String> getPhones() {
        return phones;
    }

    public void setPhones(ArrayList<String> phones) {
        this.phones = phones;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
