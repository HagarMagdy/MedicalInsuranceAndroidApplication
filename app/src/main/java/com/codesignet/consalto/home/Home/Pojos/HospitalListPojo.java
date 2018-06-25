package com.codesignet.consalto.home.Home.Pojos;

import java.util.ArrayList;

/**
 * Created by hoda.CO on 29/05/2018.
 */

public class HospitalListPojo {
    private ArrayList<com.codesignet.consalto.home.Home.Pojos.HospitalPojo> hospitals= new ArrayList<>();

    public ArrayList<com.codesignet.consalto.home.Home.Pojos.HospitalPojo> getHospitals() {
        return hospitals;
    }

    public void setHospitals(ArrayList<HospitalPojo> hospitals) {
        this.hospitals = hospitals;
    }
}
