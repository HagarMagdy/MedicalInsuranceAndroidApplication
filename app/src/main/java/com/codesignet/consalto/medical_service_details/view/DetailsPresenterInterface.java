package com.codesignet.consalto.medical_service_details.view;

import android.content.Context;

import com.codesignet.consalto.home.Home.Pojos.ClinicPojo;
import com.codesignet.consalto.home.Home.Pojos.HospitalPojo;
import com.codesignet.consalto.home.Home.Pojos.LabPojo;
import com.codesignet.consalto.home.Home.Pojos.PharmacyPojo;

/**
 * Created by Hagar on 31/05/2018.
 */

public interface DetailsPresenterInterface {
    void setDetailsToView(HospitalPojo details);
    void setDetailsToView(ClinicPojo details);
    void setDetailsToView(PharmacyPojo details);
    void setDetailsToView(LabPojo details);

    void getDetails(int tid, int sid);
    void retrofitError();
    void askForPermission(String permission, Integer requestCode, Context context);
     void launchPhoneIntent();
}
