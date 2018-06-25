package com.codesignet.consalto.home.Home.view;

import com.codesignet.consalto.home.Home.Pojos.ClinicPojo;
import com.codesignet.consalto.home.Home.Pojos.HospitalPojo;
import com.codesignet.consalto.home.Home.Pojos.LabPojo;
import com.codesignet.consalto.home.Home.Pojos.PharmacyPojo;

/**
 * Created by hoda.CO on 30/05/2018.
 */

public interface HomeView_Interface {
    void initView();
    void setHospitalLocation(HospitalPojo hospital);
    void setPharamacyLocation(PharmacyPojo pharmacy);
    void setClinicLocation(ClinicPojo clinic);
    void setLabLocation(LabPojo lab);
    void setSearchHospital(HospitalPojo result);
    void setSearchClinic(ClinicPojo result);
    void setSearchPharmacy(PharmacyPojo result);
    void setSearchLab(LabPojo result);
    void setSearchRes_NoResult();

}
