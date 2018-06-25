package com.codesignet.consalto.Profile.retrofit_control.Remote;


import com.codesignet.consalto.Profile.retrofit_control.Pojos.EmployeeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by Aya on 5/31/2018.
 */

public interface Service {
    @GET("getEmployee/employeeID={id}")
    @Headers({"Accept: application/json","Content-Type: application/json"})
    Call<EmployeeResponse> getUser(@Path("id") int id);



}
