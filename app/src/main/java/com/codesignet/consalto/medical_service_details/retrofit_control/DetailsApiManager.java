package com.codesignet.consalto.medical_service_details.retrofit_control;

import android.util.Log;


import com.codesignet.consalto.home.Home.Pojos.ClinicPojo;
import com.codesignet.consalto.home.Home.Pojos.HospitalPojo;
import com.codesignet.consalto.home.Home.Pojos.LabPojo;
import com.codesignet.consalto.home.Home.Pojos.PharmacyPojo;
import com.codesignet.consalto.medical_service_details.view.DetailsApiManagerInterface;
import com.codesignet.consalto.medical_service_details.view.DetailsInteractorInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Hagar on 31/05/2018.
 */

public class DetailsApiManager implements DetailsApiManagerInterface {
    DetailsInteractorInterface interactor;

    public DetailsApiManager(DetailsInteractorInterface interactor) {
        this.interactor = interactor;
    }


    @Override
    public void retrieveDetails(int tid, int sid) {
        Service apiService = ApiUtils.getService();
        apiService.getHospitalDetails(tid, sid).enqueue(new Callback<HospitalPojo>() {
            @Override
            public void onResponse(Call<HospitalPojo> call, Response<HospitalPojo> response) {
                Log.i("key","obj :"+response.body().getMedicalTypeId());
                interactor.setDetails(response.body());
            }

            @Override
            public void onFailure(Call<HospitalPojo> call, Throwable t) {
                interactor.setErrorMessage();
            }
        });

    }

    @Override
    public void retrievePharmacyDetails(int tid, int sid) {
        Service apiService = ApiUtils.getService();
        apiService.getPharmacyDetails(tid,sid).enqueue(new Callback<PharmacyPojo>() {
            @Override
            public void onResponse(Call<PharmacyPojo> call, Response<PharmacyPojo> response) {
                interactor.setDetails(response.body());
            }

            @Override
            public void onFailure(Call<PharmacyPojo> call, Throwable t) {
                interactor.setErrorMessage();

            }
        });
    }

    @Override
    public void retrieveLabDetails(int tid, int sid) {
        Service apiService = ApiUtils.getService();
        apiService.getLabDetails(tid,sid).enqueue(new Callback<LabPojo>() {
            @Override
            public void onResponse(Call<LabPojo> call, Response<LabPojo> response) {
                interactor.setDetails(response.body());

            }

            @Override
            public void onFailure(Call<LabPojo> call, Throwable t) {
                interactor.setErrorMessage();

            }
        });
    }

    @Override
    public void retrieveClinicDetails(int tid, int sid) {
        Service apiService = ApiUtils.getService();
        apiService.getClinicDetails(tid,sid).enqueue(new Callback<ClinicPojo>() {
            @Override
            public void onResponse(Call<ClinicPojo> call, Response<ClinicPojo> response) {
                interactor.setDetails(response.body());

            }

            @Override
            public void onFailure(Call<ClinicPojo> call, Throwable t) {
                interactor.setErrorMessage();

            }
        });
    }
}
