package com.codesignet.consalto.Profile.data_access_layer;

import android.content.Context;
import android.util.Log;

import com.codesignet.consalto.Profile.retrofit_control.Pojos.EmployeeResponse;
import com.codesignet.consalto.Profile.retrofit_control.Remote.ApiUtils;
import com.codesignet.consalto.Profile.retrofit_control.Remote.Service;
import com.codesignet.consalto.Profile.view.ApiManagerInterface;
import com.codesignet.consalto.Profile.view.ProfileInteractorInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Aya on 5/31/2018.
 */

public class ApiManager implements ApiManagerInterface {

    private Context context;
    private ProfileInteractorInterface interactor;

    public ApiManager(Context context, ProfileInteractorInterface interactor) {
        this.context = context;
        this.interactor = interactor;
    }

    public void getEmployeeData(int id) {
        Service ApiService = ApiUtils.getService();


        ApiService.getUser(id).enqueue(new Callback<EmployeeResponse>() {
            @Override
            public void onResponse(Call<EmployeeResponse> call, Response<EmployeeResponse> response) {

                Log.i("myTag", "name" + response.body().getEmployeePojo().getName());
                Log.i("myTag", "email" + response.body().getEmployeePojo().getEmail());

                interactor.getEmployee(response.body().getEmployeePojo());
            }

            @Override
            public void onFailure(Call<EmployeeResponse> call, Throwable t) {
                Log.i("myTag", "on failure");

            }
        });

    }
}
