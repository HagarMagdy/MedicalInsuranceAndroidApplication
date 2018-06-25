package com.codesignet.consalto.forget_password.control;

import com.codesignet.consalto.forget_password.pojo.EmailPojo;
import com.codesignet.consalto.forget_password.pojo.ResponseMessage;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Mayada Saleh on 6/2/2018.
 */

public interface ForgetPasswordServiceRetrofit {

    @POST("forget")
    @Headers({"Accept: application/json","Content-Type: application/json"})
    Call<ResponseMessage> resetPassword (@Body EmailPojo email);

}
