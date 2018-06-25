package com.codesignet.consalto.Profile.retrofit_control.Remote;

/**
 * Created by Aya on 5/31/2018.
 */

public class ApiUtils {
    public static final String BASE_URL = "http://30.0.0.14:4048/MedicalInsuranceSystem/api/version1/user/";

    public static Service getService() {

        return RetrofitClient.getClient(BASE_URL).create(Service.class);
    }
}
