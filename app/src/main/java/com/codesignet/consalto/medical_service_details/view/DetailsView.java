package com.codesignet.consalto.medical_service_details.view;



import com.codesignet.consalto.home.Home.Pojos.ClinicPojo;
import com.codesignet.consalto.home.Home.Pojos.HospitalPojo;
import com.codesignet.consalto.home.Home.Pojos.LabPojo;
import com.codesignet.consalto.home.Home.Pojos.PharmacyPojo;

import javax.security.auth.callback.Callback;

/**
 * Created by Hagar on 31/05/2018.
 */

public interface DetailsView {
    void setDetailsFields(HospitalPojo details);
    void setDetailsFields(LabPojo details);
    void setDetailsFields(ClinicPojo details);
    void setDetailsFields(PharmacyPojo details);

    void viewError();
   void launchActivity();
}
