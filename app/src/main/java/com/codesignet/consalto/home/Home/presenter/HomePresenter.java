package com.codesignet.consalto.home.Home.presenter;

import android.util.Log;

import com.codesignet.consalto.home.Home.Pojos.ClinicPojo;
import com.codesignet.consalto.home.Home.Pojos.HospitalPojo;
import com.codesignet.consalto.home.Home.Pojos.LabPojo;
import com.codesignet.consalto.home.Home.Pojos.PharmacyPojo;
import com.codesignet.consalto.home.Home.data_access_layer.HomeInteractor;
import com.codesignet.consalto.home.Home.view.HomeInteractor_Interface;
import com.codesignet.consalto.home.Home.view.HomePresenter_Interface;
import com.codesignet.consalto.home.Home.view.HomeView_Interface;
import com.codesignet.consalto.home.Home.view.HomePresenter_Interface;


public class HomePresenter implements HomePresenter_Interface {

    private HomeInteractor_Interface mInteractor;
    private HomeView_Interface mView;

    public HomePresenter(HomeView_Interface mView) {
        this.mView = mView;
        initPresenter();
    }
    void initPresenter()
    {
        mInteractor=new HomeInteractor(this);
        Log.i("hoda","presenter");
        mView.initView();
    }

    @Override
    public Boolean getAllServices() {

        mInteractor.getData();
        return true;
    }

    @Override
    public void setHospital(HospitalPojo hospital) {
        mView.setHospitalLocation(hospital);
    }

    @Override
    public void setPharmacy(PharmacyPojo pharmacy) {
        mView.setPharamacyLocation(pharmacy);
    }

    @Override
    public void setClinic(ClinicPojo clinic) {
        mView.setClinicLocation(clinic);
    }

    @Override
    public void setLab(LabPojo lab) {
        mView.setLabLocation(lab);
    }

    @Override
    public void getHospital() {
        mInteractor.getHospital();
    }

    @Override
    public void getPharmacy() {
       mInteractor.getPharmacy();
    }

    @Override
    public void getClinic() {
      mInteractor.getClinic();
    }

    @Override
    public void getLab() {
     mInteractor.getLab();
    }

    @Override
    public void showHospitalsWithDepart(String name) {
        mInteractor.showHospitalsWithDepart(name);
    }

    @Override
    public void showLabsWithSpec(String name) {
     mInteractor.showLabsWithSpec(name);
    }

    @Override
    public void performSearch(String str) {
        Log.i("hoda","search presenter");
        mInteractor.performSearch(str);
    }
    @Override
    public void setSearchRes_Hospital(HospitalPojo result) {
         mView.setSearchHospital(result);
    }

    @Override
    public void setSearchRes_Clinic(ClinicPojo result) {
       mView.setSearchClinic(result);
    }
    @Override
    public void setSearchRes_Pharmay(PharmacyPojo result) {
        mView.setSearchPharmacy(result);
    }

    @Override
    public void setSearchRes_Lab(LabPojo result) {
        mView.setSearchLab(result);
    }

    @Override
    public void setSearchRes_NoResult() {
        mView.setSearchRes_NoResult();

    }
}
