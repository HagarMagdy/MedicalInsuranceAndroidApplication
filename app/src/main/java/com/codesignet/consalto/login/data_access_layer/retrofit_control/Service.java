package com.codesignet.consalto.login.data_access_layer.retrofit_control;

import com.codesignet.consalto.login.data_access_layer.pojos.LoginRequestPojo;
import com.codesignet.consalto.login.data_access_layer.pojos.LoginResponsePojo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Hagar on 30/05/2018.
 */

public interface Service {
    @POST("login")
    Call<LoginResponsePojo> getUser(@Body LoginRequestPojo pojo);
}
