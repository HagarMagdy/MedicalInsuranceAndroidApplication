package com.codesignet.consalto.login.data_access_layer.retrofit_control;

import android.content.Context;

/**
 * Created by Hagar on 30/05/2018.
 */

public class ApiUtils {
    public static final String BASE_URL = "http://30.0.0.14:4048/MedicalInsuranceSystem/api/version1/user/";


    public static Service getService() {

        return RetrofitClient.getClient(BASE_URL).create(Service.class);
    }
}
