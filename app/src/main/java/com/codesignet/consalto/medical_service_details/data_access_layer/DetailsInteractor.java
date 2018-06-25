package com.codesignet.consalto.medical_service_details.data_access_layer;

import android.content.Context;

import com.codesignet.consalto.home.Home.Pojos.ClinicPojo;
import com.codesignet.consalto.home.Home.Pojos.HospitalPojo;
import com.codesignet.consalto.home.Home.Pojos.LabPojo;
import com.codesignet.consalto.home.Home.Pojos.PharmacyPojo;
import com.codesignet.consalto.medical_service_details.retrofit_control.DetailsApiManager;
import com.codesignet.consalto.medical_service_details.view.DetailsApiManagerInterface;
import com.codesignet.consalto.medical_service_details.view.DetailsInteractorInterface;
import com.codesignet.consalto.medical_service_details.view.DetailsPresenterInterface;

/**
 * Created by Hagar on 31/05/2018.
 */

public class DetailsInteractor implements DetailsInteractorInterface {

    DetailsPresenterInterface presenter;
    DetailsApiManagerInterface apiManager;
    PhoneCall call;

    public DetailsInteractor(DetailsPresenterInterface presenter) {
        this.presenter = presenter;
        initApiManager();
        initCall();
    }

    void initApiManager() {
        apiManager = new DetailsApiManager(this);
    }

    void initCall() {
        call = new PhoneCall(this);
    }

    @Override
    public void setDetails(HospitalPojo details) {
        presenter.setDetailsToView(details);
    }

    @Override
    public void setDetails(PharmacyPojo details) {
        presenter.setDetailsToView(details);
    }

    @Override
    public void setDetails(ClinicPojo details) {
        presenter.setDetailsToView(details);
    }

    @Override
    public void setDetails(LabPojo details) {
        presenter.setDetailsToView(details);
    }

    @Override
    public void getTheDetails(int tid, int sid) {
        if (tid == 1) {
            apiManager.retrieveDetails(tid, sid);
        }
        else if (tid == 2) {
            apiManager.retrieveClinicDetails(tid, sid);
        }
        else if (tid == 3) {
            apiManager.retrievePharmacyDetails(tid, sid);
        }
        else if (tid == 4) {
            apiManager.retrieveLabDetails(tid, sid);
        }

    }

    @Override
    public void setErrorMessage() {
        presenter.retrofitError();
    }

    @Override
    public void askForPermission(String permission, Integer requestCode, Context context) {
        call.askForPermission(permission, requestCode, context);
    }

    @Override
    public void launchPhoneCallIntent() {
        presenter.launchPhoneIntent();
    }
}
