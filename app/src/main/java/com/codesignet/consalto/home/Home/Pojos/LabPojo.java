package com.codesignet.consalto.home.Home.Pojos;

import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;

/**
 * Created by hoda.CO on 30/05/2018.
 */

public class LabPojo implements Serializable{
    private int id;
    private String nameEn;
    private String openHour;
    private String closeHour;
    private Double latitude;
    private Double longitude;
    private String address;
    private String startDate;
    private String endDate;
    private float rate;
    private String ceo;
    private String nameAr;
    private int medicalTypeId;
    private String image;
    private ArrayList<String> labPhones;
    private ArrayList<String> labSpecializations;

    public LabPojo() {
    }

    public LabPojo(int id, String nameEn, String openHour, String closeHour, Double latitude, Double longitude, String address, String startDate, String endDate, float rate, String ceo, String nameAr, int medicalTypeId) {
        this.id = id;
        this.nameEn = nameEn;
        this.openHour = openHour;
        this.closeHour = closeHour;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rate = rate;
        this.ceo = ceo;
        this.nameAr = nameAr;
        this.medicalTypeId = medicalTypeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public int getMedicalTypeId() {
        return medicalTypeId;
    }

    public void setMedicalTypeId(int medicalTypeId) {
        this.medicalTypeId = medicalTypeId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<String> getLabPhones() {
        return labPhones;
    }

    public void setLabPhones(ArrayList<String> labPhones) {
        this.labPhones = labPhones;
    }

    public ArrayList<String> getLabSpecializations() {
        return labSpecializations;
    }

    public void setLabSpecializations(ArrayList<String> labSpecializations) {
        this.labSpecializations = labSpecializations;
    }

}
