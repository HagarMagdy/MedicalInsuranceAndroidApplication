package com.codesignet.consalto.medical_service_details.view;

import android.content.Context;

import com.codesignet.consalto.home.Home.Pojos.ClinicPojo;
import com.codesignet.consalto.home.Home.Pojos.HospitalPojo;
import com.codesignet.consalto.home.Home.Pojos.LabPojo;
import com.codesignet.consalto.home.Home.Pojos.PharmacyPojo;


/**
 * Created by Hagar on 31/05/2018.
 */

public interface DetailsInteractorInterface {
    void setDetails(HospitalPojo details);
    void setDetails(PharmacyPojo details);
    void setDetails(ClinicPojo details);
    void setDetails(LabPojo details);

    void getTheDetails(int tid, int sid);
    void setErrorMessage();
    void askForPermission(String permission, Integer requestCode, Context context);
     void launchPhoneCallIntent();
}
