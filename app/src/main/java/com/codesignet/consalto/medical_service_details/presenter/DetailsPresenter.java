package com.codesignet.consalto.medical_service_details.presenter;

import android.content.Context;


import com.codesignet.consalto.home.Home.Pojos.ClinicPojo;
import com.codesignet.consalto.home.Home.Pojos.HospitalPojo;
import com.codesignet.consalto.home.Home.Pojos.LabPojo;
import com.codesignet.consalto.home.Home.Pojos.PharmacyPojo;
import com.codesignet.consalto.medical_service_details.data_access_layer.DetailsInteractor;
import com.codesignet.consalto.medical_service_details.view.DetailsInteractorInterface;
import com.codesignet.consalto.medical_service_details.view.DetailsPresenterInterface;
import com.codesignet.consalto.medical_service_details.view.DetailsView;

/**
 * Created by Hagar on 31/05/2018.
 */

public class DetailsPresenter implements DetailsPresenterInterface {

    DetailsView view;
    DetailsInteractorInterface interactor;

    public DetailsPresenter(DetailsView view) {
        this.view = view;
        initInteractor();
    }
    void initInteractor(){
        interactor = new DetailsInteractor(this);
    }

    @Override
    public void setDetailsToView(HospitalPojo details) {
      view.setDetailsFields(details);
    }

    @Override
    public void setDetailsToView(ClinicPojo details) {
     view.setDetailsFields(details);

    }

    @Override
    public void setDetailsToView(PharmacyPojo details) {
       view.setDetailsFields(details);

    }

    @Override
    public void setDetailsToView(LabPojo details) {
       view.setDetailsFields(details);

    }

    @Override
    public void getDetails(int tid, int sid) {
      interactor.getTheDetails(tid,sid);
    }

    @Override
    public void retrofitError() {
        interactor.setErrorMessage();
    }

    @Override
    public void askForPermission(String permission, Integer requestCode, Context context) {
        interactor.askForPermission(permission,requestCode,context);
    }

    @Override
    public void launchPhoneIntent() {
       view.launchActivity();
    }
}
