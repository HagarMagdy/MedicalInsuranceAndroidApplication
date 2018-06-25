package com.codesignet.consalto.forget_password.control;

/**
 * Created by Mayada Saleh on 6/2/2018.
 */

public class ApiRetrofitUtils {

    public static final String BASE_URL = "http://30.0.0.14:4048/MedicalInsuranceSystem/api/version1/user/";

    public static ForgetPasswordServiceRetrofit getService() {
        return RetrofitCreation.getClient(BASE_URL).create(ForgetPasswordServiceRetrofit.class);
    }

}
