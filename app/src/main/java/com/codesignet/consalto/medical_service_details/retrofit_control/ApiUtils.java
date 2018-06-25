package com.codesignet.consalto.medical_service_details.retrofit_control;

/**
 * Created by Hagar on 31/05/2018.
 */

public class ApiUtils {
    public static final String BASE_URL = "http://30.0.0.14:4048/MedicalInsuranceSystem/api/version1/details/";


    public static Service getService() {
        return RetrofitClient.getClient(BASE_URL).create(Service.class);
    }
}
