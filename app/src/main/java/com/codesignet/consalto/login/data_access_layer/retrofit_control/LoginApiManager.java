package com.codesignet.consalto.login.data_access_layer.retrofit_control;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.codesignet.consalto.login.data_access_layer.pojos.LoginRequestPojo;
import com.codesignet.consalto.login.data_access_layer.pojos.LoginResponsePojo;
import com.codesignet.consalto.login.view.LoginApiManagerInterface;
import com.codesignet.consalto.login.view.LoginInteractorInterface;
import com.codesignet.consalto.login.view.LoginPresenterInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Hagar on 30/05/2018.
 */

public class LoginApiManager implements LoginApiManagerInterface {
    LoginInteractorInterface listener;
    SharedPreference sharedPreferenceObj;

    public LoginApiManager(LoginInteractorInterface listener) {
        this.listener = listener;
        initSharedPreference();
    }

    void initSharedPreference() {
        sharedPreferenceObj = new SharedPreference();
    }

    @Override
    public void ProsessLogin(final String email, final String password, final Context context) {
        Service apiService = ApiUtils.getService();
        LoginRequestPojo user = new LoginRequestPojo(email, password);
        apiService.getUser(user).enqueue(new Callback<LoginResponsePojo>() {
            @Override
            public void onResponse(Call<LoginResponsePojo> call, Response<LoginResponsePojo> response) {
                if (response.body().getStatus() == true) {
                    int id = response.body().getId();
                    Log.i("empId","employee id is"+id);
                    sharedPreferenceObj.saveToSharedPreferences(email, password, context,id);

                    listener.success();
                    listener.passId(response.body().getId());
                } else {
                    listener.userNotFound();
                }
            }

            @Override
            public void onFailure(Call<LoginResponsePojo> call, Throwable t) {
                listener.retrofitError();
            }
        });
    }
}
