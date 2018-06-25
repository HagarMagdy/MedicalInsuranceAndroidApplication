package com.codesignet.consalto.home.Home.Pojos;

import com.codesignet.consalto.home.Home.Pojos.PharmacyPojo;

import java.util.ArrayList;

/**
 * Created by hoda.CO on 30/05/2018.
 */

public class PharmacyListPojo {
    private ArrayList<PharmacyPojo> pharmacies= new ArrayList<>();

    public ArrayList<PharmacyPojo> getPharmacies() {
        return pharmacies;
    }

    public void setPharmacies(ArrayList<PharmacyPojo> pharmacies) {
        this.pharmacies = pharmacies;
    }

}
