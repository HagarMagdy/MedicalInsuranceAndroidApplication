package com.codesignet.consalto.home.Home.view;


import com.codesignet.consalto.home.Home.Pojos.ClinicPojo;
import com.codesignet.consalto.home.Home.Pojos.HospitalPojo;
import com.codesignet.consalto.home.Home.Pojos.LabPojo;
import com.codesignet.consalto.home.Home.Pojos.PharmacyPojo;

/**
 * Created by hoda.CO on 30/05/2018.
 */

public interface HomeInteractor_Interface {
    void getData();
    void setHospital(HospitalPojo hospital);
    void setPharmacy(PharmacyPojo pharmacy);
    void setClinic(ClinicPojo clinic);
    void setLab(LabPojo lab);
    void getHospital();
    void getPharmacy();
    void getClinic();
    void getLab();
    void showHospitalsWithDepart(String name);
    void showLabsWithSpec(String name);
    void performSearch(String str);
    void setSearchRes_Hospital(HospitalPojo result);
    void setSearchRes_Clinic(ClinicPojo result);
    void setSearchRes_Pharmay(PharmacyPojo result);
    void setSearchRes_Lab(LabPojo result);
    void setSearchRes_NoResult();
}
