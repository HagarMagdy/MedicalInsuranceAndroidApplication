package com.codesignet.consalto.home.Home.data_access_layer;

import android.util.Log;

import com.codesignet.consalto.home.Home.Pojos.ClinicPojo;
import com.codesignet.consalto.home.Home.Pojos.HospitalPojo;
import com.codesignet.consalto.home.Home.Pojos.LabPojo;
import com.codesignet.consalto.home.Home.Pojos.PharmacyPojo;
import com.codesignet.consalto.home.Home.view.ApiHome_Interface;
import com.codesignet.consalto.home.Home.view.HomeInteractor_Interface;
import com.codesignet.consalto.home.Home.view.HomePresenter_Interface;


public class HomeInteractor implements HomeInteractor_Interface {
     private HomePresenter_Interface mPresenter;
     private ApiHome_Interface mApi;

    public HomeInteractor(HomePresenter_Interface mPresenter) {
        this.mPresenter = mPresenter;
        initInteractor();
    }
    void initInteractor()
    {
        mApi=new ApiHomeImpl(this);
        Log.i("hoda","presenter");
    }

    @Override
    public void getData() {
      mApi.getAllServices();
    }

    @Override
    public void setHospital(HospitalPojo hospital) {
        mPresenter.setHospital(hospital);
    }

    @Override
    public void setPharmacy(PharmacyPojo pharmacy) {
       mPresenter.setPharmacy(pharmacy);
    }

    @Override
    public void setClinic(ClinicPojo clinic) {
      mPresenter.setClinic(clinic);
    }

    @Override
    public void setLab(LabPojo lab) {
      mPresenter.setLab(lab);
    }

    @Override
    public void getHospital() {
       mApi.getAllHospitals();
    }

    @Override
    public void getPharmacy() {
       mApi.getAllPharmacies();
    }

    @Override
    public void getClinic() {
      mApi.getAllClinics();
    }

    @Override
    public void getLab() {
      mApi.getAllLabs();
    }

    @Override
    public void showHospitalsWithDepart(String name) {
        mApi.showHospitalsWithDepart(name);
    }

    @Override
    public void showLabsWithSpec(String name) {
      mApi.showLabsWithSpec(name);
    }

    @Override
    public void performSearch(String str) {
        Log.i("hoda","interactor presenter");
        mApi.performSearch(str);
    }

    @Override
    public void setSearchRes_Hospital(HospitalPojo result) {
         mPresenter.setSearchRes_Hospital(result);
    }

    @Override
    public void setSearchRes_Clinic(ClinicPojo result) {
         mPresenter.setSearchRes_Clinic(result);
    }

    @Override
    public void setSearchRes_Pharmay(PharmacyPojo result) {
         mPresenter.setSearchRes_Pharmay(result);
    }

    @Override
    public void setSearchRes_Lab(LabPojo result) {
        mPresenter.setSearchRes_Lab(result);
    }

    @Override
    public void setSearchRes_NoResult() {
        mPresenter.setSearchRes_NoResult();

    }

}
