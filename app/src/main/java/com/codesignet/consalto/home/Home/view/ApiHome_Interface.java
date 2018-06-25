package com.codesignet.consalto.home.Home.view;

import com.codesignet.consalto.home.Home.Pojos.ClinicPojo;
import com.codesignet.consalto.home.Home.Pojos.HospitalPojo;
import com.codesignet.consalto.home.Home.Pojos.LabPojo;
import com.codesignet.consalto.home.Home.Pojos.PharmacyPojo;

/**
 * Created by hoda.CO on 31/05/2018.
 */

public interface ApiHome_Interface {
    void getAllServices();
    void getAllHospitals();
    void getAllPharmacies();
    void getAllClinics();
    void getAllLabs();
    void showHospitalsWithDepart(String name);
    void showLabsWithSpec(String name);
    void performSearch(String str);
    HospitalPojo getServiceHospital(int type_id, int medicalservice_id);
    ClinicPojo getServiceClinic(int type_id, int medicalservice_id);
    PharmacyPojo getServicePharmacy(int type_id, int medicalservice_id);
    LabPojo getServiceLab(int type_id, int medicalservice_id);
}
